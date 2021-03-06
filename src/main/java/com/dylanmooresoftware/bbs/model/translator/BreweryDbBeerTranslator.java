package com.dylanmooresoftware.bbs.model.translator;

import org.springframework.stereotype.Component;

import com.dylanmooresoftware.bbs.model.Beer;
import com.dylanmooresoftware.bbs.model.BeerStyle;
import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeer;
import com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeerStyle;

/**
 * 
 * Translates a com.dylanmooresoftware.bbs.model.brewerydb.BreweryDbBeer
 * (a BreweryDB API-defined schema for a beer) into a 
 * com.dylanmooresoftware.bbs.model.Beer (the project definition of a
 * beer). 
 * 
 * Note that due to the lack of BreweryId being returned by BreweryDB,
 * the Beer#breweryDbBreweryId field is not mapped in this class.
 */
@Component
public class BreweryDbBeerTranslator implements ModelTranslator<BreweryDbBeer, Beer> {

  @Override
  public Beer translate(BreweryDbBeer br) {
    final Beer beer = new Beer();
    beer.setAbv(br.getAbv());
    beer.setBreweryDbCreateDate(br.getCreateDate());
    beer.setDescription(br.getDescription());
    beer.setIbu(br.getIbu());
    beer.setName(br.getName());
    beer.setBreweryDbId(br.getId());

    final BreweryDbBeerStyle bdbStyle = br.getStyle();

    final BeerStyle style = new BeerStyle();

    if (bdbStyle != null) {
      style.setAbvMax(bdbStyle.getAbvMax());
      style.setAbvMin(bdbStyle.getAbvMin());
      style.setBreweryDbCreateDate(bdbStyle.getCreateDate());
      style.setBreweryDbId(bdbStyle.getId());
      style.setDescription(bdbStyle.getDescription());
      style.setIbuMax(bdbStyle.getIbuMax());
      style.setIbuMin(bdbStyle.getIbuMin());
      style.setName(bdbStyle.getName());
    }

    beer.setStyle(style);

    return beer;
  }

}
