package com.morningsync.backend;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TimeConfiguration {

    Clock clock = Clock.fixed(
            Instant.parse("2026-11-10T10:00:00Z"),
            ZoneId.of("Europe/Bucharest")
    );
}
