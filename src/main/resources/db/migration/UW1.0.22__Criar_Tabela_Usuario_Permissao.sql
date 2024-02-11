create sequence seq_usuario_permissao;

create table usuario_permissao (
	id					bigint not null constraint usuario_permissao_pkey primary key,
	usuario_id       	bigint not null,
	empresa_id			bigint not null,
	filiais_id			varchar(250) not null,
	perfis_id			varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT usuario_permissao_usuario_id_foreign FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);