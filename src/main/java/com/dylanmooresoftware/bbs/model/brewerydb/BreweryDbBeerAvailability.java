package com.dylanmooresoftware.bbs.model.brewerydb;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BreweryDbBeerAvailability {
  private Integer id;
  private String name;
  private String description;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", id);
    builder.append("name", name);
    builder.append("description", description);
    return builder.toString();
  }
  
  
  
  
}
