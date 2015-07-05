--create customers table
CREATE TABLE customers(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  customer_name VARCHAR(30) NOT NULL,
  address VARCHAR(255) NOT NULL,
  tel VARCHAR(11) NOT NULL,
  hashed_password VARCHAR(128) NOT NULL,
  email VARCHAR(256) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create creditcards tables
CREATE TABLE creditcards(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  encrypted_creditno VARCHAR(96) NOT NULL,
  customer_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

