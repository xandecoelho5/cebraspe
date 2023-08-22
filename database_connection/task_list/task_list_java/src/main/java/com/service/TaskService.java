package com.service;

import java.util.List;
import java.util.Optional;

import com.dto.TaskDto;
import com.model.Task;
import com.repository.TaskRepository;

public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void addNewTask(TaskDto taskDto) {
    Task task = new Task(taskDto);
    taskRepository.insert(task);
  }

  public void updateTask(int id, TaskDto taskDto) {
    var optionalTask = retrieveTaskById(id);
    if (optionalTask.isPresent()) {
      Task task = optionalTask.get();

      task.updateData(taskDto);

      taskRepository.update(id, task);
    }
  }

  public void deleteTaskById(int id) {
    retrieveTaskById(id); // just to verify if task exists

    taskRepository.deleteById(id);
  }

  public Optional<Task> retrieveTaskById(int id) {
    try {
      return Optional.of(taskRepository.findById(id).get());
    } catch (Exception e) {
      System.out.format("Tarefa com id %d nao encontrada.", id);
      return Optional.empty();
    }
  }

  public List<Task> retrieveAllTasks() {
    return taskRepository.findAll();
  }
}
