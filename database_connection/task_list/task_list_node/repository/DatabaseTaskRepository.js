const DbConnection = require("../database/DbConnection");
const Task = require("../model/Task");
const TaskRepository = require("./TaskRepository");

class DatabaseTaskRepository extends TaskRepository {
  insertQuery = "INSERT INTO task(title, description, created_at) VALUES(?, ?, ?)";
  updateQuery = "UPDATE task SET title = ?, description = ? WHERE id = ?";
  deleteQuery = "DELETE FROM task WHERE id = ?";
  findAllQuery = "SELECT * FROM task";
  findByIdQuery = "SELECT * FROM task WHERE id = ?";

  /**
   *
   * @param {DbConnection} dbConnection
   */
  constructor(dbConnection) {
    super();
    this.dbConnection = dbConnection;
  }

  /**
   * @param {Task} task
   */
  async insert(task) {
    await this.dbConnection.executeQuery(this.insertQuery, [task.title, task.description, task.createdAt]);
  }

  /**
   * @param {number} id
   * @param {Task} task
   */
  async update(id, task) {
    await this.dbConnection.executeQuery(this.updateQuery, [task.title, task.description, id]);
  }

  /**
   * @param {number} id
   */
  async deleteById(id) {
    await this.dbConnection.executeQuery(this.deleteQuery, [id]);
  }

  /**
   * @param {number} id
   * @returns {Task}
   */
  async findById(id) {
    const rows = await this.dbConnection.executeQuery(this.findByIdQuery, [id]);
    return Task.fromObject(this.parseTask(rows[0]));
  }

  /**
   * @returns {Task[]}
   */
  async findAll() {
    const rows = await this.dbConnection.executeQuery(this.findAllQuery, []);
    return rows.map((task) => Task.fromObject(this.parseTask(task)));
  }

  parseTask(task) {
    return { id: task.id, title: task.title, description: task.description, createdAt: task.created_at };
  }
}

module.exports = DatabaseTaskRepository;
