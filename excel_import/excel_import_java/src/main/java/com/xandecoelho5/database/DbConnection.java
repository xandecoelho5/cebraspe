package com.xandecoelho5.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbConnection {

  private static final String URL = "jdbc:sqlite:src/main/resources/excel_import.db";
  private static Connection conn;

  private static final String CREATE_TABLE = """
      CREATE TABLE IF NOT EXISTS invoice(
        client_id INTEGER,
        product_category TEXT,
        product_sku TEXT,
        date DATE,
        quantity INTEGER,
        billing_amount REAL
      );
      """;

  public static Connection getConnection() {
    if (conn == null) {
      try {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(URL);
        createTable();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    return conn;
  }

  private static void createTable() {
    try (PreparedStatement ps = conn.prepareStatement(CREATE_TABLE)) {
      ps.executeUpdate();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public static void close() {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
