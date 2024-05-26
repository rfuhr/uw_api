create sequence seq_config_calculo_preco start with 1;

create table config_calculo_preco (
	id						bigint not null constraint config_calculo_preco_pkey primary key,
	tipo_preco_id			bigint not null,
	operacao_interna_id 	bigint not null,
	aplica_indices_markup	boolean NOT NULL,
	aplica_percentual_fixo	boolean NOT NULL,
	dias_busca_precos		integer NOT NULL,
	percentual				decimal(15,5),
	data_inicio_vigencia 	date NOT NULL,
	data_final_vigencia 	date NOT NULL,	
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT config_calculo_preco_tipo_preco_id_foreign FOREIGN KEY (tipo_preco_id) REFERENCES tipo_preco (id),
	CONSTRAINT config_calculo_preco_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna (id)
);