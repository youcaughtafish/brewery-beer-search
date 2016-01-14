package com.dylanmooresoftware.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.BeerStyle;

@Repository
public class BeerDao {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  @Autowired
  private RowMapper<Beer> beerRowMapper;
  
  @Autowired
  private BeerStyleDao beerStyleDao;
  
  private static final String beerFindSql = 
      "select "
    + "  br.pk as beer_pk,"
    + "  br.name as beer_name,"
    + "  br.brewerydb_id as beer_brewerydb_id,"
    + "  br.brewerydb_brewery_id as beer_brewerydb_brewery_id,"
    + "  br.description as beer_description,"
    + "  br.abv as beer_abv,"
    + "  br.ibu as beer_ibu,"
    + "  br.brewerydb_create_date as beer_brewerydb_create_date,"
    + "  bs.pk as style_pk,"
    + "  bs.brewerydb_id as style_id,"
    + "  bs.name as style_name,"
    + "  bs.description as style_description,"
    + "  bs.ibu_min as style_ibu_min,"
    + "  bs.ibu_max as style_ibu_max,"
    + "  bs.abv_max as style_abv_max,"
    + "  bs.brewerydb_create_date as style_brewerydb_create_date "
    + "from "
    + "  beer br join beer_style bs"
    + "  on (br.style_fk = bs.pk) ";

  @Transactional
  public int store(Beer beer) {
    final BeerStyle existingStyle = beerStyleDao.findByBreweryDbId(beer.getStyle().getBreweryDbId());
    int beerStylePk = -1;
    if (existingStyle == null) {
      beerStylePk = beerStyleDao.storeBeerStyle(beer.getStyle());
    } else {
      beerStylePk = existingStyle.getPk();
    }
    
    beer.getStyle().setPk(beerStylePk);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();

    namedParameterJdbcTemplate.getJdbcOperations().update(
      new PreparedStatementCreator() {
        
        @Override
        public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
          PreparedStatement ps = conn.prepareStatement(
              "insert into beer "
            + "(brewerydb_id, "
            + " name, "
            + " description, "
            + " ibu_min, "
            + " ibu_max, "
            + " abv_min, "
            + " abv_max, "
            + " brewerydb_create_date) "
            + " values "
            + "(?,"
            + " ?,"
            + " ?,"
            + " ?,"
            + " ?,"
            + " ?,"
            + " ?,"
            + " ?)",
            Statement.RETURN_GENERATED_KEYS);
         
            int i = 1;
//            ps.setInt(i++, style.getBreweryDbId());
//            ps.setString(i++, style.getName());
//            ps.setString(i++, style.getDescription());
//            ps.setInt(i++, style.getIbuMin());
//            ps.setInt(i++, style.getIbuMax());
//            ps.setDouble(i++, style.getAbvMin());
//            ps.setDouble(i++, style.getAbvMax());
//            ps.setTimestamp(i++, new Timestamp(style.getBreweryDbCreateDate().getTime()));
          
            return ps;
        }
      },
      keyHolder
    );
    
    return keyHolder.getKey().intValue();
  }
  
  public Beer findByBreweryDbBeerId(final String id) {
    final Map<String, Object> params = new HashMap<>();
    params.put("breweryDbBeerId", id);
    
    final List<Beer> beers = namedParameterJdbcTemplate.query(
        beerFindSql
        + "where "
        + "  br.brewerydb_id = :breweryDbBeerId",
        params,
        beerRowMapper
    );
    
    return beers.size() > 0 ? beers.get(0) : null;
  }
  
  public List<Beer> findByBreweryId(final String breweryId) {
    final Map<String, Object> params = new HashMap<>();
    params.put("breweryId", breweryId);
    
    return namedParameterJdbcTemplate.query(
        beerFindSql
        + "where "
        + "  br.brewerydb_brewery_id = :breweryId",
        params,
        beerRowMapper
    );
  }
}
