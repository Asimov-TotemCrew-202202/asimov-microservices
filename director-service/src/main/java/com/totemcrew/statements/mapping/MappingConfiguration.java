package com.totemcrew.statements.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("statementMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public StatementMapper statementMapper() { return new StatementMapper(); }
}
