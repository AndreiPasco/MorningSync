export type TaskResponse = {
    id: number
    title: string
    completed: boolean
    deadline: string | null
}

export type DailyReview = {
    message : string
    taskCount : number
    pendingTaskCount : number
    overdueTaskCount : number
    dueTodayTaskCount : number
    list : TaskResponse[]
}   