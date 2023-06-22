package com.totemcrew.programschools.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("programSchoolMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ProgramSchoolMapper programSchoolMapper() { return new ProgramSchoolMapper(); }
}
