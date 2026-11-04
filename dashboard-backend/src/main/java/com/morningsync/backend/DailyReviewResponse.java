package com.morningsync.backend;

import java.util.List;

public record DailyReviewResponse(String message, int taskCount,int pendingTaskCount ,List<TaskResponse> list) {

}
