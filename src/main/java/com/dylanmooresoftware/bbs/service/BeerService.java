package com.dylanmooresoftware.bbs.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dylanmooresoftware.bbs.dao.BeerDao;
import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeer;
import com.dylanmooresoftware.bbs.model.translator.ModelTranslator;

@Component
public class BeerService {
  private static final Logger logger = Logger.getLogger(BeerService.class);

  @Autowired
  private BreweryDbService breweryDbService;

  @Autowired
  private BeerDao beerDao;

  @Autowired
  private ModelTranslator<BreweryDbBeer, Beer> breweryDbBeerTranslator;
  
  public boolean populateDbBeersForBrewery(final String breweryDbBreweryId) {
    boolean success = true;
    try {
      final List<BreweryDbBeer> beers = breweryDbService.findBeers(breweryDbBreweryId);
      if (beers == null) {
        success = false;
        
      } else {
        beers.forEach(breweryDbBeer -> {
          final Beer beer = breweryDbBeerTranslator.translate(breweryDbBeer);
          beer.setBreweryDbBreweryId(breweryDbBreweryId);

          beerDao.store(beer);
        });
      }
      
    } catch (IOException e) {
      logger.error(String.format("error occurred while attempting to populate db with beers for brewery: %s",
          breweryDbBreweryId), e);

      success = false;
    }

    return success;
  }

  public List<Beer> beerSearch(double minAbv, double maxAbv, String search) {
    if (minAbv > maxAbv) return Collections.emptyList();
   
    String query = StringUtils.join("%", (search == null ? "" : search).trim(),"%");
    
    return beerDao.beerSearch(minAbv, maxAbv, query);
  }

  
}
