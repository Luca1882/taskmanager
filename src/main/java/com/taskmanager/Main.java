package com.taskmanager;

import com.taskmanager.model.Priority;
import com.taskmanager.model.TaskStatus;

import com.taskmanager.service.TaskService;
import com.taskmanager.service.TaskServiceImpl;

import java.util.Scanner;

public class Main {

    // Punto di ingresso dell'app
    private static final TaskService taskService = new TaskServiceImpl();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Benvenuto nel Task Manager! ");

        boolean run = true;

        while (run) {
            printMenu();
            String choice = scan.nextLine().trim();

            switch (choice) {
                case "1" -> addTask();
                case "2" -> printAllTasks();
                case "3" -> filterByPriority();
                case "4" -> filterByStatus();
                case "5" -> completeTask();
                case "6" -> deleteTask();
                case "7" -> taskService.printAllTasks();
                case "0" -> run = false;
                default -> System.out.println("Scelta non valida, riprova.");
            }
        }
        System.out.println("Grazie per aver usato il Task Manager. Arrivederci!");
    }

    private static void printMenu() {
        System.out.println(
                """

                                ====== MENU ======
                                    1. Aggiungi task
                                    2. Visualizza tutti
                                    3. Filtra per priorità
                                    4. Filtra per status
                                    5. Completa task
                                    6. Elimina task
                                    7. Sommario
                                    0. Esci
                                ==================
                        Scelta:\s""");
    }

    private static void addTask() {
        System.out.println("Inserisci il titolo: ");
        String title = scan.nextLine().trim();
        System.out.println("Inserisci una descrizione: ");
        String description = scan.nextLine().trim();
        
        Priority priority = null;

        while (priority == null) {
            System.out.println("Scegli la priorità della task (LOW/MEDIUM/HIGH): ");
            String priorityInput = scan.nextLine().trim().toUpperCase();
            try {
                priority = Priority.valueOf(priorityInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Priorità non valida, riprova");
            }
        }

        taskService.addTask(title, description, priority);
    }

    private static void printAllTasks() {
        var tasks = taskService.getAllTasks();
        if (tasks.isEmpty()){
            System.out.println("Non ci sono tasks da visualizzare. ");
            return;
        }

        // Stampo l'intestazione della tabella
        System.out.println("\n📋 ID | Titolo               | Priorità | Status      | Data");
        System.out.println("-".repeat(65));
        tasks.forEach(System.out::println);
    }

    private static void filterByPriority() {
        Priority priority = null;

        while (priority == null) {
            System.out.println("Scegli la priorità della task (LOW/MEDIUM/HIGH): ");
            String priorityInput = scan.nextLine().trim().toUpperCase();
            try {
                priority = Priority.valueOf(priorityInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Priorità non valida, riprova");
            }
        }
        taskService.getTaskByPriority(priority).forEach(System.out::println);
    }

    private static void filterByStatus() {
        TaskStatus status = null;

        while (status == null) {
            System.out.println("Scegli lo status della task (TODO/IN_PROGRESS/DONE): ");
            String statusInput = scan.nextLine().trim().toUpperCase();
            try {
                status = TaskStatus.valueOf(statusInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Status non valido, riprova");
            }
        }
        taskService.getTaskByStatus(status).forEach(System.out::println);
    }

    private static void completeTask() {
        Integer id = null;

        while (id == null) {
            System.out.println("Inserisci l'ID della task da completare: ");
            String idInput = scan.nextLine().trim();
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println("ID non valido, riprova");
            } 
        }
        taskService.completeTask(id);
    }

    private static void deleteTask() {
        Integer id = null;

        while (id == null) {
            System.out.println("Inserisci l'ID della task da eliminare: ");
            String idInput = scan.nextLine().trim();
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println("ID non valido, riprova");
            }
        }
        taskService.deleteTask(id);
    }
}
