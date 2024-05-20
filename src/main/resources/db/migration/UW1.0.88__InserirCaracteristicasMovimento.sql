insert into caracteristica_movimento_financeiro (id, codigo, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_caracteristica_movimento_financeiro'), 1, 'Normal', 'Normal', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into caracteristica_movimento_financeiro (id, codigo, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_caracteristica_movimento_financeiro'), 2, 'Devolução', 'Devol', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into caracteristica_movimento_financeiro (id, codigo, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_caracteristica_movimento_financeiro'), 3, 'Adiantamento', 'Adto', 1, NOW()::timestamp, 1, NOW()::timestamp);
 