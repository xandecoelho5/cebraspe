package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection implements AutoCloseable {

  protected String driverClassName;
  protected String url;
  protected String user;
  protected String password;
  protected Connection con = null;

  abstract void init();

  public DbConnection() {
    init();
    connect();
  }

  public void connect() {
    try {
      Class.forName(driverClassName);
      con = DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
      System.out.format("Erro ao se conectar no database: %s \n %s \n %s \n", ex.getSQLState(), ex.getErrorCode(),
          ex.getMessage());
    } catch (Exception ex) {
      System.out.println("Erro ao se conectar no database: " + ex.getMessage());
    }
  }

  @Override
  public void close() {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException ex) {
      System.err.println("Erro ao fechar a conexão: " + ex.getMessage());
    }
  }

  public Connection getCon() {
    return con;
  }

  public boolean hasDateTimeType() {
    return !driverClassName.contains("sqlite");
  }
}
