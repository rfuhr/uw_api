create sequence seq_conta;

create table conta (
	id					bigint not null constraint conta_pkey primary key,
	agencia_id          bigint not null,
	numero				varchar(10) not null,
	dv					char(1) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT conta_agencia_id_foreign FOREIGN KEY (agencia_id) REFERENCES agencia(id)
);

