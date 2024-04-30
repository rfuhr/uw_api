create sequence seq_config_mensagem_fiscal_tipo_incent_fiscal start with 1;

create table config_mensagem_fiscal_tipo_incent_fiscal (
	id					bigint not null constraint config_mensagem_fiscal_tipo_incent_fiscal_pkey primary key,
	tipo_incentivo_fiscal_id	bigint 		not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia 		date not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_mensagem_fiscal_tipo_incent_fiscal_tipo_incentivo_fiscal_id_foreign FOREIGN KEY (tipo_incentivo_fiscal_id) REFERENCES tipo_incentivo_fiscal (id)
);