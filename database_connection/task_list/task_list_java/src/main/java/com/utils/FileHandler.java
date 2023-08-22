package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.Task;

public class FileHandler {

  private static final String filename = "src/main/resources/task_list";

  public void writeObjectToFile(List<Task> tasks) {
    try (OutputStream os = new FileOutputStream(filename);
        ObjectOutputStream outputStream = new ObjectOutputStream(os)) {
      outputStream.writeObject(tasks);
    } catch (IOException ex) {
      System.out.println("Error writing to file: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public List<Task> readObjectsFromFile() {
    try (InputStream is = new FileInputStream(filename);
        ObjectInputStream objectInput = new ObjectInputStream(is)) {
      return (ArrayList<Task>) objectInput.readObject();
    } catch (IOException ex) {
      // System.out.println("Erro ao ler o arquivo: " + ex.getMessage());
    } catch (ClassNotFoundException ex) {
      System.out.println("Erro ao converter objeto: " + ex.getMessage());
    }
    return new ArrayList<>();
  }
}
