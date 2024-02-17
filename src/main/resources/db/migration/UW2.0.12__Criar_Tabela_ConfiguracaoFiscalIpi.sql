create sequence seq_configuracao_fiscal_ipi start with 1;

create table configuracao_fiscal_ipi (
	id						bigint not null constraint configuracao_fiscal_ipi_pkey primary key,
	configuracao_fiscal_id  bigint NOT NULL,
	situacao_tributaria_id  bigint NOT NULL,
	modalidade_base_calculo varchar(10) NOT NULL,
	enquadramento_id		bigint NOT NULL,
	aliquota				decimal(15,5) NOT NULL,
	tipo_calculo			varchar(10) NOT NULL,
	codigo_selo				varchar(10),
	quantidade_selo			int,
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT configuracao_fiscal_ipi_configuracao_fiscal_id_foreign FOREIGN KEY (configuracao_fiscal_id) REFERENCES configuracao_fiscal (id),
	CONSTRAINT configuracao_fiscal_ipi_situacao_tributaria_id_foreign FOREIGN KEY (situacao_tributaria_id) REFERENCES situacao_tributaria (id),
	CONSTRAINT configuracao_fiscal_ipi_enquadramento_id_foreign FOREIGN KEY (enquadramento_id) REFERENCES enquadramento (id)
);