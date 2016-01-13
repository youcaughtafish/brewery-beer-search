package com.dylanmooresoftware.bbs.model.brewerydb;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BreweryDbResponse {
  private String message;
  private String errorMessage;
  private List<Beer> data;
  
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public List<Beer> getData() {
    return data;
  }
  public void setData(List<Beer> beers) {
    this.data = beers;
  }
  public String getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("message", message);
    builder.append("errorMessage", errorMessage);
    builder.append("beers", data);
    return builder.toString();
  }
  
  
}
