package com.dylanmooresoftware.bbs.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dylanmooresoftware.bbs.model.BeerStyle;

@Component
public class BeerStyleRowMapper implements RowMapper<BeerStyle> {
  @Override
  public BeerStyle mapRow(ResultSet rs, int row) throws SQLException {
    final BeerStyle style = new BeerStyle();
    style.setPk(rs.getInt("style_pk"));
    style.setBreweryDbId(rs.getInt("style_brewerydb_id"));
    style.setName(rs.getString("style_name"));
    style.setDescription(rs.getString("style_description"));
    style.setIbuMin(rs.getInt("style_ibu_min"));
    style.setIbuMax(rs.getInt("style_ibu_max"));
    style.setBreweryDbCreateDate(rs.getTimestamp("style_brewerydb_create_date"));
    
    return style;
  }
}
