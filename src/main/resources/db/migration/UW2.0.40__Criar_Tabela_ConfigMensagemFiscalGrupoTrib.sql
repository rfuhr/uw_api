create sequence seq_config_mensagem_fiscal_grupo_trib start with 1;

create table config_mensagem_fiscal_grupo_trib (
	id					bigint not null constraint config_mensagem_fiscal_grupo_trib_pkey primary key,
	config_mensagem_fiscal_id	bigint not null,
	grupo_tributacao_id	bigint 		not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia 		date not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_mensagem_fiscal_grupo_trib_grupo_tributacao_id_foreign FOREIGN KEY (grupo_tributacao_id) REFERENCES grupo_tributacao (id),
	CONSTRAINT config_mensagem_fiscal_grupo_trib_config_fiscal_config_mensagem_fiscal_id_foreign FOREIGN KEY (config_mensagem_fiscal_id) REFERENCES config_mensagem_fiscal (id)
);