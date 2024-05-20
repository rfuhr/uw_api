create sequence seq_grupo_contabil start with 1;

create table grupo_contabil (
	id					bigint not null constraint grupo_contabil_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into grupo_contabil (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_grupo_contabil'),1,'Consumo Direto/Despesa',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_grupo_contabil'),2,'Produtos Agropecuários',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_grupo_contabil'),3,'Matéria Prima ou Materiais Diretos',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_grupo_contabil'),4,'Bens de Fornecimentos',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_grupo_contabil'),5,'Imobilizado',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_grupo_contabil'),6,'Uso e Consumo (Estoque)',  1, NOW()::timestamp,1,NOW()::timestamp);