create sequence seq_local_estoque start with 1;

create table local_estoque (
	id					bigint not null constraint local_estoque_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	filial_especifica	boolean not null,
	tipo_local_estoque_id	bigint not null,
	empresa_filial_id	bigint,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT local_estoque_tipo_local_estoque_id_foreign FOREIGN KEY (tipo_local_estoque_id) REFERENCES tipo_local_estoque (id),
	CONSTRAINT local_estoque_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id)
);

insert into local_estoque (id, codigo, nome, filial_especifica, tipo_local_estoque_id, empresa_filial_id, user_create, date_create, user_update, date_update) values
 (nextval('seq_local_estoque'),1,'GERAL', false, 1, null, 1, NOW()::timestamp,1,NOW()::timestamp);