package com.dylanmooresoftware.bbs.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.BeerStyle;

@Component
public class BeerRowMapper implements RowMapper<Beer> {
  @Autowired
  private RowMapper<BeerStyle> beerStyleRowMapper;

  @Override
  public Beer mapRow(ResultSet rs, int row) throws SQLException {
    final Beer beer = new Beer();
    beer.setPk(rs.getInt("beer_pk"));
    beer.setName(rs.getString("beer_name"));
    beer.setBreweryDbId(rs.getString("beer_brewerydb_id"));
    beer.setBreweryDbBreweryId(rs.getString("beer_brewerydb_brewery_id"));
    beer.setDescription(rs.getString("beer_description"));
    beer.setAbv(rs.getDouble("beer_abv"));
    beer.setIbu(rs.getInt("beer_ibu"));
    beer.setBreweryDbCreateDate(rs.getTimestamp("beer_brewerydb_create_date"));
    
    beer.setStyle(beerStyleRowMapper.mapRow(rs, row));
    
    return beer;
  }

}
