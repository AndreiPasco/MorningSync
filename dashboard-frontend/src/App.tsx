import './App.css'
import DailyReviewSummary from './components/DailyReviewSummary'
import { useEffect, useState } from 'react'
import type { DailyReview } from './types/dailyReview'
import TaskList from './components/TaskList'

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

                    <TaskList tasks={review.list} />
                </>
            )}
        </main>
    )
}

export default App