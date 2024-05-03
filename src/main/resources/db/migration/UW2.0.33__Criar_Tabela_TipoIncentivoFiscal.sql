create sequence seq_tipo_incentivo_fiscal start with 1;

create table tipo_incentivo_fiscal (
	id					bigint not null constraint tipo_incentivo_fiscal_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into tipo_incentivo_fiscal (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_tipo_incentivo_fiscal'),1,'Micro Empreendedor Individual',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_incentivo_fiscal'),2,'Orgões Públicos Federal',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_incentivo_fiscal'),3,'Orgões Públicos Municipal/Estadual',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_incentivo_fiscal'),4,'Simples Nacional',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_incentivo_fiscal'),5,'Simples Nacional Serviços',  1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_tipo_incentivo_fiscal'),6,'Zona Franca de Manaus',  1, NOW()::timestamp,1,NOW()::timestamp);