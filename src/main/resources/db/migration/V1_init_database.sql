CREATE TABLE station (
  id_station SERIAL PRIMARY KEY,
  station_name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  evaporation_rate_gasoline DECIMAL(5,2) NOT NULL,
  evaporation_rate_diesel DECIMAL(5,2) NOT NULL,
  evaporation_rate_kerosene DECIMAL(5,2) NOT NULL
);

CREATE TABLE product (
  id_product SERIAL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL,
  product_type VARCHAR(255) NOT NULL
);

CREATE TABLE price (
  id_price SERIAL PRIMARY KEY,
  id_product INT NOT NULL REFERENCES product(id_product),
  price_date DATE NOT NULL,
  unit_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE sale (
  sale_id SERIAL PRIMARY KEY,
  id_station INT NOT NULL REFERENCES station(id_station),
  id_product INT NOT NULL REFERENCES product(id_product),
  sale_date DATE NOT NULL,
  quantity_sold DECIMAL(10,2) NOT NULL,
  sale_amount DECIMAL(10,2) NOT NULL
);

CREATE TABLE stock (
  id_stock SERIAL PRIMARY KEY,
  id_station INT NOT NULL REFERENCES station(id_station),
  id_product INT NOT NULL REFERENCES product(id_product),
  stock_date DATE NOT NULL,
  quantity_stocked DECIMAL(10,2) NOT NULL
);

