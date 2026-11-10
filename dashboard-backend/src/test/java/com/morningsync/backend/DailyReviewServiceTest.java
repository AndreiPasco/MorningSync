package com.morningsync.backend;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static org.mockito.Mockito.when;

public class DailyReviewServiceTest {

    @Test
    void shouldReturnEmptyReviewWhenNoTasksExist() {
        TaskRepository repository = mock(TaskRepository.class);

        Clock clock = Clock.fixed(
                Instant.parse("2026-11-10T10:00:00Z"),
                ZoneId.of("Europe/Bucharest")
        );
        DailyReviewService service = new DailyReviewService(repository, clock);

        when(repository.findAll()).thenReturn(List.of());
        DailyReviewResponse result = service.getDailyReview();

        assertEquals(0, result.taskCount());
        assertEquals(0, result.pendingTaskCount());
        assertEquals(0, result.overdueTaskCount());
        assertEquals(0, result.dueTodayTaskCount());
        assertTrue(result.list().isEmpty());
    }

    @Test
    void shouldCountPendingTaskWithoutDeadline(){

        TaskRepository repository = mock(TaskRepository.class);

        Clock clock = Clock.fixed(
                Instant.parse("2026-11-10T10:00:00Z"),
                ZoneId.of("Europe/Bucharest")
        );
        DailyReviewService service = new DailyReviewService(repository, clock);

        Task task = new Task("Invata Spring", false, null);
        ReflectionTestUtils.setField(task, "id", 1L);
        when(repository.findAll()).thenReturn(List.of(task));

        DailyReviewResponse result = service.getDailyReview();

        assertEquals(1, result.taskCount());
        assertEquals(1, result.pendingTaskCount());
        assertEquals(0, result.overdueTaskCount());
        assertEquals(0, result.dueTodayTaskCount());
        assertEquals(1, result.list().size());
    }


    @Test
    void shouldCountOverdueTaskDueToday(){

        TaskRepository repository = mock(TaskRepository.class);

        Clock clock = Clock.fixed(
                Instant.parse("2026-11-10T10:00:00Z"),
                ZoneId.of("Europe/Bucharest")
        );
        DailyReviewService service = new DailyReviewService(repository, clock);

        LocalDateTime deadline = LocalDateTime.of(2026, 11, 10, 9, 0);

        Task task = new Task("TestOverdue", false, deadline);

        ReflectionTestUtils.setField(task, "id", 1L);
        when(repository.findAll()).thenReturn(List.of(task));

        DailyReviewResponse result = service.getDailyReview();

        assertEquals(1, result.taskCount());
        assertEquals(1, result.pendingTaskCount());
        assertEquals(1, result.overdueTaskCount());
        assertEquals(1, result.dueTodayTaskCount());
    }

    @Test
    void shouldExcludeCompletedTaskFromPendingCounts() {
        TaskRepository repository = mock(TaskRepository.class);

        Clock clock = Clock.fixed(
                Instant.parse("2026-11-10T10:00:00Z"),
                ZoneId.of("Europe/Bucharest")
        );
        DailyReviewService service = new DailyReviewService(repository, clock);

        LocalDateTime deadline = LocalDateTime.of(2026, 11, 10, 9, 0);
        Task task = new Task("Task finalizat", true, deadline);

        ReflectionTestUtils.setField(task, "id", 1L);
        when(repository.findAll()).thenReturn(List.of(task));

        DailyReviewResponse result = service.getDailyReview();

        assertEquals(1, result.taskCount());
        assertEquals(0, result.pendingTaskCount());
        assertEquals(0, result.overdueTaskCount());
        assertEquals(0, result.dueTodayTaskCount());
    }

    @Test
    void shouldSortPendingTasksBeforeCompletedTasks() {
        TaskRepository repository = mock(TaskRepository.class);

        Clock clock = Clock.fixed(
                Instant.parse("2026-11-10T10:00:00Z"),
                ZoneId.of("Europe/Bucharest")
        );
        DailyReviewService service = new DailyReviewService(repository, clock);

        Task completed = new Task(
                "Task finalizat", true,
                LocalDateTime.of(2026, 11, 10, 8, 0)
        );
        Task withoutDeadline = new Task("Fara deadline", false, null);
        Task withDeadline = new Task(
                "Cu deadline", false,
                LocalDateTime.of(2026, 11, 10, 18, 0)
        );

        ReflectionTestUtils.setField(completed, "id", 1L);
        ReflectionTestUtils.setField(withoutDeadline, "id", 2L);
        ReflectionTestUtils.setField(withDeadline, "id", 3L);

        when(repository.findAll()).thenReturn(
                List.of(completed, withoutDeadline, withDeadline)
        );

        DailyReviewResponse result = service.getDailyReview();

        List<Long> actualIds = result.list().stream()
                .map(TaskResponse::id)
                .toList();

        assertEquals(List.of(3L, 2L, 1L), actualIds);
    }
}
