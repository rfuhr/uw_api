create table tipo_certificado (
	value				char(3) not null constraint tipo_certificado_pkey primary key
);

insert into tipo_certificado values ('A1');
insert into tipo_certificado values ('A3');

create table tipo_ambiente (
	value				char(1) not null constraint tipo_ambiente_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_ambiente values ('1', 'Produção');
insert into tipo_ambiente values ('2', 'Homologação');

create table tipo_emissao (
	value				char(1) not null constraint tipo_emissao_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_emissao values ('1', 'Emissão normal (não em contingência)');
insert into tipo_emissao values ('2', 'Contingência FS-IA, com impressão do DANFE em formulário de segurança');
insert into tipo_emissao values ('3', 'Contingência SCAN (Sistema de Contingência do Ambiente Nacional)');
insert into tipo_emissao values ('4', 'Contingência DPEC (Declaração Prévia da Emissão em Contingência)');
insert into tipo_emissao values ('5', 'Contingência FS-DA, com impressão do DANFE em formulário de segurança');
insert into tipo_emissao values ('6', 'Contingência SVC-AN (SEFAZ Virtual de Contingência do AN)');
insert into tipo_emissao values ('7', 'Contingência SVC-RS (SEFAZ Virtual de Contingência do RS)');

create sequence seq_config_empresa;

create table config_empresa (
	id					bigint not null constraint config_empresa_pkey primary key,
	empresa_id			bigint not null,
	regime_tributario_id bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_empresa_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

create sequence seq_config_empresa_nfe;

create table config_empresa_nfe (
	id					bigint not null constraint config_empresa_nfe_pkey primary key,
	config_empresa_id	bigint not null,
	empresa_filial_id  	bigint not null,
	tipo_certificado 	varchar(2) not null,
	tipo_ambiente		integer not null,
	tipo_emissao		integer not null,
	serie 				integer not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT config_empresa_nfe_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial(id)
);

create sequence seq_empresa_certificado;

create table empresa_certificado (
	id					bigint not null constraint empresa_certificado_pkey primary key,
	empresa_id			bigint not null,
	tipo_Certificado 	varchar(2) not null,
	certificado 		bytea not null,
	data_validade 		date not null,
	senha 				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT empresa_certificado_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);