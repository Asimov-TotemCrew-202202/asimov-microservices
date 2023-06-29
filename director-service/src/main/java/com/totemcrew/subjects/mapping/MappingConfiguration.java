package com.totemcrew.subjects.mapping;

import com.totemcrew.teachers.mapping.TeacherMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("subjectMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public SubjectMapper subjectMapper() { return new SubjectMapper(); }
}
