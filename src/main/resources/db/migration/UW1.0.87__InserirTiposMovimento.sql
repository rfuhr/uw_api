insert into tipo_titulo (id, codigo, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_titulo'), 1, 'Contas a Receber', 'REC', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into tipo_titulo (id, codigo, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_titulo'), 2, 'Contas a Pagar', 'PAG', 1, NOW()::timestamp, 1, NOW()::timestamp);
 