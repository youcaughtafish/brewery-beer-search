create table beer_style (
  pk integer identity,
  brewerydb_id integer,
  name varchar(100),
  description varchar(4000),
  ibu_min integer,
  ibu_max integer,
  abv_min numeric,
  abv_max numeric,
  brewerydb_create_date timestamp,
  unique (brewerydb_id)
);

create table beer (
  pk integer identity,
  name varchar(100),
  brewerydb_id varchar(8),
  brewerydb_brewery_id varchar(8),
  description varchar(4000),
  abv numeric,
  ibu integer,
  brewerydb_create_date timestamp,
  style_fk integer,
  unique (brewerydb_id),
  foreign key (style_fk) references beer_style
);

