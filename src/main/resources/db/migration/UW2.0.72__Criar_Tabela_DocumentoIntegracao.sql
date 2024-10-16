create table tipo_integracao (
	value				char(2) not null constraint tipo_integracao_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_integracao values ('1', 'Estoque');
insert into tipo_integracao values ('2', 'Financeiro');

create sequence seq_documento_integracao start with 1;

create table documento_integracao (
	id						bigint not null constraint documento_integracao_pkey primary key,
	documento_id			bigint not null,
	tipo_integracao         varchar(2) not null,
	integrado   			boolean not null default false,
	data_integracao		 	timestamp with time zone,
	protocolo_destino		bigint,
	pilha_erro				BYTEA,
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT documento_integracao_documento_id_foreign FOREIGN KEY (documento_id) REFERENCES documento (id),
	CONSTRAINT documento_integracao_tipo_integracao_foreign FOREIGN KEY (tipo_integracao) REFERENCES tipo_integracao (value)
);