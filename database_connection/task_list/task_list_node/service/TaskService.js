const TaskDto = require("../dto/TaskDto");
const Task = require("../model/Task");
const TaskRepository = require("../repository/TaskRepository");

/**
 * @class TaskService
 */
class TaskService {
  /**
   * @param {TaskRepository} taskRepository
   */
  constructor(taskRepository) {
    this.taskRepository = taskRepository;
  }

  /**
   * @param {TaskDto} taskDto
   */
  async addNewTask(taskDto) {
    const task = Task.fromDto(taskDto);
    await this.taskRepository.insert(task);
  }

  /**
   * @param {number} id
   * @param {TaskDto} taskDto
   */
  async updateTask(id, taskDto) {
    const task = await this.retrieveTaskById(id);
    task.updateData(taskDto);
    await this.taskRepository.update(id, task);
  }

  /**
   * @param {number} id
   */
  async deleteTaskById(id) {
    await this.retrieveTaskById(id); // just to verify if task exists
    await this.taskRepository.deleteById(id);
  }

  /**
   * @param {number} id
   * @returns {Task}
   */
  async retrieveTaskById(id) {
    return await this.taskRepository.findById(id);
  }

  /**
   * @returns {Task[]}
   */
  async retrieveAllTasks() {
    return await this.taskRepository.findAll();
  }
}

module.exports = TaskService;
