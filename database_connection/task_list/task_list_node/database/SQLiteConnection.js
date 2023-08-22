const sqlite3 = require("sqlite3").verbose();
const Promise = require("bluebird");
const DbConnection = require("./DbConnection");

class SQLiteConnection extends DbConnection {
  constructor() {
    super();
    this.init();
  }

  async init() {
    this.db = new sqlite3.Database("./resources/task_list.db", (err) => {
      if (err) {
        console.error(err.message);
      } else {
        console.log("Connected to the task_list database.");
      }
    });
    await this.executeQuery(`
    CREATE TABLE IF NOT EXISTS task(
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      title TEXT NOT NULL,
      description TEXT NOT NULL,
      created_at DATETIME
    );
    `);
  }

  async executeQuery(baseQuery, values) {
    if (baseQuery.startsWith("SELECT")) return this.all(baseQuery, values);

    return new Promise((resolve, reject) => {
      this.db.run(baseQuery, values, function (err, row) {
        if (err) {
          console.log("Error running sql " + baseQuery);
          console.log(err);
          reject(err);
        } else {
          resolve({ id: this.lastID });
        }
      });
    });
  }

  all(sql, params = []) {
    return new Promise((resolve, reject) => {
      this.db.all(sql, params, (err, rows) => {
        if (err) {
          console.log("Error running sql: " + sql);
          console.log(err);
          reject(err);
        } else {
          resolve(rows);
        }
      });
    });
  }

  async close() {
    await this.db.close((err) => {
      if (err) {
        return console.error(err.message);
      }
      console.log("Close the database connection.");
    });
  }
}

module.exports = SQLiteConnection;
