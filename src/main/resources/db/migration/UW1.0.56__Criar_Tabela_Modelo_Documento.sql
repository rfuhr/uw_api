create sequence seq_tipo_documento;

create table tipo_documento (
	id					bigint not null constraint tipo_documento_pkey primary key,
	nome				varchar(100) not null,
	sigla				varchar(20) not null,
	codigo_receita		varchar(2),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Nota Fiscal Eletrônica','NFe', '55', 1, NOW()::timestamp,1,NOW()::timestamp); 
insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Nota Fiscal do Consumidor Eletrônica','NFCe', '65', 1, NOW()::timestamp,1,NOW()::timestamp);	
insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Nota Fiscal de Serviços Eletrônica','NFSe', null, 1, NOW()::timestamp,1,NOW()::timestamp);
insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Conhecimento de Transporte Eletrônico','CTe', '57', 1, NOW()::timestamp,1,NOW()::timestamp);				
insert into tipo_documento (id, nome, sigla, codigo_receita, user_create, date_create, user_update, date_update) 
	values (nextval('seq_tipo_documento'), 'Manifesto Eletrônico de Documentos Fiscais','MDFe', '58', 1, NOW()::timestamp,1,NOW()::timestamp);				