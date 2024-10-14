alter table movimento_financeiro rename carteira_id to carteira_financeira_id;
alter table titulo add column nosso_numero bigint;
alter table tipo_operacao_financeira add column idn_sel_baixa boolean default false;
alter table tipo_operacao_financeira add column idn_baixa_titulo boolean default false;
alter table tipo_operacao_financeira add column idn_estorna_baixa boolean default false;  
alter table operacao_acessoria_financeira  add column idn_cria_sub_seq_movto boolean default false;
alter table operacao_acessoria_financeira  add column idn_compoe_saldo boolean default false;

insert into tipo_operacao_financeira (id, nome, sigla, idn_sel_baixa, idn_baixa_titulo, idn_estorna_baixa, user_create, date_create, user_update, date_update) values
  (2, 'Baixa por Pagamento', 'PAG', true, true, false, 1, NOW()::timestamp, 1, NOW()::timestamp),
  (3, 'Inclusão por Estorno', 'INC_EST', false, false, true, 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into operacao_financeira (id, tipo_operacao_financeira_id, operacao_movimento_financeiro_id, operacao_acessoria_financeira_id, user_create, date_create, user_update, date_update) values
 (nextval('seq_operacao_financeira'), 1, 51, 1, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 2, 51, 1, 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_operacao_financeira'), 3, 1, 1, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 1, 2, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 1, 3, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 1, 6, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 1, 8, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 51, 2, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 51, 3, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 51, 6, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 3, 51, 8, 1, NOW()::timestamp, 1, NOW()::timestamp); 
 
insert into operacao_acessoria_financeira (id, nome, idn_juro_desconto, idn_cria_sub_seq_movto, idn_compoe_saldo, user_create, date_create, user_update, date_update) values
 (2, 'Encargo Pactuado', 'J', true, true, 1, NOW()::timestamp, 1, NOW()::timestamp),
 (3, 'Encargo Não Pactuado', 'J', true, false, 1, NOW()::timestamp, 1, NOW()::timestamp),
 (6, 'Desconto Pactuado', 'D', true, false, 1, NOW()::timestamp, 1, NOW()::timestamp),
 (8, 'Desconto Não Pactuado', 'D', true, false, 1, NOW()::timestamp, 1, NOW()::timestamp);

insert into operacao_financeira (id, tipo_operacao_financeira_id, operacao_movimento_financeiro_id, operacao_acessoria_financeira_id, user_create, date_create, user_update, date_update) values
 (nextval('seq_operacao_financeira'), 2, 51, 2, 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_operacao_financeira'), 2, 51, 3, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 2, 51, 6, 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_operacao_financeira'), 2, 51, 8, 1, NOW()::timestamp, 1, NOW()::timestamp); 
 
alter table parametro_financeiro drop column empresa_id;
alter table parametro_financeiro drop column operacao_financeira_id;
alter table parametro_financeiro add column operacao_movimento_financeiro_id bigint not null;
alter table parametro_financeiro add CONSTRAINT operacao_movimento_financeiro_parametro_financeiro_foreign FOREIGN KEY (operacao_movimento_financeiro_id) REFERENCES operacao_movimento_financeiro (id);
alter table parametro_financeiro add column operacao_acessoria_financeira_id bigint not null;
alter table parametro_financeiro add CONSTRAINT operacao_acessoria_financeira_parametro_financeiro_foreign FOREIGN KEY (operacao_acessoria_financeira_id) REFERENCES operacao_acessoria_financeira (id);

insert into parametro_financeiro (id, tipo_titulo_id, carteira_financeira_id, fato_gerador_id, caracteristica_movimento_financeiro_id, operacao_movimento_financeiro_id, operacao_acessoria_financeira_id, indicador_dc, user_create, date_create, user_update, date_update) values
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 1, 1, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 1, 2, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 1, 3, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 1, 6, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 1, 8, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 51, 1, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp), 
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 51, 2, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 51, 3, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 51, 6, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 1, 1, 1, 1, 51, 8, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 1, 1, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 1, 2, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 1, 3, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 1, 6, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 1, 8, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 51, 1, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 51, 2, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 51, 3, 'C', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 51, 6, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp),
 (nextval('seq_parametro_financeiro'), 2, 1, 1, 1, 51, 8, 'D', 1, NOW()::timestamp, 1, NOW()::timestamp);  

