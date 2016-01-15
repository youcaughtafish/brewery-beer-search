package com.dylanmooresoftware.bbs.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeerDbPopulatorService {
  @Value("${default.brewery.id}")
  private String defaultBreweryId;
  
  @Autowired
  private BeerService beerService;
  
  @PostConstruct
  public void populateDbWithDefaultBrewery() {
    beerService.populateDbBeersForBrewery(defaultBreweryId);
  }
}
