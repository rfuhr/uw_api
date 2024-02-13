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