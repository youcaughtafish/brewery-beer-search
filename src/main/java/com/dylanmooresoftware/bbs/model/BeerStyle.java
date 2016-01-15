package com.dylanmooresoftware.bbs.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BeerStyle {
  private int pk;
  private int breweryDbId;
  private String name;
  private String description;
  private Double abvMin;
  private Double abvMax;
  private Integer ibuMin;
  private Integer ibuMax;
  private Date breweryDbCreateDate;
  
  public Double getAbvMin() {
    return abvMin;
  }
  public void setAbvMin(Double abvMin) {
    this.abvMin = abvMin;
  }
  public Double getAbvMax() {
    return abvMax;
  }
  public void setAbvMax(Double abvMax) {
    this.abvMax = abvMax;
  }
  public Integer getIbuMin() {
    return ibuMin;
  }
  public void setIbuMin(Integer ibuMin) {
    this.ibuMin = ibuMin;
  }
  public Integer getIbuMax() {
    return ibuMax;
  }
  public void setIbuMax(Integer ibuMax) {
    this.ibuMax = ibuMax;
  }
  public int getPk() {
    return pk;
  }
  public void setPk(int pk) {
    this.pk = pk;
  }
  public int getBreweryDbId() {
    return breweryDbId;
  }
  public void setBreweryDbId(int breweryDbId) {
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
  public Date getBreweryDbCreateDate() {
    return breweryDbCreateDate;
  }
  public void setBreweryDbCreateDate(Date breweryDbCreateDate) {
    this.breweryDbCreateDate = breweryDbCreateDate;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("pk", pk);
    builder.append("breweryDbId", breweryDbId);
    builder.append("name", name);
    builder.append("description", description);
    builder.append("abvMin", abvMin);
    builder.append("abvMax", abvMax);
    builder.append("ibuMin", ibuMin);
    builder.append("ibuMax", ibuMax);
    builder.append("breweryDbCreateDate", breweryDbCreateDate);
    return builder.toString();
  }
  
  
}
