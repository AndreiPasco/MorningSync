# MorningSync

MorningSync is a personal daily planning application designed to help users start their morning with a clear overview of tasks, deadlines, and priorities.

The project currently contains a Java and Spring Boot backend. A mobile-friendly frontend and external calendar integrations are planned.

## Features

- Create tasks with optional deadlines.
- Mark tasks as completed or pending.
- Store tasks in a persistent H2 database.
- Generate a summary with total, pending, overdue, and due-today task counts.
- Sort tasks by completion status and deadline.
- Validate incoming requests.

A pending task can be both overdue and due today, so these counts may overlap.

## Tech Stack

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA and Hibernate
- Bean Validation
- H2 Database
- Maven Wrapper
- JUnit and Mockito


## Roadmap

- Mobile-friendly user interface
- User accounts and task ownership
- Google Calendar integration
- Explicit time zone handling
- Morning reminders and notifications

## Project Status

Under active development as a learning project focused on backend development, REST APIs, persistence, and automated testing.
