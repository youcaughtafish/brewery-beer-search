package com.dylanmooresoftware.bbs.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dylanmooresoftware.bbs.config.WebAppConfigurationAware;

public class BeerServiceTest extends WebAppConfigurationAware {
  @Autowired
  private BeerService beerService;
  
  @Value("${default.brewery.id}")
  private String defaultBreweryId;
  
  @Test
  public void testPopulate() {
    Assert.assertTrue(beerService.populateDbBeersForBrewery(defaultBreweryId));
    Assert.assertFalse(beerService.populateDbBeersForBrewery("fake-id-here"));
  }
}
