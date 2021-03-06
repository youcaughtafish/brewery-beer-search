package com.dylanmooresoftware.bbs.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dylanmooresoftware.bbs.config.WebAppConfigurationAware;
import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeer;

import junit.framework.TestCase;

public class BreweryDbServiceTest extends WebAppConfigurationAware {
  @Autowired
  private BreweryDbService breweryDbService;
  
  @Value("${default.brewery.id}")
  private String defaultBreweryId;
  
  @Test
  public void testFindBeers() throws IOException {
    final List<BreweryDbBeer> beers = breweryDbService.findBeers(defaultBreweryId);
    TestCase.assertNotNull(beers);
    TestCase.assertTrue(beers.size() > 0);
  }
}
