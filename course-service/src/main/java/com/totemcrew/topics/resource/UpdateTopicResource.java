package com.totemcrew.topics.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateTopicResource {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String file;
    private Boolean status;
}
