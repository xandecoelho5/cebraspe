const { Task } = require("../model/Task");

/**
 * Abstract Class TaskRepository.
 *
 * @class TaskRepository
 * @abstract
 */
class TaskRepository {
  constructor() {
    if (this.constructor === TaskRepository) {
      throw new Error("Abstract classes can't be instantiated.");
    }
  }

  /**
   * @param {Task} task
   */
  insert(task) {
    throw new Error("Method must be implemented.");
  }

  /**
   * @param {number} id
   * @param {Task} task
   */
  update(id, task) {
    throw new Error("Method must be implemented.");
  }

  /**
   * @param {number} id
   */
  deleteById(id) {
    throw new Error("Method must be implemented.");
  }

  /**
   * @param {number} id
   * @returns {Task}
   */
  findById(id) {
    throw new Error("Method must be implemented.");
  }

  /**
   * @returns {Task[]}
   */
  findAll() {
    throw new Error("Method must be implemented.");
  }
}

module.exports = TaskRepository;
