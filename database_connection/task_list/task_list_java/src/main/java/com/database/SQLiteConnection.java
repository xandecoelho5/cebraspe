package com.database;

public class SQLiteConnection extends DbConnection {

  @Override
  void init() {
    driverClassName = "org.sqlite.JDBC";
    url = "jdbc:sqlite:src/main/resources/task_list.db";
  }
}
