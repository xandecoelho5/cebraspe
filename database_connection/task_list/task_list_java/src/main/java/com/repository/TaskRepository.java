package com.repository;

import java.util.List;
import java.util.Optional;

import com.model.Task;

public interface TaskRepository {
  void insert(Task task);

  void update(int id, Task task);

  void deleteById(int id);

  Optional<Task> findById(int id);

  List<Task> findAll();
}
