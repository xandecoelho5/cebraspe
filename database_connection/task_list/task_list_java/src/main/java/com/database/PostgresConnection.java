package com.database;

public class PostgresConnection extends DbConnection {

  @Override
  protected void init() {
    driverClassName = "org.postgresql.Driver";
    url = "jdbc:postgresql://localhost:5432/task_list";
    user = "postgres";
    password = "postgres";
    createTableQuery = """
            CREATE TABLE IF NOT EXISTS task(
              id SERIAL PRIMARY KEY,
              title VARCHAR(512) NOT NULL,
              description VARCHAR(1024) NOT NULL,
              created_at TIMESTAMP
            );
        """;
  }
}
