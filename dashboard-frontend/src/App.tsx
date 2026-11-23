import './App.css'
import DailyReviewSummary from './components/DailyReviewSummary'
import { useEffect, useState } from 'react'

type TaskResponse = {
    id: number
    title: string
    completed: boolean
    deadline: string | null
}

type DailyReview = {
    message : string
    taskCount : number
    pendingTaskCount : number
    overdueTaskCount : number
    dueTodayTaskCount : number
    list : TaskResponse[]
}

function App(){
    const[loading,setLoading] = useState(true)
    const[review,setReview] = useState<DailyReview | null>(null)
    const[error,setError] = useState<string | null>(null)

    useEffect(() => {
        const controller = new AbortController()

        async function loadReview(){
            try{
                const response = await fetch('/api/daily-review', {
                    signal : controller.signal,
                })
                if(!response.ok){
                    throw new Error('Unable to load your daily overview.')
                }
                const data : DailyReview = await response.json()

                if(!controller.signal.aborted){
                    setReview(data)
                }
            }catch{
                if(!controller.signal.aborted){
                    setError("Unable to load your daily overview. Please reload the page.")
                }
            }finally{
                if(!controller.signal.aborted){
                    setLoading(false)
                }
            }
        }
        void loadReview()

        return () => controller.abort()
    },[])

    return(
        <main>
            <h1> Morning Sync </h1>

            {loading && <p role = "status">Loading your daily overview…</p>}
            {error && <p role ="alert">{error}</p>}

            {review != null && (
                <>
                    <DailyReviewSummary
                        message={review.message}
                        taskCount={review.taskCount}
                        pendingTaskCount={review.pendingTaskCount}
                        overdueTaskCount={review.overdueTaskCount}
                        dueTodayTaskCount={review.dueTodayTaskCount}
                    />

                    <section>
                        <h2> Your tasks </h2>
                        {review.list.length === 0 ? (
                            <p>No tasks yet. Your schedule is clear.</p>
                        ) : (
                            <ul>
                                {review.list.map(task => (
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
                </>
            )}
        </main>
    )
}

export default App