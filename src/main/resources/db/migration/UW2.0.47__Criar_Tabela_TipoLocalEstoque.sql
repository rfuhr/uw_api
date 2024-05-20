create sequence seq_tipo_local_estoque start with 1;

create table tipo_local_estoque (
	id					bigint not null constraint tipo_local_estoque_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_local_estoque (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_tipo_local_estoque'),1,'Filial',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_local_estoque'),2,'Terceiros',  1, NOW()::timestamp,1,NOW()::timestamp);