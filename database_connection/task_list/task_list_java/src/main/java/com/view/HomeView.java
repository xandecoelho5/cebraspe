package com.view;

import java.util.List;

import com.dto.TaskDto;
import com.model.Task;
import com.service.TaskService;
import com.utils.MyScanner;

public class HomeView {

  private final MyScanner scanner;
  private final TaskService taskService;

  public HomeView(MyScanner scanner, TaskService taskService) {
    this.scanner = scanner;
    this.taskService = taskService;
  }

  public void start() {
    System.out.println("""
        \nSelecione uma opçao:
        1. Criar nova tarefa
        2. Visualizar tarefas
        3. Atualizar tarefa
        4. Excluir tarefa
        0. Sair """);
    var optionalOption = scanner.readInt();
    handleOption(optionalOption.orElse(5));
  }

  public void handleOption(int option) {
    switch (option) {
      case 1 -> onCreateNewTask();
      case 2 -> onShowTasks();
      case 3 -> onUpdateTask();
      case 4 -> onDeleteTask();
      case 0 -> onExit();
      default -> System.out.println("Opçao inválida. Selecione outra.");
    }
    start();
  }

  private void onCreateNewTask() {
    System.out.print("\nInforme o título da tarefa: ");
    String title = scanner.readLine();

    System.out.print("Informe a descriçao da tarefa: ");
    String description = scanner.readLine();

    taskService.addNewTask(new TaskDto(title, description));

    System.out.println("\nTarefa cadastrada com sucesso!");
  }

  private void onShowTasks() {
    List<Task> tasks = taskService.retrieveAllTasks();

    if (!tasks.isEmpty()) {
      tasks.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
      System.out.println("\nTarefas cadastradas: ");
      tasks.forEach(task -> System.out.format("Tarefa %d -> %s \n", task.getId(), task.toString()));
    } else {
      System.out.println("\nNao há nenhuma tarefa cadastrada.");
    }
  }

  private void onUpdateTask() {
    int selectedTask = selectATask("alterar") - 1;

    System.out.println("\nInforme os dados para alteraçao, caso nao queira mudar, apenas deixe em branco: ");

    System.out.print("\nInforme o título da tarefa: ");
    String title = scanner.readLine();

    System.out.print("Informe a descriçao da tarefa: ");
    String description = scanner.readLine();

    taskService.updateTask(selectedTask, new TaskDto(title, description));

    System.out.println("Tarefa alterada com sucesso!");
  }

  private void onDeleteTask() {
    int selectedTask = selectATask("deletar");

    System.out.println("\nTem certeza que deseja excluir a tarefa " + selectedTask + "? (S/N)");
    String answer = scanner.readLine();

    if (answer.toLowerCase().equals("s")) {
      taskService.deleteTaskById(selectedTask);
      System.out.println("Tarefa deletada com sucesso!");
    } else if (answer.toLowerCase().equals("n")) {
      System.out.println("Tarefa não deletada");
    } else {
      System.out.println("Opçao inválida.");
    }
  }

  private int selectATask(String operation) {
    System.out.println("\nQual tarefa deseja " + operation + "? (Escolha de acordo com o número de cada tarefa)");
    var selectedTaskIndex = scanner.readInt().orElse(-1);
    taskService.retrieveTaskById(selectedTaskIndex); // throws exception if index is out of bounds
    return selectedTaskIndex;
  }

  private void onExit() {
    System.out.println("Obrigado pela preferência.");
    System.exit(0);
  }
}
