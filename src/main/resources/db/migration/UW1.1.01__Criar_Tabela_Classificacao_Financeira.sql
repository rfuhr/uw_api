create sequence seq_plano_classificao_financeira;

create table plano_classificacao_financeira (
	id					bigint not null constraint plano_classificao_financeira_pk primary key,
	id_superior			bigint,
	codigo				varchar(50)  not null,
	nome				varchar(100) not null,
	nivel				integer not null,
	sintetica			boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT superior_plano_classificacao_financeira_foreign FOREIGN KEY (id_superior) REFERENCES plano_classificacao_financeira (id)						
);

alter table parametro_financeiro add column plano_classificacao_financeira_id bigint not null;
alter table parametro_financeiro CONSTRAINT plano_cassfificacao_parametro_financeiro_foreign FOREIGN KEY (plano_classificacao_financeira_id) REFERENCES plano_classificacao_financeira (id)