create table beer_style (
  pk integer identity,
  brewerydb_id integer,
  name varchar(50),
  description varchar(4000),
  brewerydb_create_date timestamp,
  unique (brewerydb_id)
);

create table beer (
  pk integer identity,
  brewerydb_id varchar(8),
  brewerydb_brewery_id varchar(8),
  name varchar(50),
  abv numeric,
  description varchar(4000),
  ibu_min integer,
  ibu_max integer,
  abv_min numeric,
  abv_max numeric,
  brewerydb_create_date timestamp,
  style_fk integer,
  unique (brewerydb_id),
  foreign key (style_fk) references beer_style
);



