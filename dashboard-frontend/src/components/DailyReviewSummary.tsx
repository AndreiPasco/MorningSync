type DailyReviewSummaryProps = {
    message: string
    taskCount: number
    pendingTaskCount: number
}

export default function DailyReviewSummary(
    props: DailyReviewSummaryProps
) {
    return (
        <section>
            <h2>{props.message}</h2>
            <p>Total taskuri: {props.taskCount}</p>
            <p>Taskuri neterminate: {props.pendingTaskCount}</p>
        </section>
    )
}