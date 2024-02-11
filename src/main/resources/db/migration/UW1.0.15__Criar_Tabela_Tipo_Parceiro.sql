create sequence seq_tipo_parceiro;

create table tipo_parceiro (
	id					bigint       not null constraint tipo_parceiro_pkey primary key,
	codigo      		integer not null,
	nome			 	varchar(120) NOT NULL,
	is_cliente 			boolean NOT NULL DEFAULT false,
    is_empresa 			boolean NOT NULL DEFAULT false,
    is_fornecedor 		boolean NOT NULL DEFAULT false,
    is_produtor_rural	boolean NOT NULL DEFAULT false,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_parceiro (id, codigo, nome, is_empresa, is_cliente, is_fornecedor, is_produtor_rural, user_create, date_create, user_update, date_update) 
values (nextval('seq_tipo_parceiro'), 1, 'Empresa', true, false, false, false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into tipo_parceiro (id, codigo, nome, is_empresa, is_cliente, is_fornecedor, is_produtor_rural, user_create, date_create, user_update, date_update) 
values (nextval('seq_tipo_parceiro'), 2, 'Fornecedor', false, false, true, false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into tipo_parceiro (id, codigo, nome, is_empresa, is_cliente, is_fornecedor, is_produtor_rural, user_create, date_create, user_update, date_update) 
values (nextval('seq_tipo_parceiro'), 3, 'Cliente', false, true, false, false, 1, NOW()::timestamp, 1, NOW()::timestamp);
insert into tipo_parceiro (id, codigo, nome, is_empresa, is_cliente, is_fornecedor, is_produtor_rural, user_create, date_create, user_update, date_update) 
values (nextval('seq_tipo_parceiro'), 4, 'Produtor Rural', false, false, false, true, 1, NOW()::timestamp, 1, NOW()::timestamp);
