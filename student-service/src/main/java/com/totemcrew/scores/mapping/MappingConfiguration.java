package com.totemcrew.scores.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("scoreMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ScoreMapper scoreMapper() { return new ScoreMapper(); }
}
