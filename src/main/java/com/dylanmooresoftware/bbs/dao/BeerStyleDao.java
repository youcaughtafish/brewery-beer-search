package com.dylanmooresoftware.bbs.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.BeerStyle;

@Repository
public class BeerStyleDao {
  private static final Logger logger = Logger.getLogger(BeerStyleDao.class);
  
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  @Autowired
  private RowMapper<BeerStyle> beerStyleRowMapper;
  
  /**
   * Stores the specified beer style if the {@link BeerStyle#getBreweryDbId()} field
   * was not found in the database.  Note that existing beer styles are not updated 
   * in this implementation.
   * 
   * @param style
   * @return pk of existing or newly-inserted {@link Beer}
   */
  public int storeBeerStyle(BeerStyle style) {
    // lookup beer style by BreweryDB id
    final BeerStyle existingStyle = findByBreweryDbId(style.getBreweryDbId());
  
    // if existing style found, return the pk
    if (existingStyle != null) {
      logger.debug(String.format("for existing beer style: %s, found pk: %s", style.getBreweryDbId(), existingStyle.getPk()));
      return existingStyle.getPk();
    }
   
    // otherwise, let's insert a row for the new style
    KeyHolder keyHolder = new GeneratedKeyHolder();

    namedParameterJdbcTemplate.getJdbcOperations().update(conn -> {
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
            DaoUtils.setIntOrNull(ps, i++, style.getBreweryDbId());
            ps.setString(i++, style.getName());
            ps.setString(i++, style.getDescription());
            DaoUtils.setIntOrNull(ps, i++, style.getIbuMin());
            DaoUtils.setIntOrNull(ps, i++, style.getIbuMax());
            DaoUtils.setDoubleOrNull(ps, i++, style.getAbvMin());
            DaoUtils.setDoubleOrNull(ps, i++, style.getAbvMax());
            DaoUtils.setTimestampOrNull(ps, i++, style.getBreweryDbCreateDate());
          
            return ps;
            
      }, keyHolder);
   
    final int newBeerStylePk = keyHolder.getKey().intValue();
    logger.debug(String.format("for new beer style: %s, stored under pk: %s", style.getBreweryDbId(), newBeerStylePk));
    
    return newBeerStylePk;
  }

  public BeerStyle findByBreweryDbId(int id) {
    final Map<String, Object> params = new HashMap<>();
    params.put("breweryDbId", id);
    
    final List<BeerStyle> styles = namedParameterJdbcTemplate.query(
          "select "
        + "  bs.pk as style_pk,"
        + "  bs.brewerydb_id as style_brewerydb_id,"
        + "  bs.name as style_name,"
        + "  bs.description as style_description,"
        + "  bs.ibu_min as style_ibu_min,"
        + "  bs.ibu_max as style_ibu_max,"
        + "  bs.abv_max as style_abv_max,"
        + "  bs.brewerydb_create_date as style_brewerydb_create_date "
        + "from "
        + "  beer_style bs "
        + "where "
        + "  bs.brewerydb_id = :breweryDbId",
        params,
        beerStyleRowMapper
    );
    
    return styles.size() > 0 ? styles.get(0) : null;
  }
}
