create sequence seq_pais;

create table pais (
	id				bigint       not null constraint pais_pkey primary key,
	nome       		varchar(255) not null,
	sigla			varchar(3) 	 not null,
	codigo_ibge     varchar(4) 	 not null,
	codigo_siscomex varchar(3)   not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into pais (id, nome, sigla, codigo_ibge, codigo_siscomex, user_create, date_create, user_update, date_update) 
values (nextval('seq_pais'), 'Brasil', 'BRA', '1058', '105', 1, NOW()::timestamp, 1, NOW()::timestamp);