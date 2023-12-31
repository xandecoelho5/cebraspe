package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TaskHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    HttpMethod method = HttpMethod.valueOf(exchange.getRequestMethod());
    switch (method) {
      case GET -> handleGETRequest(exchange);
      case POST -> handlePOSTRequest(exchange);
      case PUT -> handlePUTRequest(exchange);
      case DELETE -> handleDELETERequest(exchange);
    }
    ;
    // handleResponse(exchange, response);
  }

  private void handleGETRequest(HttpExchange exchange) throws IOException {
    // parse request
    Map<String, Object> parameters = new HashMap<String, Object>();
    URI requestedUri = exchange.getRequestURI();
    String query = requestedUri.getRawQuery();
    parseQuery(query, parameters);

    // send response
    String response = "";
    for (String key : parameters.keySet())
      response += key + " = " + parameters.get(key) + "\n";
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.toString().getBytes());

    os.close();
  }

  private void handlePOSTRequest(HttpExchange exchange) throws IOException {
    // parse request
    Map<String, Object> parameters = new HashMap<String, Object>();
    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
    BufferedReader br = new BufferedReader(isr);
    String query = br.readLine();
    parseQuery(query, parameters);

    // send response
    String response = "";
    for (String key : parameters.keySet())
      response += key + " = " + parameters.get(key) + "\n";
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.toString().getBytes());
    os.close();
  }

  private String handlePUTRequest(HttpExchange exchange) {
    return "teste";
  }

  private String handleDELETERequest(HttpExchange exchange) {
    return "teste";
  }

  private void handleResponse(HttpExchange exchange, String response) throws IOException {
    try (OutputStream outputStream = exchange.getResponseBody();) {

      exchange.sendResponseHeaders(200, response.length());
      outputStream.write(response.getBytes());
      outputStream.flush();
    }
  }

  public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
    if (query != null) {
      String pairs[] = query.split("[&]");
      for (String pair : pairs) {
        String param[] = pair.split("[=]");
        String key = null;
        String value = null;
        if (param.length > 0) {
          key = URLDecoder.decode(param[0],
              System.getProperty("file.encoding"));
        }

        if (param.length > 1) {
          value = URLDecoder.decode(param[1],
              System.getProperty("file.encoding"));
        }

        if (parameters.containsKey(key)) {
          Object obj = parameters.get(key);
          if (obj instanceof List<?>) {
            List<String> values = (List<String>) obj;
            values.add(value);

          } else if (obj instanceof String) {
            List<String> values = new ArrayList<String>();
            values.add((String) obj);
            values.add(value);
            parameters.put(key, values);
          }
        } else {
          parameters.put(key, value);
        }
      }
    }
  }
}
