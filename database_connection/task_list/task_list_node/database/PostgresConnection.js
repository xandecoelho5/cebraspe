const { Client } = require("pg");
const DbConnection = require("./DbConnection");

class PostgresConnection extends DbConnection {
  constructor() {
    super();
    this.client = new Client({
      user: "postgres",
      host: "localhost",
      database: "task_list",
      password: "postgres",
      port: 5432,
    });
    this.client.connect();
  }

  async executeQuery(baseQuery, values) {
    try {
      const query = {
        text: this.replaceQueryPlaceholders(baseQuery),
        values,
      };
      const res = await this.client.query(query);
      return res.rows;
    } catch (error) {
      console.log(error);
    }
  }

  async close() {
    await this.client.end();
  }

  replaceQueryPlaceholders(query) {
    const paramsCount = query.split("?").length - 1;
    const placeholders = Array.from({ length: paramsCount }, (_, index) => `$${index + 1}`);
    return query.replace(/\?/g, () => placeholders.shift());
  }
}

module.exports = PostgresConnection;
