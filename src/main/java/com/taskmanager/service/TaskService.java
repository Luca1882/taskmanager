package com.taskmanager.service;

import java.util.List;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;

public interface TaskService {

    // Interfaccia che definisce i metodi per la gesione delle task
    void addTask(String title, String description, Priority priority);
    
    List<Task> getAllTasks();
    List<Task> getTaskByPriority(Priority priority);
    List<Task> getTaskByStatus(TaskStatus status);
    boolean completeTask(int id);
    boolean deleteTask(int id);
    void printAllTasks();
}
