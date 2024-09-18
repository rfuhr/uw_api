create sequence seq_safra;

create table safra (
	id					bigint not null constraint safra_pk primary key,
	nome				varchar(100) not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);

