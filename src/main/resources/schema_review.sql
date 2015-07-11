--create reviews table
CREATE TABLE reviews(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  evaluation INT NOT NULL,
  comment VARCHAR(255),
  product_code char(10) NOT NULL,
  customer_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);
