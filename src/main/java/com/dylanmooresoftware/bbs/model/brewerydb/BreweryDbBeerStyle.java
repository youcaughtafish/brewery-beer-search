package com.dylanmooresoftware.bbs.model.brewerydb;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BreweryDbBeerStyle {
  private Integer id;
  private Integer categoryId;
  private BreweryDbBeerStyleCategory category;
  private String name;
  private String shortName;
  private String description;
  private Integer ibuMin;
  private Integer ibuMax;
  private Double abvMin;
  private Double abvMax;
  private Integer srmMin;
  private Integer srmMax;
  private Double ogMin;
  private Double fgMin;
  private Double fgMax;
  private Date createDate;
  private Date updateDate;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }
  public BreweryDbBeerStyleCategory getCategory() {
    return category;
  }
  public void setCategory(BreweryDbBeerStyleCategory category) {
    this.category = category;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getShortName() {
    return shortName;
  }
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
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
  public Integer getSrmMin() {
    return srmMin;
  }
  public void setSrmMin(Integer srmMin) {
    this.srmMin = srmMin;
  }
  public Integer getSrmMax() {
    return srmMax;
  }
  public void setSrmMax(Integer srmMax) {
    this.srmMax = srmMax;
  }
  public Double getOgMin() {
    return ogMin;
  }
  public void setOgMin(Double ogMin) {
    this.ogMin = ogMin;
  }
  public Double getFgMin() {
    return fgMin;
  }
  public void setFgMin(Double fgMin) {
    this.fgMin = fgMin;
  }
  public Double getFgMax() {
    return fgMax;
  }
  public void setFgMax(Double fgMax) {
    this.fgMax = fgMax;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  public Date getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", id);
    builder.append("categoryId", categoryId);
    builder.append("category", category);
    builder.append("name", name);
    builder.append("shortName", shortName);
    builder.append("description", description);
    builder.append("ibuMin", ibuMin);
    builder.append("ibuMax", ibuMax);
    builder.append("abvMin", abvMin);
    builder.append("abvMax", abvMax);
    builder.append("srmMin", srmMin);
    builder.append("srmMax", srmMax);
    builder.append("ogMin", ogMin);
    builder.append("fgMin", fgMin);
    builder.append("fgMax", fgMax);
    builder.append("createDate", createDate);
    builder.append("updateDate", updateDate);
    return builder.toString();
  }
 
  
}
