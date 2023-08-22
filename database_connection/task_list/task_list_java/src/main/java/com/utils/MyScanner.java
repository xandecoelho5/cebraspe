package com.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class MyScanner implements Closeable {

  private final Scanner scanner;

  public MyScanner() {
    scanner = new Scanner(System.in);
  }

  public Optional<Integer> readInt() {
    try {
      return Optional.of(Integer.parseInt(readLine()));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  public String readLine() {
    return scanner.nextLine();
  }

  @Override
  public void close() throws IOException {
    scanner.close();
  }
}
