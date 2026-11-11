import './App.css'

type DailyReview = {
  message: string
  taskCount: number
  pendingTaskCount: number
}

function App() {
  const review: DailyReview= {
    message: 'Bună dimineața!',
    taskCount: 5,
    pendingTaskCount: 3,
  }
  return (
      <main>
        <h1>MorningSync</h1>
        <p>{review.message}</p>
        <p>Total taskuri: {review.taskCount}</p>
        <p>Taskuri neterminate: {review.pendingTaskCount}</p>
      </main>
  )
}

export default App