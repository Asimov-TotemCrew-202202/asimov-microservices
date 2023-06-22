package com.totemcrew.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.totemcrew.feign.StudentFeignClient;
import com.totemcrew.payload.request.CreateStudentResource;
import com.totemcrew.payload.request.SignUpRequestGeneral;
import com.totemcrew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.totemcrew.models.ERole;
import com.totemcrew.models.Role;
import com.totemcrew.models.User;
import com.totemcrew.payload.request.LoginRequest;
import com.totemcrew.payload.request.SignupRequest;
import com.totemcrew.payload.response.JwtResponse;
import com.totemcrew.payload.response.MessageResponse;
import com.totemcrew.repository.RoleRepository;
import com.totemcrew.repository.UserRepository;
import com.totemcrew.security.jwt.JwtUtils;
import com.totemcrew.security.services.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(),
                         userDetails.getEmail(),
                         userDetails.getFirst_name(),
                          userDetails.getLast_name(),
                          userDetails.getPhone(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestGeneral signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getFirst_name(),
            signUpRequest.getLast_name(),
            signUpRequest.getPhone()
            );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "teacher":
          Role adminRole = roleRepository.findByName(ERole.ROLE_TEACHER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
          isStudent = false;
          isPrincipal = false;
          isTeacher = true;
          break;
        case "principal":
          Role modRole = roleRepository.findByName(ERole.ROLE_PRINCIPAL)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);
          isStudent = false;
          isPrincipal = true;
          isTeacher = false;
          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
          isStudent = true;
          isPrincipal = false;
          isTeacher = false;
        }
      });
    }

    user.setRoles(roles);
    User userRegistered = userRepository.save(user);

    if(isStudent) {
      CreateStudentResource studentResource = new CreateStudentResource();
      studentResource.setParentFullName(signUpRequest.getParentFullName());
      studentResource.setPhoneParent(signUpRequest.getPhoneParent());
      studentResource.setUserId(userRegistered.getId());
      studentResource.setSectionId(signUpRequest.getSectionId());
      studentFeignClient.createStudent(studentResource);
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadUsers(@RequestParam("file") MultipartFile file) {
    try {
      List<User> users = new ArrayList<>();

      // Leer el archivo CSV y mapear los datos a objetos User
      BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
      String line;
      boolean headerLine = true; // Indica si la línea actual es la línea de encabezado
      while ((line = reader.readLine()) != null) {
        if (headerLine) {
          headerLine = false;
          continue; // Saltar la línea de encabezado
        }

        String[] fields = line.split(",");

        // Crea un nuevo objeto User y configura los campos correspondientes
        User user = new User();
        user.setUsername(fields[0]);
        user.setEmail(fields[1]);
        user.setPassword(fields[2]);
        user.setFirst_name(fields[3]);
        user.setLast_name(fields[4]);
        user.setPhone(fields[5]);

        users.add(user);
      }

      // Guarda los usuarios en la base de datos
      userService.saveAll(users);

      return ResponseEntity.ok("Usuarios insertados exitosamente.");
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar usuarios.");
    }
  }

}
