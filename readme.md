## Brewery Beer Search
This projects uses the BreweryDB API to lookup beers produced by Harpoon Brewery and cache them for lookup in a flat file HSQL database.  A simple static web frontend is provided to search the beers by name and description.

#### Building and Running
This project requires:
* Java 8
* Maven

This project can be built into a war using the `mvn clean package` command or can be run in-place with `mvn tomcat7:run`.