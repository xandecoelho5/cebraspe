package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  public static String format(LocalDateTime dateTime) {
    return formatter.format(dateTime);
  }
}
