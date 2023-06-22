package com.totemcrew.topics.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("itemMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public TopicMapper itemMapper() { return new TopicMapper(); }
}
