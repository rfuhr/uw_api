create sequence seq_nfe_comunicacao_sefaz;

create table nfe_comunicacao_sefaz (
	id					bigint not null constraint nfe_comunicacao_sefazpkey primary key,
	nfe_id   			bigint not null,
	tipo_comunicacao	varchar(5) 		 not null,
	cstat				varchar(5),
	nprotnfe			varchar(100),
	nrecibo				varchar(100),
	xmotivo				varchar(500),
	status				varchar(30),
	xml_envio			BYTEA,
	xml_retorno			BYTEA,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	constraint nfe_nfe_comunicacao_sefaz_id_foreign foreign key (nfe_id) references nfe (id)
);