-- load product table
insert into products (product_code, product_name, price, detail, created_at, updated_at, version) values ('ITEM000001', 'productA', 1000, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into products (product_code, product_name, price, detail, created_at, updated_at, version) values ('ITEM000002', 'productB', 2000, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into products (product_code, product_name, price, detail, created_at, updated_at, version) values ('ITEM000003', 'productC', 3000, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into products (product_code, product_name, price, detail, created_at, updated_at, version) values ('ITEM000004', 'productD', 4000, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into products (product_code, product_name, price, detail, created_at, updated_at, version) values ('ITEM000005', 'productE', 5000, 'productA detail', current_timestamp(), current_timestamp(), 1);

-- load product_pic table
insert into product_images (pic, product_id, created_at, updated_at, version) values(file_read('classpath:/img/ITEM000001.jpg'), 1, current_timestamp(), current_timestamp(), 1);
insert into product_images (pic, product_id, created_at, updated_at, version) values(file_read('classpath:/img/ITEM000002.jpg'), 2, current_timestamp(), current_timestamp(), 1);
insert into product_images (pic, product_id, created_at, updated_at, version) values(file_read('classpath:/img/ITEM000003.jpg'), 3, current_timestamp(), current_timestamp(), 1);
insert into product_images (pic, product_id, created_at, updated_at, version) values(file_read('classpath:/img/ITEM000004.jpg'), 4, current_timestamp(), current_timestamp(), 1);
insert into product_images (pic, product_id, created_at, updated_at, version) values(file_read('classpath:/img/ITEM000005.jpg'), 5, current_timestamp(), current_timestamp(), 1);

-- load stock table
insert into stocks (stock, product_id, created_at, updated_at, version) values (4, 1,current_timestamp(), current_timestamp(), 1);
insert into stocks (stock, product_id, created_at, updated_at, version) values (3, 2,current_timestamp(), current_timestamp(), 1);
insert into stocks (stock, product_id, created_at, updated_at, version) values (2, 3,current_timestamp(), current_timestamp(), 1);
insert into stocks (stock, product_id, created_at, updated_at, version) values (1, 4,current_timestamp(), current_timestamp(), 1);
insert into stocks (stock, product_id, created_at, updated_at, version) values (0, 5,current_timestamp(), current_timestamp(), 1);
