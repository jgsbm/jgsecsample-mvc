-- load customer table
insert into customers (customer_name, address, tel, hashed_password, email, created_at, updated_at, version) values ('山田太郎', 'ＸＸ県ＹＹ市ＺＺ町　1-2-3', '09000000000', '$2a$04$DjmAiHiqHUzanV/1JSxxGeSqnTkXcngTkWUxRlDw4K3W202RaRPlW' /* test1 */, 't.yamada@example.com', current_timestamp(), current_timestamp(), 1);
insert into customers (customer_name, address, tel, hashed_password, email, created_at, updated_at, version) values ('鈴木次郎', 'ＡＡ県ＢＢ市ＣＣ町　3-2-1', '09000000001', '$2a$04$rhiyXmNNPqGapJ6Af/Npuu7X17u.P29Wh91H/yu0AVS/3D2x5.GHG' /* test2 */, 'j.suzuki@example.com', current_timestamp(), current_timestamp(), 1);

-- load creditcards table
insert into creditcards (creditno, customer_id, created_at, updated_at, version) values ('e994066d9f8d09ec030689f805f4d31b1606177e52c1fc27cf7a6f81ccf33fae5a2709d2459b187cfc615e00ed4e9f8a' /* 1234567890123456 */, 1, current_timestamp(), current_timestamp(), 1);
insert into creditcards (creditno, customer_id, created_at, updated_at, version) values ('8e49eb51a1ade8a18eff6e92a47f042269bce4b116b79a200a55403803b03cb339c18a806874c3e84d6e0c964f1a0898' /* 2345678901234567 */, 2, current_timestamp(), current_timestamp(), 1);