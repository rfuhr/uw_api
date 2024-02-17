create sequence seq_configuracao_fiscal_icms start with 1;

create table configuracao_fiscal_icms (
	id						bigint not null constraint configuracao_fiscal_icms_pkey primary key,
	configuracao_fiscal_id  bigint NOT NULL,
	situacao_tributaria_id  bigint NOT NULL,
	modalidade_base_calculo varchar(10) NOT NULL,
	motivo_desoneracao_id	bigint,
	reducao_base_calculo	decimal(15,5),
	soma_ipi_base_calculo	boolean NOT NULL,
	aliquota				decimal(15,5),
	aliquota_credito		decimal(15,5),
	diferencial_aliquota	decimal(15,5),
	modalidade_base_calculo_st	varchar(10),
	motivo_desoneracao_st_id	bigint,
	soma_ipi_base_calculo_st	boolean,
	reducao_base_calculo_st		decimal(15,5),
	aliquota_st					decimal(15,5),
	margem_valor_agregado_st	decimal(15,5),
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT configuracao_fiscal_icms_configuracao_fiscal_id_foreign FOREIGN KEY (configuracao_fiscal_id) REFERENCES configuracao_fiscal (id),
	CONSTRAINT configuracao_fiscal_icms_situacao_tributaria_id_foreign FOREIGN KEY (situacao_tributaria_id) REFERENCES situacao_tributaria (id),
	CONSTRAINT configuracao_fiscal_icms_motivo_desoneracao_id_foreign FOREIGN KEY (motivo_desoneracao_id) REFERENCES motivo_desoneracao (id),
	CONSTRAINT configuracao_fiscal_icms_motivo_desoneracao_st_id_foreign FOREIGN KEY (motivo_desoneracao_st_id) REFERENCES motivo_desoneracao (id)
);