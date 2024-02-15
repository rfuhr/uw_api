create sequence seq_linha;

create table linha (
	id					bigint not null constraint linha_pkey primary key,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

