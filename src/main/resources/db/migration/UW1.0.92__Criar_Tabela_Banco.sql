create sequence seq_banco;

create table banco (
	id					bigint not null constraint banco_pkey primary key,
	codigo				varchar(10) not null,
	dv					char(1) not null,
	nome				varchar(100) not null,
	sigla               varchar(20)  not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into banco (id, codigo, dv, nome, sigla, user_create, date_create, user_update, date_update)
 values (nextval('seq_banco'), 0, 0, 'Conta Caixa', 'CX', 1, NOW()::timestamp, 1, NOW()::timestamp); 