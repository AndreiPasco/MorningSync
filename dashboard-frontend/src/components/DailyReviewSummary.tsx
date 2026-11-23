type DailyReviewSummaryProps = {
    message: string
    taskCount: number
    pendingTaskCount: number
    overdueTaskCount: number
    dueTodayTaskCount: number
}

export default function DailyReviewSummary(
    props: DailyReviewSummaryProps
) {
    return (
        <section>
            <h2>{props.message}</h2>

            <div className="summary-grid">
                <p className="summary-card">
                    <span>Total tasks : </span>
                    <strong>{props.taskCount}</strong>
                </p>

                <p className="summary-card">
                    <span>Pending : </span>
                    <strong>{props.pendingTaskCount}</strong>
                </p>

                <p className="summary-card">
                    <span>Overdue : </span>
                    <strong>{props.overdueTaskCount}</strong>
                </p>

                <p className="summary-card">
                    <span>Due today : </span>
                    <strong>{props.dueTodayTaskCount}</strong>
                </p>
            </div>
        </section>
    )
}