package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dto.TaskDto;
import com.mock.TestSQLiteConnection;
import com.model.Task;
import com.repository.DatabaseRepository;
import com.repository.TaskRepository;

public class TaskServiceTest {

  private static TaskService taskService;
  private static TaskRepository taskRepository;
  private static final TaskDto taskDto = new TaskDto("teste", "testeSapeoca");

  @BeforeAll
  static void setup() {
    var connection = new TestSQLiteConnection();
    taskRepository = new DatabaseRepository(connection);
    taskService = new TaskService(taskRepository);
  }

  @AfterEach
  void tearDown() {
    var tasks = taskRepository.findAll();
    for (var task : tasks) {
      taskRepository.deleteById(task.getId());
    }
  }

  @Test
  void shouldAddANewTask() {
    Task task = new Task(taskDto);

    taskService.addNewTask(taskDto);

    var tasks = taskRepository.findAll();
    var savedTask = tasks.get(0);

    assertEquals(1, tasks.size());
    assertEquals(task.getTitle(), savedTask.getTitle());
    assertEquals(task.getDescription(), savedTask.getDescription());
  }

  @Test
  void shouldDeleteATaskById() {
    taskService.addNewTask(taskDto);

    // workaround to get the id, without changing the sequence table
    var task = taskRepository.findAll().get(0);

    taskService.deleteTaskById(task.getId());

    assertEquals(0, taskRepository.findAll().size());
  }

  @Test
  void shouldReturnAllTasks() {
    taskService.addNewTask(new TaskDto("teste", "testeSapeoca"));
    taskService.addNewTask(new TaskDto("teste2", "testeSapeoca2"));
    taskService.addNewTask(new TaskDto("teste3", "testeSapeoca3"));

    assertEquals(3, taskRepository.findAll().size());
  }

  @Test
  void shouldReturnATaskByItsId() {
    taskService.addNewTask(taskDto);

    // workaround to get the id, without changing the sequence table
    var task = taskRepository.findAll().get(0);

    var taskById = taskService.retrieveTaskById(task.getId()).get();

    assertEquals(taskDto.title(), taskById.getTitle());
    assertEquals(taskDto.description(), taskById.getDescription());
  }

  @Test
  void shouldReturnEmptyWithGivenAInvalidId() {
    taskService.addNewTask(taskDto);

    var task = taskService.retrieveTaskById(10);

    assertTrue(task.isEmpty());
  }

  @Test
  void shouldUpdateATask() {
    taskService.addNewTask(taskDto);

    var savedTask = taskRepository.findAll().get(0);

    TaskDto updatedDto = new TaskDto("fofo", "naotroca");

    taskService.updateTask(savedTask.getId(), updatedDto);

    savedTask = taskRepository.findAll().get(0);

    assertEquals(updatedDto.title(), savedTask.getTitle());
    assertEquals(updatedDto.description(), savedTask.getDescription());
  }
}
