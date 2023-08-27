package com.xandecoelho5;

import java.util.Scanner;

import com.xandecoelho5.model.TaskStatus;
import com.xandecoelho5.repository.InMemoryRepository;
import com.xandecoelho5.service.TaskService;

public class Main {

  private static Scanner sc = new Scanner(System.in);
  private static TaskService service = new TaskService(new InMemoryRepository());

  public static void main(String[] args) {
    try {
      start();
    } finally {
      sc.close();
    }
  }

  private static void start() {
    System.out.print("""
        \nEscolha uma opçao:
        1. Cadastrar uma tarefa
        2. Atualizar status da tarefa
        3. Listar tarefas
        4. Listar tarefas por categoria
        0. Sair
        """);
    int option = sc.nextInt();
    sc.nextLine();
    handleOption(option);
  }

  private static void handleOption(int option) {
    switch (option) {
      case 1 -> onCreateNewTask();
      case 2 -> onUpdateATaskStatus();
      case 3 -> onListAllTasks();
      case 4 -> onListTasksByCategory();
      case 0 -> onExit();
      default -> System.out.println("Opcao inválida.");
    }
    start();
  }

  private static void onCreateNewTask() {
    try {
      System.out.println("\nQual a descricao da tarefa?");
      String description = sc.nextLine();

      service.createNewTask(description);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private static void onUpdateATaskStatus() {
    try {
      System.out
          .println("\nQual tarefa você deseja atualizar o status? (Escolha de acordo com a ordem da mesma na lista)");

      int taskIndex = sc.nextInt();
      sc.nextLine();

      var taskStatus = chooseANewStatus(
          "\nPara qual status deseja mover sua tarefa? Informe o nome ou número da mesma");

      service.updateTaskStatus(taskIndex - 1, taskStatus);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private static TaskStatus chooseANewStatus(String question) {
    try {
      System.out.println(question);
      System.out.println(TaskStatus.showOptions());

      String status = sc.nextLine();

      if (status.length() == 1) { // pode ser que tenha sido escolhido um número
        return TaskStatus.fromIndex(Integer.parseInt(status) - 1);
      }
      return TaskStatus.fromString(status);
    } catch (Exception ex) {
      System.out.println(ex);
      chooseANewStatus(question);
    }
    return null;
  }

  private static void onListAllTasks() {
    var tasks = service.listAllTasks();

    if (tasks.isEmpty()) {
      System.out.println("\nNenhuma tarefa cadastrada");
      return;
    }

    System.out.println("\n------------------------ Lista de Tarefas ------------------------");
    for (int i = 0; i < tasks.size(); i++) {
      var task = tasks.get(i);
      System.out
          .println(String.format("Tarefa %d: %s -> %s", i + 1, task.getDescription(), task.getStatusDescription()));
    }
  }

  private static void onListTasksByCategory() {
    var taskStatus = chooseANewStatus("\nInforme o nome ou número da categoria: ");
    var tasks = service.listTasksByCategory(taskStatus);

    if (tasks.isEmpty()) {
      System.out.println("\nNenhuma tarefa cadastrada para esta categoria");
      return;
    }

    System.out.println("\n------------- Lista de Tarefas -> " + taskStatus.getDescription() + " -----------------");
    for (int i = 0; i < tasks.size(); i++) {
      var task = tasks.get(i);
      System.out.println(String.format("Tarefa %d: %s", i + 1, task.getDescription()));
    }
  }

  private static void onExit() {
    System.exit(1);
  }
}