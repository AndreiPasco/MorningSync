package com.morningsync.backend;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTaskRequest(@NotBlank String title, LocalDateTime deadline) {
}
