package com.xandecoelho5.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

public class FastexcelHelper {

  public void readExcelAndInsert(String fileLocation) {
    StringBuilder sb = new StringBuilder();
    try (FileInputStream file = new FileInputStream(fileLocation);
        ReadableWorkbook wb = new ReadableWorkbook(file)) {
      Sheet sheet = wb.getFirstSheet();
      try (Stream<Row> rows = sheet.openStream().skip(1)) {
        rows.forEach(r -> {
          if (sb.length() == 0) {
            sb.append(
                "INSERT INTO invoice (client_id, product_category, product_sku, date, quantity, billing_amount) VALUES ");
          }
          sb.append(getSQLfromRow(r.getCell(0).asNumber().intValue(),
              r.getCell(1).asString(),
              r.getCell(2).asString(),
              r.getCell(3).asDate().toLocalDate(),
              r.getCell(4).asNumber().intValue(),
              new BigDecimal(r.getCell(5).asString())));
          if (sb.length() % 2500 == 0) {
            sb.setCharAt(sb.length() - 1, ';');
            insertOnDB(sb.toString());
            sb.setLength(0);
          }
        });
      }
      if (sb.length() > 0) {
        sb.setCharAt(sb.length() - 1, ';');
        insertOnDB(sb.toString());
      }
    } catch (IOException ex) {
      System.out.println("Error like reading: " + ex.getMessage());
    }
  }

  private void insertOnDB(String query) {
    DatabaseHelper.insert(query);
  }

  private String getSQLfromRow(int clientId, String productCategory, String productSku, LocalDate date, int quantity,
      BigDecimal billingAmount) {
    String strBillingValue = String.format("%.2f", billingAmount).replace(',', '.');
    String str = String.format(" (%d, '%s', '%s', '%s', %d, %s),", clientId, productCategory,
        productSku, date, quantity, strBillingValue);
    return str;
  }
}
