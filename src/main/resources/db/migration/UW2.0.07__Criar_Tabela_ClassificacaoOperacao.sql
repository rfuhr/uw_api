create sequence seq_classificacao_operacao start with 1;

create table classificacao_operacao (
	id					bigint not null constraint classificacao_operacao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	uso_consumo			boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into classificacao_operacao (id, codigo, nome, uso_consumo, user_create, date_create, user_update, date_update) values
 (nextval('seq_classificacao_operacao'),1,'Adquirida de Terceiros', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),2,'Produção Própria', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),3,'Matéria Prima', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),4,'Mercadoria para Revenda', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),5,'Embalagem', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),6,'Produto em Processo', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),7,'Produto Acabado', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),8,'Subproduto', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),9,'Produto Intermediário', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),10,'Material de Uso e Consumo', true, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),11,'Ativo Imobilizado', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),12,'Serviços', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),13,'Outros Insumos', true, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),14,'Outros', false, 1, NOW()::timestamp,1,NOW()::timestamp);