package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void testCalculateAverageFromNumbersList() {
    List<Float> numbers = List.of(10f, 15f, 20f, 25f);

    double average = Main.calculateAverageFromNumbersList(numbers);

    assertEquals(17.50, average);
  }

  @Test
  public void testCalculateAverageFromEmptyList() {
    List<Float> emptyList = new ArrayList<>();

    float average = Main.calculateAverageFromNumbersList(emptyList);

    assertEquals(0.0f, average);
  }

  @Test
  public void testMainMethodWithInput() {
    String input = "3\n10\n20\n30\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Main.main(new String[] {});
  }

  @Test
  public void testMainMethodWithInvalidInput() {
    String input = "invalid\n3\n10\n20\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    assertThrows(java.util.InputMismatchException.class, () -> Main.main(new String[] {}));
  }
}
