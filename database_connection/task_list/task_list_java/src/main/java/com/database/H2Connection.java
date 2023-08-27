package com.database;

public class H2Connection extends DbConnection {

  @Override
  protected void init() {
    driverClassName = "org.h2.Driver";
    url = "jdbc:h2:~/test"; // jdbc:h2:mem:task_list
    user = "sa";
    password = "";
    createTableQuery = """
            CREATE TABLE IF NOT EXISTS task(
              id INTEGER PRIMARY KEY AUTO_INCREMENT,
              title VARCHAR(512) NOT NULL,
              description VARCHAR(1024) NOT NULL,
              created_at TIMESTAMP
            );
        """;
  }
}
