import './App.css'
import DailyReviewSummary from './components/DailyReviewSummary'
import  {useState} from 'react'

type DailyReview = {
  message: string
  taskCount: number
  pendingTaskCount: number
}


function App() {
  const[review, setReview] = useState<DailyReview>({
      message : "Buna dimineata!",
      taskCount : 5,
      pendingTaskCount : 3,
  })
    const[error,setError] = useState<string | null>(null)

    async function loadReview(){
     setError(null)
        try{
         const response = await fetch('/api/daily-review')
            if(!response.ok){
                throw new Error('Nu am putut incarca rezumatul!')
            }

            const data: DailyReview = await response.json()
            setReview(data)
        }catch{
         setError('Nu am putut incarca rezumatul. Incearca din nou!')
        }
  }

  return (
      <main>
        <h1>MorningSync</h1>
      <DailyReviewSummary
        message={review.message}
        taskCount={review.taskCount}
        pendingTaskCount={review.pendingTaskCount}
      />
          <button onClick={loadReview}>Incarca rezumatul</button>
          {error && <p role = "alert">{error}</p>}
      </main>
  )
}

export default App