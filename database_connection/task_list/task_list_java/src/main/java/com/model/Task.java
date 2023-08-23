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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Task other = (Task) obj;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Título: " + title + ", Descriçao: " + description + ", Data de criaçao: " + DateUtils.format(createdAt);
  }
}