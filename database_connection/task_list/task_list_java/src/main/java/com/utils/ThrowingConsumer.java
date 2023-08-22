package com.utils;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
  void accept(T t) throws E;

  static <T, E extends Exception> Consumer<T> consumerWrapper(
      ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
    return i -> {
      try {
        throwingConsumer.accept(i);
      } catch (Exception ex) {
        try {
          E exCast = exceptionClass.cast(ex);
          System.err.println("Exception occured : " + exCast.getMessage());
        } catch (ClassCastException ccEx) {
          throw new RuntimeException(ex);
        }
      }
    };
  }
}
