package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DefaultHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String response = "";

    if ("GET".equals(exchange.getRequestMethod())) {
      response = "{\"message\": \"Hello, World!\"}";
    } else if ("POST".equals(exchange.getRequestMethod())) {
      StringBuilder requestBody = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
      String line;
      while ((line = reader.readLine()) != null) {
        requestBody.append(line);
      }
      reader.close();

      response = "{\"received_data\": " + requestBody.toString() + "}";
    }

    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
