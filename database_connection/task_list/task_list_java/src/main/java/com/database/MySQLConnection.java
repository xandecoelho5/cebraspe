package com.database;

public class MySQLConnection extends DbConnection {

  @Override
  protected void init() {
    driverClassName = "com.mysql.cj.jdbc.Driver";
    url = "jdbc:mysql://localhost/task_list";
    user = "root";
    password = "root";
    createTableQuery = """
            CREATE TABLE IF NOT EXISTS task(
              id INTEGER NOT NULL AUTO_INCREMENT,
              title VARCHAR(512) NOT NULL,
              description VARCHAR(1024) NOT NULL,
              created_at DATETIME,
              PRIMARY KEY (id)
            );
        """;
  }
}
