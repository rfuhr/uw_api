create sequence seq_config_markup_plano_item start with 1;

create table config_markup_plano_item (
	id						bigint not null constraint config_markup_plano_item_pkey primary key,
    plano_classificacao_item_id bigint not null,
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT config_markup_plano_item_plano_classificacao_item_id_foreign FOREIGN KEY (plano_classificacao_item_id) REFERENCES plano_classificacao_item (id)
);