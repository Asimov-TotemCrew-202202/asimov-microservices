package com.totemcrew.teachers.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("teacherMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public TeacherMapper teacherMapper() { return new TeacherMapper(); }
}
