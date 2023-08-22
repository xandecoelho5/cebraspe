package com.database;

public class MySQLConnection extends DbConnection {

  @Override
  void init() {
    driverClassName = "com.mysql.cj.jdbc.Driver";
    url = "jdbc:mysql://localhost/task_list";
    user = "root";
    password = "root";
  }
}
