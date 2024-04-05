create table modalidade_base_calculo (
	value				char(3) not null constraint modalidade_base_calculo_pkey primary key,
	name				varchar(250) not null
);

insert into modalidade_base_calculo values ('0', 'Margem Valor Agregado');
insert into modalidade_base_calculo values ('1', 'Pauta (Valor)');
insert into modalidade_base_calculo values ('2', 'Preço Tabelado');
insert into modalidade_base_calculo values ('3', 'Valor da Operação');

create table modalidade_base_calculo_st (
	value				char(3) not null constraint modalidade_base_calculo_st_pkey primary key,
	name				varchar(250) not null
);

insert into modalidade_base_calculo_st values ('0', 'Preço tabelado ou máximo sugerido');
insert into modalidade_base_calculo_st values ('1', 'Lista Negativa (valor)');
insert into modalidade_base_calculo_st values ('2', 'Lista Positiva (valor)');
insert into modalidade_base_calculo_st values ('3', 'Lista Neutra (valor)');
insert into modalidade_base_calculo_st values ('4', 'Margem Valor Agregado (%)');
insert into modalidade_base_calculo_st values ('5', 'Pauta (valor)');

create table tipo_calculo (
	value				char(3) not null constraint tipo_calculo_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_calculo values ('P', 'Percentual');
insert into tipo_calculo values ('V', 'Valor');

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