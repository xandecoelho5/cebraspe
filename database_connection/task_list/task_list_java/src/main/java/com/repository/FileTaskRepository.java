package com.repository;

import java.util.List;
import java.util.Optional;

import com.model.Task;
import com.utils.FileHandler;

public class FileTaskRepository implements TaskRepository {

  private final FileHandler fileHandler;

  public FileTaskRepository(FileHandler fileHandler) {
    this.fileHandler = fileHandler;
  }

  @Override
  public void insert(Task task) {
    var tasks = findAll();
    task.setId(tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1);
    tasks.add(task);
    fileHandler.writeObjectToFile(tasks);
  }

  @Override
  public void update(int id, Task task) {
    var tasks = findAll();
    tasks.set(id - 1, task);
    fileHandler.writeObjectToFile(tasks);
  }

  @Override
  public void deleteById(int id) {
    var tasks = findAll();
    tasks.remove(id - 1);
    fileHandler.writeObjectToFile(tasks);
  }

  @Override
  public Optional<Task> findById(int id) {
    var tasks = findAll();
    return Optional.ofNullable(tasks.get(id - 1));
  }

  @Override
  public List<Task> findAll() {
    return fileHandler.readObjectsFromFile();
  }
}
