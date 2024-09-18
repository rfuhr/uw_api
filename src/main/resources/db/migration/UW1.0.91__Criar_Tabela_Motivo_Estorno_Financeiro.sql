create sequence seq_motivo_estorno_financeiro;

create table motivo_estorno_financeiro (
	id					bigint not null constraint motivo_estorno_financeiro_pkey primary key,
	nome				varchar(100) not null,
	sigla               varchar(20)  not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

