package com.totemcrew.grades.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("gradeMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public GradeMapper gradeMapper() { return new GradeMapper(); }
}
