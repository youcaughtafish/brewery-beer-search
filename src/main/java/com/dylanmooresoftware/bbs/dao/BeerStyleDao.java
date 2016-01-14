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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dylanmooresoftware.bbs.model.BeerStyle;

@Repository
public class BeerStyleDao {
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  @Autowired
  private RowMapper<BeerStyle> beerStyleRowMapper;
  
  public int storeBeerStyle(BeerStyle style) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    namedParameterJdbcTemplate.getJdbcOperations().update(
      new PreparedStatementCreator() {
        
        @Override
        public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
          PreparedStatement ps = conn.prepareStatement(
              "insert into beer_style "
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
            ps.setInt(i++, style.getBreweryDbId());
            ps.setString(i++, style.getName());
            ps.setString(i++, style.getDescription());
            ps.setInt(i++, style.getIbuMin());
            ps.setInt(i++, style.getIbuMax());
            ps.setDouble(i++, style.getAbvMin());
            ps.setDouble(i++, style.getAbvMax());
            ps.setTimestamp(i++, new Timestamp(style.getBreweryDbCreateDate().getTime()));
          
            return ps;
        }
      },
      keyHolder
    );
    
    return keyHolder.getKey().intValue();
  }

  public BeerStyle findByBreweryDbId(int id) {
    final Map<String, Object> params = new HashMap<>();
    params.put("breweryDbId", id);
    
    final List<BeerStyle> styles = namedParameterJdbcTemplate.query(
          "select "
        + "  bs.pk as style_pk,"
        + "  bs.brewerydb_id as style_id,"
        + "  bs.name as style_name,"
        + "  bs.description as style_description,"
        + "  bs.ibu_min as style_ibu_min,"
        + "  bs.ibu_max as style_ibu_max,"
        + "  bs.abv_max as style_abv_max,"
        + "  bs.brewerydb_create_date as style_brewerydb_create_date "
        + "from "
        + "  beer_style bs"
        + "where "
        + "  bs.brewerydb_id = :breweryDbId",
        params,
        beerStyleRowMapper
    );
    
    return styles.size() > 0 ? styles.get(0) : null;
  }
}
