package com.pepop99.calendly.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserDTO {
    @Schema(example = "rishabharora1780@gmail.com")
    private String email;
}
