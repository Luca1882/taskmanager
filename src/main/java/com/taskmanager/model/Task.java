package com.taskmanager.model;

import java.time.LocalDate;

public class Task {

    // Definisco uno static per tenere conto dell'id incrementale

    private static int counter = 0;

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate createdAt;

    // Genero un costruttore per inizializzare le task e assegno uno stato di
    // default
    public Task(String title, String description, Priority priority) {
        this.id = ++counter;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDate.now();
    }

    // Getters
    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Setters solo per lo Status
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    // Override del metodo toString per una formattazione delle task leggibile
    @Override
    public String toString() {
        return String.format("[%d] %-20s | %-10s | %-12s | %s",
                id, title, priority, status, createdAt);
    }
}
