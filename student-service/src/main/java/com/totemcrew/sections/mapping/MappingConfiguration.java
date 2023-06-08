package com.totemcrew.sections.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("sectionMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public SectionMapper sectionMapper() { return new SectionMapper(); }
}
