package com.dylanmooresoftware.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.service.BeerService;

@Controller
public class BeerController {
  @Autowired
  private BeerService beerService;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() { 
    return "static/index.html";
  }
  
  @RequestMapping(value = "/beer-search", method = RequestMethod.GET)
  @ResponseBody
  public List<Beer> beerSearch(
    @RequestParam(name = "minAbv", defaultValue = "0", required = false) double minAbv,
    @RequestParam(name = "maxAbv", defaultValue = "100", required = false) double maxAbv,
    @RequestParam(name = "search", defaultValue = "", required = false) String search)
  {
    return beerService.beerSearch(minAbv, maxAbv, search);
  }
}
