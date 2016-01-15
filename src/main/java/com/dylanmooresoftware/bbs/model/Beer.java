package com.dylanmooresoftware.bbs.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Beer {
  private int pk;
  private String name;
  private String description;
  private Double abv;
  private Integer ibu;
  private BeerStyle style;

  private String breweryDbId;
  private String breweryDbBreweryId;
  private Date breweryDbCreateDate;
  
  public int getPk() {
    return pk;
  }
  public void setPk(int pk) {
    this.pk = pk;
  }
  public String getBreweryDbId() {
    return breweryDbId;
  }
  public void setBreweryDbId(String breweryDbId) {
    this.breweryDbId = breweryDbId;
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
  public Double getAbv() {
    return abv;
  }
  public void setAbv(Double abv) {
    this.abv = abv;
  }
  public Integer getIbu() {
    return ibu;
  }
  public void setIbu(Integer ibu) {
    this.ibu = ibu;
  }
  public Date getBreweryDbCreateDate() {
    return breweryDbCreateDate;
  }
  public void setBreweryDbCreateDate(Date breweryDbCreatedDate) {
    this.breweryDbCreateDate = breweryDbCreatedDate;
  }
  public BeerStyle getStyle() {
    return style;
  }
  public void setStyle(BeerStyle style) {
    this.style = style;
  }
  public String getBreweryDbBreweryId() {
    return breweryDbBreweryId;
  }
  public void setBreweryDbBreweryId(String breweryDbBreweryId) {
    this.breweryDbBreweryId = breweryDbBreweryId;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("pk", pk);
    builder.append("name", name);
    builder.append("description", description);
    builder.append("abv", abv);
    builder.append("ibu", ibu);
    builder.append("style", style);
    builder.append("breweryDbId", breweryDbId);
    builder.append("breweryDbBreweryId", breweryDbBreweryId);
    builder.append("breweryDbCreateDate", breweryDbCreateDate);
    return builder.toString();
  }
  
}
