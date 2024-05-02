create sequence seq_config_incentivo_fiscal_parceiro start with 1;

create table config_incentivo_fiscal_parceiro (
	id					bigint not null constraint config_incentivo_fiscal_parceiro_pkey primary key,
	config_incentivo_fiscal_id	bigint not null,
	parceiro_local_id	bigint not null,
	data_inicio_vigencia 		date not null,
	data_final_vigencia 		date not null,
	tipo_tributo        		varchar(15),
	uf_id						bigint,
	percentual_aproveitamento	decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_incentivo_fiscal_config_incentivo_fiscal_id_foreign FOREIGN KEY (config_incentivo_fiscal_id) REFERENCES config_incentivo_fiscal (id),
	CONSTRAINT config_incentivo_fiscal_parceiro_tipo_tributo_foreign FOREIGN KEY (tipo_tributo) REFERENCES tipo_tributo (value),
	CONSTRAINT config_incentivo_fiscal_parceiro_uf_id_foreign FOREIGN KEY (uf_id) REFERENCES uf (id)
);