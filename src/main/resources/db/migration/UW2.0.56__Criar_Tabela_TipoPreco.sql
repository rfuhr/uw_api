create sequence seq_tipo_preco start with 1;

create table tipo_preco (
	id					bigint not null constraint tipo_preco_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	transferencia		boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_preco (id, codigo, nome, transferencia, user_create, date_create, user_update, date_update) values
 (nextval('seq_tipo_preco'),1,'Vendas', false, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_preco'),2,'Transferências', true, 1, NOW()::timestamp,1,NOW()::timestamp);