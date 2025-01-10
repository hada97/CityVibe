package com.start.CityVibe.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDetail(
        @NotBlank String name,

        Object o) {
}