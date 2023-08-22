const mysql = require("mysql2/promise");
const DbConnection = require("./DbConnection");

class MySqlConnection extends DbConnection {
  constructor() {
    super();
    this.init();
  }

  async init() {
    this.connection = await mysql.createConnection({
      host: "localhost",
      user: "root",
      password: "root",
      database: "task_list",
    });
    this.connection.connect();
  }

  async executeQuery(baseQuery, values) {
    try {
      const [rows] = await this.connection.execute(baseQuery, values);
      return rows;
    } catch (error) {
      console.log(error);
    }
  }

  async close() {
    this.connection.end();
  }
}

module.exports = MySqlConnection;
