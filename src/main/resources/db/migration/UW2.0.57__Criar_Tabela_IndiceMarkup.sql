create sequence seq_indice_markup start with 1;

create table indice_markup (
	id					bigint not null constraint indice_markup_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	tributo			    boolean not null,
	tipo_tributo		varchar(80),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT indice_markup_tipo_tributo_foreign FOREIGN KEY (tipo_tributo) REFERENCES tipo_tributo (value)
);

insert into indice_markup (id, codigo, nome, tributo, tipo_tributo, user_create, date_create, user_update, date_update) values
 (nextval('seq_indice_markup'),1,'Despesas Administrativas', false, null, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_indice_markup'),2,'Margem de Lucro', false, null, 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_indice_markup'),3,'IPI', true, 'IPI', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_indice_markup'),4,'PIS', true, 'PIS', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_indice_markup'),5,'COFINS', true, 'COFINS', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_indice_markup'),6,'ICMS', true, 'ICMS', 1, NOW()::timestamp,1,NOW()::timestamp);