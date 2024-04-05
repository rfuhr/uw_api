create sequence seq_regime_tributario start with 1;

create table regime_tributario (
	id					bigint not null constraint regime_tributario_pkey primary key,
	codigo				integer not null,
	nome				varchar(250) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

insert into regime_tributario (id, codigo, nome, user_create, date_create, user_update, date_update) values
 (nextval('seq_regime_tributario'), 1, 'Simples Nacional', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_regime_tributario'), 2, 'Simples Nacional, excesso sublimite de receita bruta', 1, NOW()::timestamp,1,NOW()::timestamp),
 (nextval('seq_regime_tributario'), 3, 'Regime Normal', 1, NOW()::timestamp,1,NOW()::timestamp);