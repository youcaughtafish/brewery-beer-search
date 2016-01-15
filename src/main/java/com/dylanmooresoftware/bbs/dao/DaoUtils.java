package com.dylanmooresoftware.bbs.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public class DaoUtils {
  public static void setIntOrNull(PreparedStatement ps, int index, Integer i) throws SQLException {
    if (i == null) {
      ps.setNull(index, Types.INTEGER);
    } else {
      ps.setInt(index, i);
    }
  }

  public static void setDoubleOrNull(PreparedStatement ps, int index, Double d) throws SQLException {
    if (d == null) {
      ps.setNull(index, Types.DOUBLE);
    } else {
      ps.setDouble(index, d);
    }
  }
  
  public static void setTimestampOrNull(PreparedStatement ps, int index, Date d) throws SQLException {
    if (d == null) {
      ps.setNull(index, Types.TIMESTAMP);
    } else {
      ps.setTimestamp(index, new Timestamp(d.getTime()));
    }
  }
}
