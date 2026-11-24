import type {TaskResponse} from '../types/dailyReview'

type TaskListProps = {
    tasks : TaskResponse[]
}

export default function TaskList(props : TaskListProps){
    return(
        <section>
            <h2> Your tasks </h2>
            {props.tasks.length === 0 ? (
                <p>No tasks yet. Your schedule is clear.</p>
            ) : (
                <ul>
                    {props.tasks.map(task => (
                        <li key={task.id}>
                            <span>{task.title}</span>

                            <span
                                className={`task-status ${
                                    task.completed ? 'is-completed' : 'is-pending'
                                }`}
                            >
                                                {task.completed ? 'Completed' : 'Pending'}
                                        </span>

                            {task.deadline !== null && (
                                <p>
                                    Deadline:{' '}
                                    {new Date(task.deadline).toLocaleString('en-GB', {
                                        dateStyle: 'medium',
                                        timeStyle: 'short',
                                    })}
                                </p>
                            )}
                        </li>
                    ))}
                </ul>
            )}
        </section>
    )
}

