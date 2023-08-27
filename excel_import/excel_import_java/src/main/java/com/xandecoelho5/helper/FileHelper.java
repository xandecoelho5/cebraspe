package com.xandecoelho5.helper;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

  public static void writeToFile(String filename, String content) {
    // FileHelper.class.getClassLoader().getResourceAsStream(filename);
    try (FileOutputStream os = new FileOutputStream(filename);
        PrintWriter writer = new PrintWriter(os)) {
      writer.write(content);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public static String readFromFile(String filename) {
    try {
      return Files.readString(Path.of(filename));
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return "";
  }
}
