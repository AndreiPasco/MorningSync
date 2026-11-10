package com.morningsync.backend;

import java.time.LocalDateTime;

public record TaskResponse(long id, String title, boolean completed, LocalDateTime deadline) {

}
