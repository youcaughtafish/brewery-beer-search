package com.dylanmooresoftware.bbs.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.dylanmooresoftware.bbs.config.WebAppConfigurationAware;
import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.BeerStyle;


public class BeerDaoTest extends WebAppConfigurationAware{
  private static final Logger logger = Logger.getLogger(BeerDaoTest.class);
  
  @Autowired
  private BeerDao beerDao;
  
  private int breweryIdTicker = 1;
  
  @Test
  public void testStoreAndFindExistingBeer() throws ParseException {
    final Beer beer = createDummyBeerAndStyle();
    beer.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    Assert.isTrue(beerDao.storeByBreweryDbId(beer) == beerDao.storeByBreweryDbId(beer));
  }
  
  @Test
  public void testStoreAndFindNewWithExistingBeer() throws ParseException {
    final Beer beer1 = createDummyBeerAndStyle();
    beer1.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    final Beer beer2 = createDummyBeerAndStyle();
    beer2.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    Assert.isTrue(beerDao.storeByBreweryDbId(beer1) != beerDao.storeByBreweryDbId(beer2));
  }
  
  @Test
  public void testStoreAndFindNewWithExistingBeerSameStyle() throws ParseException {
    final Beer beer1 = createDummyBeerAndStyle();
    beer1.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    final Beer beer2 = createDummyBeerAndStyle();
    beer2.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    final Beer foundBeer1 = beerDao.find(beerDao.storeByBreweryDbId(beer1));
    final Beer foundBeer2 = beerDao.find(beerDao.storeByBreweryDbId(beer2));
    
    Assert.isTrue(foundBeer1.getPk() != foundBeer2.getPk());
    Assert.isTrue(foundBeer1.getStyle().getPk() == foundBeer2.getStyle().getPk());
  }
  
  @Test
  public void testStoreAndFindNewBeer() throws ParseException {
    final Beer beer = createDummyBeerAndStyle();
    beer.setBreweryDbId(String.valueOf(breweryIdTicker++));
    
    int beerPk = beerDao.storeByBreweryDbId(beer);
    Assert.isTrue(beerPk >= 0);
    
    final Beer foundBeer = beerDao.find(beerPk);
    logger.info(String.format("retrieval of stored beer yielded: %s", foundBeer));
    Assert.notNull(foundBeer);
    Assert.isTrue(beerPk == foundBeer.getPk());
    Assert.isTrue(foundBeer.getStyle().getPk() >= 0);
  }
  
  private Beer createDummyBeerAndStyle() throws ParseException {
    final Beer beer = new Beer();
    beer.setAbv(1.1);
    beer.setBreweryDbBreweryId("RzvedX");
    beer.setBreweryDbCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-10 12:42:51"));
    beer.setBreweryDbId("H66Pe6");
    beer.setDescription("Harpoon Bohemian Pilsner follows the age-old tradition of extended lagering to help produce a smooth yet complex beer. The blend of European malts is balanced with Tettnang and Saaz hops to create a slightly malty beer with a crisp finish.");
    beer.setIbu(20);
    beer.setName("Bohemian Pilsner");
    
    BeerStyle style = new BeerStyle();
    style.setAbvMax(4.0);
    style.setAbvMin(1.0);
    style.setBreweryDbCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-10 12:42:51"));
    style.setBreweryDbId(17);
    style.setDescription("Traditional Bohemian Pilseners are medium bodied, and they can be as dark as a light amber color. This style balances moderate bitterness and noble-type hop aroma and flavor with a malty, slightly sweet, medium body. Extremely low levels of diacetyl and low levels of sweet corn-like dimethylsulfide (DMS) character, if perceived, are characteristic of this style and both may accent malt aroma. A toasted-, biscuit-like, bready malt character along with low levels of sulfur compounds may be evident. There should be no chill haze. Its head should be dense and rich.");
    style.setIbuMax(30);
    style.setIbuMin(10);
    style.setName("Bohemian-Style Pilsener");
    beer.setStyle(style);
    return beer;
  }
}
