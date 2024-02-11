create sequence seq_funcionalidade;

create table funcionalidade (
	id					bigint       not null constraint funcionalidade_pkey primary key,
	modulo_id           bigint       not null,
	nome            	varchar(255) not null,
	tag            		varchar(30)  not null,
	crud 				boolean 	 not null,
	CONSTRAINT contexto_modulo_id_foreign FOREIGN KEY (modulo_id) REFERENCES modulo(id)
);

insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Manutenção de Usuários', 'usuario', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Manutenção de Perfis', 'perfil', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de País', 'pais', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'TAbela de Uf', 'uf', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de Cidade', 'cidade', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de Sexo', 'sexo', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de Profissão', 'profissao', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de Nacionalidade', 'nacionalidade', true);
insert into funcionalidade (id, modulo_id, nome, tag, crud) values (nextval('seq_funcionalidade'), 1, 'Tabela de Estado Civil', 'estadocivil', true);
