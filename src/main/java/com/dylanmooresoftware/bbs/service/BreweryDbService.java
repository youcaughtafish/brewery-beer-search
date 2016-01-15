package com.dylanmooresoftware.bbs.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeer;
import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

@Component
public class BreweryDbService {
  private static final Logger logger = Logger.getLogger(BreweryDbService.class);
  
  @Value("${default.brewery.id}")
  private String defaultBreweryId;
  
  @Value("${brewerydb.brewery.url}")
  private String breweryDbBreweryUrl;
  
  private Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd HH:mm:ss")
      .create();
  
  public List<BreweryDbBeer> findBeers(final String breweryDbBreweryId) throws IOException {
    Assert.notNull(breweryDbBreweryId);
    Assert.notNull(breweryDbBreweryUrl);
    
    final String beersForBreweryUrl = String.format(breweryDbBreweryUrl, breweryDbBreweryId);
    
    logger.debug(String.format("Using url: %s", beersForBreweryUrl));
    
    BreweryDbResponse breweryDbResponse = null;
    
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(beersForBreweryUrl);
      
      try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
        HttpEntity entity = response.getEntity();
      
        breweryDbResponse = 
            gson.fromJson(new JsonReader(
                new InputStreamReader(entity.getContent(), "UTF-8")), 
                BreweryDbResponse.class);
        
        EntityUtils.consume(entity);
      }
    }
    
    return breweryDbResponse == null ? null : breweryDbResponse.getData();
  }
}
