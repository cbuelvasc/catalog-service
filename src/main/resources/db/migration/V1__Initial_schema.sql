CREATE TABLE prices (
  price_list bigserial NOT NULL,
  brand_id bigserial NOT NULL,
  start_date timestamp NOT NULL,
  end_date timestamp NOT NULL,
  product_id bigserial NOT NULL,
  priority integer NOT NULL,
  price numeric(15,2),
  currency varchar(3),
  created_date timestamp NOT NULL,
  created_by varchar(255),
  last_modified_date timestamp NOT NULL,
  last_modified_by varchar(255),
  version integer NOT NULL,
  CONSTRAINT price_list_id PRIMARY KEY (price_list)
);