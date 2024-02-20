create sequence seq_tipo_produto start with 1;

create table tipo_produto (
	id					bigint not null constraint tipo_produto_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	uso_consumo			boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_produto (id, codigo, nome, uso_consumo, user_create, date_create, user_update, date_update) values
 (nextval('seq_tipo_produto'),1,'Matéria Prima', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),2,'Mercadoria para Revenda', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),3,'Embalagem', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),4,'Produto em Processo', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),5,'Produto Acabado', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),6,'Subproduto', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),7,'Produto Intermediário', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),8,'Material de Uso e Consumo', true, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),9,'Ativo Imobilizado', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),10,'Serviços', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),11,'Outros Insumos', true, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_produto'),12,'Outros', false, 1, NOW()::timestamp,1,NOW()::timestamp);