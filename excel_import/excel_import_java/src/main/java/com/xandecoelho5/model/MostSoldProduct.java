package com.xandecoelho5.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public record MostSoldProduct(String productSku, BigDecimal billingAmount) {

  public static MostSoldProduct fromResultSet(ResultSet rs) throws SQLException {
    return new MostSoldProduct(rs.getString(1), rs.getBigDecimal(2));
  }

  @Override
  public String toString() {
    return String.format("ProductSku: %s, BillingAmount: %.2f", productSku, billingAmount);
  }
}
