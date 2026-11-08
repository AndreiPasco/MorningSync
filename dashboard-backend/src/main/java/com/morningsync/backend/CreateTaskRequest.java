package com.morningsync.backend;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(@NotBlank String title) {
}
