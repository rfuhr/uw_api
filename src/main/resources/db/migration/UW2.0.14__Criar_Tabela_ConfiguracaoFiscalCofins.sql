create sequence seq_configuracao_fiscal_cofins start with 1;

create table configuracao_fiscal_cofins (
	id						bigint not null constraint configuracao_fiscal_cofins_pkey primary key,
	configuracao_fiscal_id  bigint NOT NULL,
	situacao_tributaria_id  bigint NOT NULL,
	modalidade_base_calculo varchar(10) NOT NULL,
	aliquota				decimal(15,5) NOT NULL,
	tipo_calculo			varchar(10) NOT NULL,
	aliquota_st				decimal(15,5),
	tipo_calculo_st			varchar(10),
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT configuracao_fiscal_cofins_configuracao_fiscal_id_foreign FOREIGN KEY (configuracao_fiscal_id) REFERENCES configuracao_fiscal (id),
	CONSTRAINT configuracao_fiscal_cofins_situacao_tributaria_id_foreign FOREIGN KEY (situacao_tributaria_id) REFERENCES situacao_tributaria (id)
);