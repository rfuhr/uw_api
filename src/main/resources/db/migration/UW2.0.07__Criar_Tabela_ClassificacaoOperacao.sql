create sequence seq_classificacao_operacao start with 1;

create table classificacao_operacao (
	id					bigint not null constraint classificacao_operacao_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into classificacao_operacao (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_classificacao_operacao'),1,'Adquirida de Terceiros', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),2,'Produção Própria', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),3,'Matéria Prima', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_classificacao_operacao'),4,'Insumo Processo Industrial', 1, NOW()::timestamp,1,NOW()::timestamp);