package com.morningsync.backend;

import java.util.List;

public record DailyReviewResponse(
        String message,
        int taskCount,
        int pendingTaskCount,
        int overdueTaskCount,
        int dueTodayTaskCount,
        List<TaskResponse> list
) {}