package com.xandecoelho5.model;

public class Task {
  private final String description;
  private TaskStatus status;

  public Task(String description) {
    this.description = description;
    this.status = TaskStatus.TODO;
  }

  public String getDescription() {
    return this.description;
  }

  public TaskStatus getStatus() {
    return this.status;
  }

  public String getStatusDescription() {
    return this.status.getDescription();
  }

  public void updateStatus(TaskStatus status) {
    this.status = status;
  }
}
