-- load customer table
insert into customers (customer_name, address, tel, hashed_password, email, created_at, updated_at, version) values ('山田太郎', 'ＸＸ県ＹＹ市ＺＺ町　1-2-3', '09000000000', '$2a$04$DjmAiHiqHUzanV/1JSxxGeSqnTkXcngTkWUxRlDw4K3W202RaRPlW' /* test1 */, 't.yamada@example.com', current_timestamp(), current_timestamp(), 1);
insert into customers (customer_name, address, tel, hashed_password, email, created_at, updated_at, version) values ('鈴木次郎', 'ＡＡ県ＢＢ市ＣＣ町　3-2-1', '09000000001', '$2a$04$rhiyXmNNPqGapJ6Af/Npuu7X17u.P29Wh91H/yu0AVS/3D2x5.GHG' /* test2 */, 'j.suzuki@example.com', current_timestamp(), current_timestamp(), 1);

