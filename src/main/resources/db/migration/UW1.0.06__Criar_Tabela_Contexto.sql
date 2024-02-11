create sequence seq_contexto;

create table contexto (
	id					bigint       not null constraint contexto_pkey primary key,
	usuario_id      	bigint       not null,
	empresa_id          bigint       null,
	empresa_filial_id   bigint       null,
	modulo_id           bigint       null,
	CONSTRAINT contexto_usuario_id_foreign FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT contexto_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial(id),
	CONSTRAINT contexto_empresa_id_foreign FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT contexto_modulo_id_foreign FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);