package com.xandecoelho5.service;

import java.util.List;

import com.xandecoelho5.model.Task;
import com.xandecoelho5.model.TaskStatus;
import com.xandecoelho5.repository.InMemoryRepository;

public class TaskService {

  private final InMemoryRepository repository;

  public TaskService(InMemoryRepository repository) {
    this.repository = repository;
  }

  public void createNewTask(String description) {
    Task task = new Task(description);
    repository.saveTask(task);
  }

  public void updateTaskStatus(int index, TaskStatus status) {
    repository.updateStatus(index, status);
  }

  public List<Task> listAllTasks() {
    return repository.listAllTasks();
  }

  public List<Task> listTasksByCategory(TaskStatus status) {
    return repository.listTasksByCategory(status);
  }
}
