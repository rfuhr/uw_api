create sequence seq_token;

create table token (
	id					bigint not null constraint token_pkey primary key,
	user_id				bigint not null,
	token				varchar(255) not null,
	refresh_token		varchar(255) not null,
	date_create			timestamp with time zone 	 not null
);