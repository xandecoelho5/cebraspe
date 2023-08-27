package com.xandecoelho5.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xandecoelho5.database.DbConnection;
import com.xandecoelho5.model.MostSoldProduct;
import com.xandecoelho5.model.QuarterSum;

public class DatabaseHelper {

  private static final String QUARTER_SUM_QUERY = """
      select SUM(CASE WHEN month in ('01', '02', '03') THEN total ELSE 0 END) first_quarter,
             SUM(CASE WHEN month in ('04', '05', '06') THEN total ELSE 0 END) second_quarter,
             SUM(CASE WHEN month in ('07', '08', '09') THEN total ELSE 0 END) thir_quarter,
             SUM(CASE WHEN month in ('10', '11', '12') THEN total ELSE 0 END) fourth_quarter
        from (
           select strftime('%m', invoice.date) as month, sum(billing_amount) total
             from invoice
            group by month
           );
       """;
  private static final String TOP_5_MOST_SOLD_PRODUCTS_QUERY = """
      select product_sku, sum(billing_amount)
        from invoice
       group by product_sku
       order by quantity desc
       LIMIT 5;
         """;

  public static void insert(String query) {
    long start2 = System.currentTimeMillis();
    try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
      ps.executeUpdate();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    long end2 = System.currentTimeMillis();
    System.out.println("Elapsed time: " + (end2 - start2) + "ms");
  }

  public static boolean hasAnyRowInserted() {
    try (PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT COUNT(*) > 0 FROM invoice;")) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        return rs.getInt(1) == 1;
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return false;
  }

  public static QuarterSum selectQuarterSum() {
    try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(QUARTER_SUM_QUERY)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        return QuarterSum.fromResultSet(rs);
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

  public static List<MostSoldProduct> selectTop5MostSoldProducts() {
    List<MostSoldProduct> products = new ArrayList<>(5);
    try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(TOP_5_MOST_SOLD_PRODUCTS_QUERY)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        products.add(MostSoldProduct.fromResultSet(rs));
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return products;
  }
}
