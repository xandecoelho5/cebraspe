package com.xandecoelho5;

import com.xandecoelho5.helper.DatabaseHelper;
import com.xandecoelho5.helper.FastexcelHelper;
import com.xandecoelho5.helper.FileHelper;

public class Main {

  private static final String QUARTER_SUM_FILENAME = "src/main/resources/quarter_sum.txt";
  private static final String TOP_5_MOST_SOLD_PRODUCTS_FILENAME = "src/main/resources/top_5_most_sold_products.txt";

  public static void main(String[] args) {
    importInitialData();
    saveQuarterSum();
    saveTop5MostSoldProducts();
  }

  private static void importInitialData() {
    boolean needInsert = !DatabaseHelper.hasAnyRowInserted();

    if (needInsert) {
      long start1 = System.currentTimeMillis();
      new FastexcelHelper().readExcelAndInsert("C:/NewProjects/cebraspe/excel_import/dummy_data.xlsx");
      long end1 = System.currentTimeMillis();
      System.out.println("Elapsed time: " + (end1 - start1) + "ms");
    }
  }

  private static void saveQuarterSum() {
    var quarterSum = DatabaseHelper.selectQuarterSum();

    FileHelper.writeToFile(QUARTER_SUM_FILENAME, quarterSum.toString());

    String savedText = FileHelper.readFromFile(QUARTER_SUM_FILENAME);
    System.out.println(savedText);
  }

  private static void saveTop5MostSoldProducts() {
    var top5MostSoldProducts = DatabaseHelper.selectTop5MostSoldProducts();

    StringBuilder sb = new StringBuilder();
    for (var product : top5MostSoldProducts) {
      sb.append(product.toString()).append('\n');
    }
    FileHelper.writeToFile(TOP_5_MOST_SOLD_PRODUCTS_FILENAME, sb.toString());

    String savedText = FileHelper.readFromFile(TOP_5_MOST_SOLD_PRODUCTS_FILENAME);
    System.out.println(savedText);
  }
}