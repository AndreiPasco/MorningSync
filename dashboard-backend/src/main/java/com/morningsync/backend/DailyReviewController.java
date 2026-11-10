package com.morningsync.backend;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public TaskResponse createTask( @Valid @RequestBody CreateTaskRequest request){
         return dailyReviewService.createTask(request);
    }
    
    @PatchMapping("/api/tasks/{id}")
    public TaskResponse updateTaskStatus(@PathVariable("id") Long id, @Valid @RequestBody UpdateTaskStatusRequest request){
        return dailyReviewService.updateTaskStatus(id,request);
    }
}