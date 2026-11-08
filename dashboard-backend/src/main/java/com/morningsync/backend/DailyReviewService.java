package com.morningsync.backend;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailyReviewService {

    private long nextId = 3;

    private final List<TaskResponse> tasks = new ArrayList<>(
            List.of(
            new TaskResponse(1L, "Termina proiectul in Java", false ),
            new TaskResponse(2L, "Trimite tema", true)
            )
    );

    public DailyReviewResponse getDailyReview(){

        int pendingTaskCount = 0;
        for(TaskResponse task : tasks){
            if(!task.completed()){
                pendingTaskCount++;
            }
        }

        return new DailyReviewResponse("Buna dimineata", tasks.size(),pendingTaskCount,tasks);
    }

    public TaskResponse createTask(CreateTaskRequest request){
        TaskResponse task = new TaskResponse(nextId++, request.title(), false);
        tasks.add(task);
        return task;
    }

}
