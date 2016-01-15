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
public class BeerDao {
  private static final Logger logger = Logger.getLogger(BeerDao.class);

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
    + "  bs.brewerydb_id as style_brewerydb_id,"
    + "  bs.name as style_name,"
    + "  bs.description as style_description,"
    + "  bs.ibu_min as style_ibu_min,"
    + "  bs.ibu_max as style_ibu_max,"
    + "  bs.abv_max as style_abv_max,"
    + "  bs.brewerydb_create_date as style_brewerydb_create_date "
    + "from "
    + "  beer br join beer_style bs"
    + "  on (br.style_fk = bs.pk) ";
  
  public List<Beer> beerSearch(double minAbv, double maxAbv, String query) {
    final Map<String, Object> params = new HashMap<>();
    params.put("minAbv", minAbv);
    params.put("maxAbv", maxAbv);
    params.put("query", query);
    
    return namedParameterJdbcTemplate.query(
        beerFindSql
      + "where "
      + "  (br.abv >= :minAbv and br.abv <= :maxAbv)"
      + "  and (lower(br.description) like lower(:query) "
      +"       or lower(br.name) like lower(:query)) "
      +"order by br.brewerydb_create_date desc",
      params,
      beerRowMapper);
  }

  /**
   * Stores the specified beer if the {@link Beer#getBreweryDbId()} field
   * was not found in the database.  Note that existing 
   * beers are not updated in this implementation.
   * 
   * The same logic is used to store new instances {@link Beer#getStyle()}
   * (based on {@link BeerStyle#getBreweryDbId()}).  
   * See {@link BeerStyleDao#storeBeerStyle(BeerStyle)} for more info.
   * 
   * @param beer
   * @return pk of existing or newly-inserted {@link Beer}
   */
  public int storeByBreweryDbId(Beer beer) {
    // lookup beer by BreweryDB id
    final Beer existingBeer = findByBreweryDbId(beer.getBreweryDbId());
   
    // if we found one, return the pk
    if (existingBeer != null) {
      logger.debug(String.format("for existing beer: %s, found pk: %s", beer.getBreweryDbId(), existingBeer.getPk()));
      return existingBeer.getPk();
    }
   
    // store new (or lookup existing) beer style
    beer.getStyle().setPk(beerStyleDao.storeBeerStyle(beer.getStyle()));

    // insert a row representing the new beer
    KeyHolder keyHolder = new GeneratedKeyHolder();

    namedParameterJdbcTemplate.getJdbcOperations().update(conn -> {
          PreparedStatement ps = conn.prepareStatement(
              "insert into beer "
            + "(name, "
            + " brewerydb_id, "
            + " brewerydb_brewery_id, "
            + " description, "
            + " abv, "
            + " ibu, "
            + " brewerydb_create_date,"
            + " style_fk) "
            + " values "
            + "(?, ?, ?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);
         
            int i = 1;
            ps.setString(i++, beer.getName());
            ps.setString(i++, beer.getBreweryDbId());
            ps.setString(i++, beer.getBreweryDbBreweryId());
            ps.setString(i++, beer.getDescription());
            DaoUtils.setDoubleOrNull(ps, i++, beer.getAbv());
            DaoUtils.setIntOrNull(ps, i++, beer.getIbu());
            DaoUtils.setTimestampOrNull(ps, i++, beer.getBreweryDbCreateDate());
            DaoUtils.setIntOrNull(ps, i++, beer.getStyle().getPk());
          
            return ps;
            
      }, keyHolder);
   
    final int newBeerPk = keyHolder.getKey().intValue();
    logger.debug(String.format("for new beer with id: %s, stored under pk: %s", beer.getBreweryDbId(), beer.getPk()));
    
    return newBeerPk;
  }
  
  public Beer find(final int pk) {
    final Map<String, Object> params = new HashMap<>();
    params.put("pk", pk);
    
    final List<Beer> beers = namedParameterJdbcTemplate.query(
        beerFindSql
        + "where "
        + "  br.pk = :pk",
        params,
        beerRowMapper
    );
    
    return beers.size() > 0 ? beers.get(0) : null;
  }
  
  public Beer findByBreweryDbId(final String id) {
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
  
  public List<Beer> findByBreweryDbBreweryId(final String breweryId) {
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
