alter table operacao_acessoria_financeira drop column idn_cria_sub_seq_movto;
alter table operacao_acessoria_financeira drop column idn_compoe_saldo;

alter table tipo_operacao_financeira drop column idn_subst_carteira;
alter table tipo_operacao_financeira drop column idn_retencao;
alter table tipo_operacao_financeira drop column idn_baixa_titulo;
alter table tipo_operacao_financeira drop column idn_cria_parcela;
alter table tipo_operacao_financeira drop column idn_cria_seq_parcela;
alter table tipo_operacao_financeira drop column idn_cria_seq_mvto;
alter table tipo_operacao_financeira drop column idn_sel_baixa;
alter table tipo_operacao_financeira drop column idn_negociacao;
alter table tipo_operacao_financeira drop column idn_cancelamento;
alter table tipo_operacao_financeira drop column idn_estorno;
alter table tipo_operacao_financeira drop column idn_baixa_npr;
alter table tipo_operacao_financeira drop column idn_estorno_baixa; 
alter table tipo_operacao_financeira drop column idn_baixa_chq_avulso;
alter table tipo_operacao_financeira drop column idn_lista_postitbaixa;
alter table tipo_operacao_financeira drop column idn_rel_despesas;
alter table tipo_operacao_financeira drop column idn_lista_analisevda;

alter table operacao_financeira drop column idn_gera_ficha;
alter table operacao_financeira drop column idn_integra_bco;
alter table operacao_financeira drop column operacao_banco_id;
alter table operacao_financeira drop column idn_gera_apurfin;

drop sequence seq_operacao_movimento_financeiro;
drop sequence seq_operacao_acessoria_financeira;

insert into operacao_movimento_financeiro (id, nome, user_create, date_create, user_update, date_update)
 values (1, 'Inclusão', 1, NOW()::timestamp, 1, NOW()::timestamp); 

insert into operacao_movimento_financeiro (id, nome, user_create, date_create, user_update, date_update)
 values (51, 'Baixa', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into operacao_acessoria_financeira (id, nome, idn_juro_desconto, user_create, date_create, user_update, date_update)
 values (1, 'Principal', 'N', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into tipo_operacao_financeira (id, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_tipo_operacao_financeira'), 'Inclusão por Lançamento', 'INC_LCTO', 1, NOW()::timestamp, 1, NOW()::timestamp);
 
insert into operacao_financeira (id, tipo_operacao_financeira_id, operacao_movimento_financeiro_id, operacao_acessoria_financeira_id, user_create, date_create, user_update, date_update)
 values (nextval('seq_operacao_financeira'), 1, 1, 1, 1, NOW()::timestamp, 1, NOW()::timestamp); 
 
 alter table movimento_financeiro alter column observacao drop not null;
 
 alter table titulo add column empresa_filial_id int8 not null;

alter table titulo add constraint empresa_filial_titulo_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id);
 