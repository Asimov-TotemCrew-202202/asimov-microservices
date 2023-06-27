package com.totemcrew.alternative_student_question.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("alternativeStudentQuestionMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public AlternativeStudentQuestionMapper alternativeStudentQuestionMapper() { return new AlternativeStudentQuestionMapper(); }
}
