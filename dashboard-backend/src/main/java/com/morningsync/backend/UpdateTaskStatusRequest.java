package com.morningsync.backend;

import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(@NotNull Boolean completed) {

}
