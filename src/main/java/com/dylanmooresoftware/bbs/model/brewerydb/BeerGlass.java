package com.dylanmooresoftware.bbs.model.brewerydb;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BeerGlass {
  private Integer id;
  private String name;
  private Date createDate;
  
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
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", id);
    builder.append("name", name);
    builder.append("createDate", createDate);
    return builder.toString();
  }
  
  
  
}
