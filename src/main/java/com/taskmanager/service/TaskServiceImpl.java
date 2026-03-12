package com.taskmanager.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;

public class TaskServiceImpl implements TaskService {

    // Creo un'implementazione Stream API per gestire le task

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(String title, String description, Priority priority) {
        tasks.add(new Task(title, description, priority));
        System.out.println("Task aggiunta con successo!");
    }

    @Override
    public List<Task> getAllTasks() {
        // Stream per ordinare tutte le task in base alla priorità e all'id
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed()
                        .thenComparing(Task::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTaskByPriority(Priority priority) {
        // Stram per filtrare le task in base alla priorità
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTaskByStatus(TaskStatus status) {
        // Straem per filtrare le task in base allo stato
        return tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public boolean completeTask(int id) {
        // Stream per trovare la task tramite ID e restituire Optional
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .map(t -> {
                    t.setStatus(TaskStatus.DONE);
                    System.out.println("Task: " + t.getTitle() + " Completata!");
                    return true;
                })
                .orElseGet(() -> {
                    System.out.println("La task con ID " + id + " non è stata trovata.");
                    return false;
                });
    }

    @Override
    public boolean deleteTask(int id) {
        // Qui utilizzo il removeIf per cancellare la task in base all'ID
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        System.out.println(removed ? "Task con ID " + id + " Cancellata!"
                : "La task con ID " + id + " non e stata trovata.");
        return removed;
    }

    @Override
    public void printAllTasks() {
        // Stream per il Counter delle task
        long todo = tasks.stream().filter(t -> t.getStatus() == TaskStatus.TODO).count();
        long inProgress = tasks.stream().filter(t -> t.getStatus() == TaskStatus.IN_PROGRESS).count();
        long done = tasks.stream().filter(t -> t.getStatus() == TaskStatus.DONE).count();

        System.out.println("\n --- SOMMARIO ---");
        System.out.println("TODO: " + todo);
        System.out.println("IN_PROGRESS: " + inProgress);
        System.out.println("DONE: " + done);
        System.out.println("TOTALE: " + tasks.size());
    }

}
