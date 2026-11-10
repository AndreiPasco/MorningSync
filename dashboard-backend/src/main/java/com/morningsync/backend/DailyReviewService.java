    package com.morningsync.backend;

    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.time.Clock;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.List;

    @Service
    public class DailyReviewService {

        private final TaskRepository taskRepository;

        private final Clock clock;

        public DailyReviewService(TaskRepository tp, Clock clock){
            this.taskRepository = tp; this.clock = clock;
        }

        private TaskResponse toResponse(Task task){
            return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted(), task.getDeadline());
        }


        public DailyReviewResponse getDailyReview(){

            List<Task> savedTasks = taskRepository.findAll();
            List<TaskResponse> tasks = new ArrayList<>();

            int pendingTaskCount = 0;
            int overdueTaskCount = 0;
            int dueTodayTaskCount = 0;
            LocalDateTime now = LocalDateTime.now(clock);
            LocalDate today = now.toLocalDate();

           for(Task task : savedTasks){

               tasks.add(toResponse(task));

               if(!task.isCompleted() && task.getDeadline()!=null && task.getDeadline().isBefore(now)){
                   overdueTaskCount++;
               }

               if(!task.isCompleted() && task.getDeadline() != null && task.getDeadline().toLocalDate().equals(today)){
                   dueTodayTaskCount++;
               }

               if(!task.isCompleted()){
                   pendingTaskCount++;
               }
           }
           tasks.sort(
                Comparator.comparing(TaskResponse::completed)
                        .thenComparing(
                              TaskResponse::deadline,
                                Comparator.nullsLast(Comparator.naturalOrder())

                        )
           );

            return new DailyReviewResponse("Buna dimineata", tasks.size(),pendingTaskCount,overdueTaskCount,dueTodayTaskCount,tasks);
        }

        public TaskResponse createTask(CreateTaskRequest request){
            Task task = new Task(request.title(), false,request.deadline());
            Task savedTask = taskRepository.save(task);

            return toResponse(savedTask);
        }

        public TaskResponse updateTaskStatus(Long id, UpdateTaskStatusRequest request){
            Task task =  taskRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Taskul nu exista"
                    ));
            task.changeStatus(request.completed());
            Task savedTask = taskRepository.save(task);

            return toResponse(savedTask);
        }

    }
