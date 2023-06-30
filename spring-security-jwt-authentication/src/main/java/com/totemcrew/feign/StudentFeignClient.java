package com.totemcrew.feign;

import com.totemcrew.payload.request.CreateStudentResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "student-service", url = "http://13.59.217.223:8030/api/v1/students")
public interface StudentFeignClient {

    @PostMapping()
    CreateStudentResource createStudent(@RequestBody CreateStudentResource request);
}
