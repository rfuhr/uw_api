create sequence seq_empresa_filial;

create table empresa_filial (
	id				bigint       not null constraint empresa_filial_pkey primary key,
	empresa_id      bigint       not null,
	nome            varchar(255) not null,
	sigla        	varchar(30)  not null,
	user_create     bigint 		 not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone,
	CONSTRAINT empresa_filial_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

create sequence seq_departamento;

create table departamento (
	id					bigint       not null constraint departamento_pkey primary key,
	empresa_filial_id   bigint       not null,
	nome            varchar(255) not null,
	sigla        	varchar(30)  not null,
	user_create     bigint 		 not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone,
	CONSTRAINT departamento_empresa__filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial(id)
);