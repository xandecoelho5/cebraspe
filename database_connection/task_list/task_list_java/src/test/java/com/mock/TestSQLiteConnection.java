package com.mock;

import com.database.DbConnection;

public class TestSQLiteConnection extends DbConnection {

  @Override
  protected void init() {
    driverClassName = "org.sqlite.JDBC";
    url = "jdbc:sqlite:src/main/resources/task_list_test.db";
    createTableQuery = """
            CREATE TABLE IF NOT EXISTS task(
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              title TEXT NOT NULL,
              description TEXT NOT NULL,
              created_at DATETIME
            );
        """;
  }
}
