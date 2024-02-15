create sequence seq_usuario_autonomia;

create table usuario_autonomia (
	id					bigint not null constraint usuario_autonomia_pkey primary key,
	usuario_id       	bigint not null,
	empresa_id			bigint not null,
	filiais_id			varchar(250) not null,
	autonomia_id		bigint not null,
	liberado			boolean not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT usuario_funcionalidade_usuario_id_foreign FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT usuario_funcionalidade_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT usuario_funcionalidade_autonomia_id_foreign FOREIGN KEY (autonomia_id) REFERENCES autonomia(id)
);