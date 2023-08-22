package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.dto.TaskDto;
import com.utils.DateUtils;

public class Task implements Serializable {
  private int id;
  private String title;
  private String description;
  private LocalDateTime createdAt;

  public Task(int id, String title, String description, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
  }

  public Task(TaskDto taskDto) {
    this.title = taskDto.title();
    this.description = taskDto.description();
    this.createdAt = LocalDateTime.now();
  }

  public void updateData(TaskDto taskDto) {
    if (taskDto.title() != null) {
      this.title = taskDto.title();
    }
    if (taskDto.description() != null) {
      this.description = taskDto.description();
    }
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Título: " + title + ", Descriçao: " + description + ", Data de criaçao: " + DateUtils.format(createdAt);
  }
}