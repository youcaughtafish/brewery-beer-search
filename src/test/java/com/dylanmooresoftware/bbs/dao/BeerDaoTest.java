package com.dylanmooresoftware.bbs.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dylanmooresoftware.bbs.config.WebAppConfigurationAware;

public class BeerDaoTest extends WebAppConfigurationAware{
  @Autowired
  private BeerDao beerDao;
  
  @Test
  public void testStoreBeer() {
    beerDao.store(null);
  }
}
