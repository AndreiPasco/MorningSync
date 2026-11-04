package com.morningsync.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailyReviewController {

    @GetMapping("/api/daily-review")
    public DailyReviewResponse getDailyReview() {
        List<TaskResponse> tasks = List.of(
                new TaskResponse(1L, "Termina proiectul in Java", false ),
         new TaskResponse(2L, "Trimite tema", true)
        );
        int pendingTaskCount = 0;
        for(TaskResponse task : tasks){
            if(!task.completed()){
               pendingTaskCount++;
            }
        }

        return new DailyReviewResponse("Buna dimineata", tasks.size(),pendingTaskCount,tasks);
    }
}