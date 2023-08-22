const Task = require("../model/Task");
const { readFromFile, writeObjectToFile } = require("../utils/FileHandler");
const TaskRepository = require("./TaskRepository");

class FileTaskRepository extends TaskRepository {
  constructor() {
    super();
  }

  /**
   * @param {Task} task
   */
  insert(task) {
    const tasks = this.findAll();
    task.id = !tasks.length ? 1 : tasks[tasks.length - 1].id + 1;
    tasks.push(task);
    writeObjectToFile(tasks);
  }

  /**
   * @param {number} id
   * @param {Task} task
   */
  update(id, task) {
    const tasks = this.findAll();
    tasks[id - 1] = task;
    writeObjectToFile(tasks);
  }

  /**
   * @param {number} id
   */
  deleteById(id) {
    const tasks = this.findAll();
    tasks.splice(id - 1, 1);
    writeObjectToFile(tasks);
  }

  /**
   * @param {number} id
   */
  findById(id) {
    const tasks = this.findAll();
    const task = tasks[id - 1];
    return Task.fromObject(task);
  }

  /**
   * @returns {Task[]}
   */
  findAll() {
    const tasks = readFromFile();
    return tasks.map((task) => Task.fromObject(task));
  }
}

module.exports = FileTaskRepository;
