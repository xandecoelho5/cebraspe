package com;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Main {
  public static void main(String[] args) {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
      server.createContext("/task", new DefaultHandler());
      server.start();
      System.out.println("Listening on port 8080");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
