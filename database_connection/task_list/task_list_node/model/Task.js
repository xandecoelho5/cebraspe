const TaskDto = require("../dto/TaskDto");

class Task {
  /**
   * @param {int} id
   * @param {string} title
   * @param {string} description
   * @param {Date} createdAt
   */
  constructor(id, title, description, createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
  }

  /**
   * @param {TaskDto} taskDto
   */
  static fromDto(taskDto) {
    return new Task(0, taskDto.title, taskDto.description, new Date());
  }

  static fromObject({ id, title, description, createdAt }) {
    const date = typeof createdAt == "number" ? new Date(createdAt) : createdAt;
    return new Task(id, title, description, date); // new Date(createdAt)
  }

  /**
   * @param {TaskDto} taskDto
   */
  updateData(taskDto) {
    if (taskDto.title) {
      this.title = taskDto.title;
    }
    if (taskDto.description) {
      this.description = taskDto.description;
    }
  }

  toString() {
    return `Título: ${this.title}, Descriçao: ${this.description}, Data de criaçao: ${this.createdAt.toLocaleString()}`;
  }
}

module.exports = Task;
