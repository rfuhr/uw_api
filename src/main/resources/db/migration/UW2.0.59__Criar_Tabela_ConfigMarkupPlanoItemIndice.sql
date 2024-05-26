create sequence seq_config_markup_plano_item_indice start with 1;

create table config_markup_plano_item_indice (
	id						bigint not null constraint config_markup_plano_item_indice_pkey primary key,
	config_markup_plano_item_id bigint not null,
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
    indice_markup_id 		bigint not null,
	percentual				decimal(15,5),
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT config_markup_plano_item_indice_config_markup_plano_item_id_foreign FOREIGN KEY (config_markup_plano_item_id) REFERENCES config_markup_plano_item (id),
	CONSTRAINT config_markup_plano_item_indice_indice_markup_id_foreign FOREIGN KEY (indice_markup_id) REFERENCES indice_markup (id)
);