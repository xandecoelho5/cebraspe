package com.xandecoelho5.model;

public enum TaskStatus {
  TODO("PARA FAZER"),
  WORKING("TRABALHANDO"),
  WAITING("ESPERANDO"),
  DONE("FEITO");

  private final String description;

  private TaskStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public static TaskStatus fromString(String status) {
    try {
      for (var taskStatus : TaskStatus.values()) {
        if (taskStatus.description.toLowerCase().equals(status.toLowerCase())) {
          return taskStatus;
        }
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }
    throw new RuntimeException("Status inválido");
  }

  public static TaskStatus fromIndex(int index) {
    try {
      return TaskStatus.values()[index];
    } catch (Exception ex) {
      throw new RuntimeException("Status inválido");
    }
  }

  public static String showOptions() {
    StringBuilder sb = new StringBuilder();

    for (var taskStatus : TaskStatus.values()) {
      sb.append(String.format("%d. %s", taskStatus.ordinal() + 1, taskStatus.description))
          .append("\n");
    }
    sb.setCharAt(sb.length() - 1, ' ');
    return sb.toString();
  }
}
