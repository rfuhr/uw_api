create sequence seq_perfil_funcionalidade;

create table perfil_funcionalidade (
	id					bigint not null constraint perfil_funcionalidade_pkey primary key,
	perfil_id       	bigint not null,
	funcionalidade_id	bigint not null,
	consultar			boolean,
	inserir				boolean,
	alterar				boolean,
	excluir				boolean,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT perfil_funcionalidade_perfil_id_foreign FOREIGN KEY (perfil_id) REFERENCES perfil(id),
	CONSTRAINT perfil_funcionalidade_funcionalidade_id_foreign FOREIGN KEY (funcionalidade_id) REFERENCES funcionalidade(id)
);
