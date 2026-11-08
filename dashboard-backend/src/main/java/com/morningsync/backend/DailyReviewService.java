package com.morningsync.backend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailyReviewService {

    private final TaskRepository taskRepository;

    public DailyReviewService(TaskRepository tp){
        this.taskRepository = tp;
    }

    public DailyReviewResponse getDailyReview(){

        List<Task> savedTasks = taskRepository.findAll();
        List<TaskResponse> tasks = new ArrayList<>();

        int pendingTaskCount = 0;

       for(Task task : savedTasks){
           TaskResponse response = new TaskResponse(
                   task.getId(),
                   task.getTitle(),
                   task.isCompleted()
           );
           tasks.add(response);

           if(!task.isCompleted()){
               pendingTaskCount++;
           }
       }

        return new DailyReviewResponse("Buna dimineata", tasks.size(),pendingTaskCount,tasks);
    }

    public TaskResponse createTask(CreateTaskRequest request){
        Task task = new Task(request.title(), false);
        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.isCompleted()
        );
    }

}
