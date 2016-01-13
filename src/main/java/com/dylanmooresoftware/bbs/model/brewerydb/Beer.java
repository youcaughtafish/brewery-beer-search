package com.dylanmooresoftware.bbs.model.brewerydb;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Beer {
  private String id;
  private String name;
  private String nameDisplay;
  private String description;
  private Double abv;
  private Integer ibu;
  private Integer glasswareId;
  private Integer availableId;
  private Integer styleId;
  private String isOrganic;
  private String status;
  private String statusDisplay;
  private Date createDate;
  private Date updateDate;
  private BeerGlass glass;
  private BeerAvailability available;
  private BeerStyle style;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNameDisplay() {
    return nameDisplay;
  }
  public void setNameDisplay(String nameDisplay) {
    this.nameDisplay = nameDisplay;
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
  public Integer getGlasswareId() {
    return glasswareId;
  }
  public void setGlasswareId(Integer glasswareId) {
    this.glasswareId = glasswareId;
  }
  public Integer getAvailableId() {
    return availableId;
  }
  public void setAvailableId(Integer availableId) {
    this.availableId = availableId;
  }
  public Integer getStyleId() {
    return styleId;
  }
  public void setStyleId(Integer styleId) {
    this.styleId = styleId;
  }
  public String getIsOrganic() {
    return isOrganic;
  }
  public void setIsOrganic(String isOrganic) {
    this.isOrganic = isOrganic;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getStatusDisplay() {
    return statusDisplay;
  }
  public void setStatusDisplay(String statusDisplay) {
    this.statusDisplay = statusDisplay;
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
  public BeerGlass getGlass() {
    return glass;
  }
  public void setGlass(BeerGlass glass) {
    this.glass = glass;
  }
  public BeerAvailability getAvailable() {
    return available;
  }
  public void setAvailable(BeerAvailability available) {
    this.available = available;
  }
  public BeerStyle getStyle() {
    return style;
  }
  public void setStyle(BeerStyle style) {
    this.style = style;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", id);
    builder.append("name", name);
    builder.append("nameDisplay", nameDisplay);
    builder.append("description", description);
    builder.append("abv", abv);
    builder.append("ibu", ibu);
    builder.append("glasswareId", glasswareId);
    builder.append("availableId", availableId);
    builder.append("styleId", styleId);
    builder.append("isOrganic", isOrganic);
    builder.append("status", status);
    builder.append("statusDisplay", statusDisplay);
    builder.append("createDate", createDate);
    builder.append("updateDate", updateDate);
    builder.append("glass", glass);
    builder.append("available", available);
    builder.append("style", style);
    return builder.toString();
  }
  
  
  
}
