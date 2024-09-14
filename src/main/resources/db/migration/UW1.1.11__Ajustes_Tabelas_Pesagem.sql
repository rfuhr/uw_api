alter table operacao_interna add caracteristica_agricola boolean not null default false;

create sequence seq_operacao_interna_agricola;

create table operacao_interna_agricola (
	id					bigint not null constraint operacao_interna_agricola_pkey primary key,
	operacao_interna_id bigint not null,
	seleciona_pesagem	boolean not null default false,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT operacao_interna_agricola_operacao_interna_id_foreign FOREIGN KEY (operacao_interna_id) REFERENCES operacao_interna(id)
);