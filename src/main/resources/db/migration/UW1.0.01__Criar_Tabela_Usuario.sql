create sequence seq_usuario start with 2;

create table usuario (
	id				bigint       not null constraint usuario_pkey primary key,
	nome   			varchar(120) not null,
	email			varchar(120) not null,
	user_id     	bigint 		 not null,
	admin           boolean      not null default false,
	ativo           boolean      not null default true,
	user_create     bigint 		 not null,
	date_create		timestamp with time zone 	 not null,
	user_update     bigint,
	date_update		timestamp with time zone
);

insert into usuario (id, nome, email, user_id, admin, ativo, user_create, date_create, user_update, date_update) values (1, 'Administrador', 'admin@naodefinido.com.br', 1, true, true, 1, current_timestamp AT TIME ZONE 'America/Sao_Paulo', 1, current_timestamp AT TIME ZONE 'America/Sao_Paulo');