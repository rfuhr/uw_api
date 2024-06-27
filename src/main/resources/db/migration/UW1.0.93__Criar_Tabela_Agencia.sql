create sequence seq_agencia;

create table agencia (
	id					bigint not null constraint agencia_pkey primary key,
	banco_id            bigint not null,
	codigo				varchar(10) not null,
	dv					char(1) not null,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT agencia_banco_id_foreign FOREIGN KEY (banco_id) REFERENCES banco(id)
);

insert into agencia (id, banco_id, codigo, dv, nome, user_create, date_create, user_update, date_update)
 values (nextval('seq_agencia'), (select id from banco where codigo = '0' limit 1), 0, 0, 'Conta Caixa', 1, NOW()::timestamp, 1, NOW()::timestamp); 