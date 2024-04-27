create sequence controle_numeracao_seq;

create table controle_numeracao (
	id					bigint not null constraint controle_numeracao_pkey primary key,
	empresa_filial_id   bigint not null,
	tipo_documento_id	bigint not null,
	serie				bigint not null,
	ultimo_numero		bigint not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	constraint empresa_filial_controle_numeracao_id_foreign foreign key (empresa_filial_id) references empresa_filial (id),
	constraint tipo_documento_controle_numeracao_id_foreign foreign key (tipo_documento_id) references tipo_documento (id)
);
