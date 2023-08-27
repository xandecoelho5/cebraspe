package com.xandecoelho5.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public record QuarterSum(BigDecimal firstQuarter, BigDecimal secondQuarter, BigDecimal thirdQuarter,
    BigDecimal fourthQuarter) {

  public static QuarterSum fromResultSet(ResultSet rs) throws SQLException {
    return new QuarterSum(rs.getBigDecimal(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getBigDecimal(4));
  }

  @Override
  public String toString() {
    return String.format("firstQuarter=%.2f, secondQuarter=%.2f, thirdQuarter=%.2f, fourthQuarter=%.2f",
        firstQuarter, secondQuarter, thirdQuarter, fourthQuarter);
  }
}
