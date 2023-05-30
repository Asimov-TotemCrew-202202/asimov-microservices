package com.totemcrew.directors.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("principalMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PrincipalMapper directorMapper() { return new PrincipalMapper(); }
}
