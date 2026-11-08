package com.morningsync.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailyReviewController {

    private final DailyReviewService dailyReviewService;

    public DailyReviewController(DailyReviewService dr){
        this.dailyReviewService = dr;
    }

    @GetMapping("/api/daily-review")
    public DailyReviewResponse getDailyReview() {
        return dailyReviewService.getDailyReview();
    }

    @PostMapping("/api/tasks")
    public TaskResponse createTask(@RequestBody CreateTaskRequest request){
         return dailyReviewService.createTask(request);
    }
}