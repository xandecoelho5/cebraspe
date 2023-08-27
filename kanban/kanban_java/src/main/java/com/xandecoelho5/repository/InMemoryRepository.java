package com.xandecoelho5.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.xandecoelho5.model.Task;
import com.xandecoelho5.model.TaskStatus;

public class InMemoryRepository {

  private List<Task> tasks = new ArrayList<>();

  public void saveTask(Task task) {
    if (tasks.stream().anyMatch(t -> isSameDescription.test(t, task))) {
      throw new RuntimeException("\nJá existe uma tarefa com essa descrição.");
    }
    tasks.add(task);
  }

  public void updateStatus(Task task) {
    for (int i = 0; i < tasks.size(); i++) {
      if (isSameDescription.test(tasks.get(i), task)) { // tasks.get(i).getDescription().equals(task.getDescription())
        tasks.set(i, task);
        break;
      }
    }
  }

  public void updateStatus(int index, TaskStatus status) {
    try {
      var task = tasks.get(index);
      if (task.getStatus().equals(status)) {
        throw new RuntimeException(
            "\nNao é possível atualizar o STATUS de uma tarefa para o mesmo STATUS que ela já está.");
      }
      task.updateStatus(status);
    } catch (IndexOutOfBoundsException ex) {
      throw new RuntimeException("\nNao existe tarefa para o índice selecionado.");
    }
  }

  BiPredicate<Task, Task> isSameDescription = (t1, t2) -> t1.getDescription().equals(t2.getDescription());

  public List<Task> listAllTasks() {
    return List.copyOf(tasks);
  }

  public List<Task> listTasksByCategory(TaskStatus status) {
    return tasks.stream().filter(t -> t.getStatus().equals(status)).toList();
  }
}
