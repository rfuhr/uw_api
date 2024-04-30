create sequence seq_config_mensagem_fiscal_situac_trib start with 1;

create table config_mensagem_fiscal_situac_trib (
	id					bigint not null constraint config_mensagem_fiscal_situac_trib_pkey primary key,
	situacao_tributaria_id	bigint not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia 		date not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_mensagem_fiscal_situac_trib_situac_trib_id_foreign FOREIGN KEY (situacao_tributaria_id) REFERENCES situacao_tributaria (id)
);