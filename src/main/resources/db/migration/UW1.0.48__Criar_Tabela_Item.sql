create sequence seq_item;

create table item (
	id					bigint not null constraint item_pkey primary key,
	codigo				bigint not null,
	nome				varchar(120) not null,
	sku					varchar(50)  not null,
	descritivo			varchar(500) not null,
	unidade_medida_comercial_id   bigint not null,
	proprio				boolean   not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT item_unidade_medida_comercial_id_foreign FOREIGN KEY (unidade_medida_comercial_id) REFERENCES unidade_medida(id)
);
