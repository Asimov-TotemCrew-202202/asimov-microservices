package com.totemcrew.feign;

import com.totemcrew.payload.request.CreatePrincipalResource;
import com.totemcrew.payload.request.CreateTeacherResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "director-service", path = "/api/v1")
public interface PrincipalFeignClient {

    @PostMapping("principals")
    CreatePrincipalResource createPrincipal(@RequestBody CreatePrincipalResource request);

    @PostMapping("principals/{principalId}/teachers")
    CreatePrincipalResource createTeacher(@PathVariable Long principalId, @RequestBody CreateTeacherResource request);
}
