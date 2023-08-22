/**
 * Abstract Class DbConnection.
 *
 * @class DbConnection
 * @abstract
 */
class DbConnection {
  constructor() {
    if (this.constructor === DbConnection) {
      throw new Error("Abstract classes can't be instantiated.");
    }
  }

  getConnection() {
    throw new Error("not implemented");
  }

  async close() {
    throw new Error("not implemented");
  }

  async executeQuery(baseQuery, values) {
    throw new Error("not implemented");
  }
}

module.exports = DbConnection;
