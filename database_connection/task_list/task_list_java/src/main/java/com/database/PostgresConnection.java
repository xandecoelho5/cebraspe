package com.database;

public class PostgresConnection extends DbConnection {

  @Override
  void init() {
    driverClassName = "org.postgresql.Driver";
    url = "jdbc:postgresql://localhost:5432/task_list";
    user = "postgres";
    password = "postgres";
  }
}
