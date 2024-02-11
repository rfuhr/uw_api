create sequence seq_usuario_funcionalidade;

create table usuario_funcionalidade (
	id					bigint not null constraint usuario_funcionalidade_pkey primary key,
	usuario_id       	bigint not null,
	empresa_id			bigint not null,
	filiais_id			varchar(250) not null,
	funcionalidade_id	bigint not null,
	liberado			boolean not null,
	consultar			boolean not null,
	inserir				boolean not null,
	alterar				boolean not null,
	excluir				boolean not null,
	user_create     	bigint not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT usuario_funcionalidade_usuario_id_foreign FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT usuario_funcionalidade_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT usuario_funcionalidade_func_id_foreign FOREIGN KEY (funcionalidade_id) REFERENCES funcionalidade(id)
);