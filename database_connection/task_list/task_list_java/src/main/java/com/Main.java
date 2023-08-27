package com;

import java.io.IOException;

import com.database.DbConnection;
import com.database.H2Connection;
import com.database.MySQLConnection;
import com.database.PostgresConnection;
import com.database.SQLiteConnection;
import com.repository.DatabaseRepository;
import com.repository.FileTaskRepository;
import com.service.TaskService;
import com.utils.FileHandler;
import com.utils.MyScanner;
import com.view.HomeView;

public class Main {
  public static void main(String[] args) {
    try (MyScanner reader = new MyScanner();
        DbConnection connection = new H2Connection();) {
      // var taskService = new TaskService(new FileTaskRepository(new FileHandler()));

      var taskService = new TaskService(new DatabaseRepository(connection));
      var homeView = new HomeView(reader, taskService);
      homeView.start();

      System.out.println("conexao encerrada!!");
    } catch (IOException ex) {
      System.out.println("Ocorreu algum erro: " + ex.getMessage());
    }
  }
}