--create reviews table
CREATE TABLE reviews(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  evaluation INT NOT NULL,
  comment VARCHAR(255),
  customer_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);
