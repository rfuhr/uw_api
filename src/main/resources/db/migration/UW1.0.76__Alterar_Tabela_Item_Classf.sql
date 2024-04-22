alter table item add column classificacao_operacao_id bigint;
alter table item add CONSTRAINT item_classificacao_operacao_id_foreign FOREIGN KEY (classificacao_operacao_id) REFERENCES classificacao_operacao (id);

alter table classificacao_operacao add column uso_consumo boolean;

update classificacao_operacao set uso_consumo = false;
update classificacao_operacao set uso_consumo = true where codigo = 4;

insert into classificacao_operacao (id, codigo, nome, uso_consumo, user_create, date_create, user_update, date_update) values
 (nextval('seq_classificacao_operacao'),5,'Embalagem', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),6,'Produto em Processo', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),7,'Subproduto', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),8,'Produto Intermediário', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),9,'Material de Uso e Consumo', true, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),10,'Ativo Imobilizado', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),99,'Outros', false, 1, NOW()::timestamp,1,NOW()::timestamp);
 
 drop table tipo_produto;