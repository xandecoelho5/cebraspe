package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      System.out.print("Quantos números deseja inserir? ");
      int numbersQuantity = sc.nextInt();

      List<Float> numbersToCalc = new ArrayList<>();
      for (int i = 0; i < numbersQuantity; i++) {
        System.out.format("Insira o número %d: ", i + 1);
        numbersToCalc.add(sc.nextFloat());
      }

      float average = calculateAverageFromNumbersList(numbersToCalc);
      System.out.format("A média dos números inseridos é: %.2f", average);
    }
  }

  public static float calculateAverageFromNumbersList(List<Float> numbers) {
    if (numbers.isEmpty())
      return 0;

    float sum = numbers.stream().reduce(0.0f, Float::sum);
    return sum / numbers.size();
  }
}