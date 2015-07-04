--create products table
CREATE TABLE products(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  product_code CHAR(10) NOT NULL UNIQUE,
  product_name VARCHAR(50),
  price DECIMAL(6, 2),
  detail VARCHAR(200),
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create product_images table
CREATE TABLE product_images(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  pic BLOB NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create stocks table
CREATE TABLE stocks(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  stock INT NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);
