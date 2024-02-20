create sequence seq_plano_classificao_item;

create table plano_classificacao_item (
	id					bigint not null constraint plano_classificao_item_pk primary key,
	id_superior			bigint,
	codigo				varchar(50)  not null,
	nome				varchar(100) not null,
	nivel				integer not null,
	sintetica			boolean not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	CONSTRAINT superior_plano_classificacao_item_foreign FOREIGN KEY (id_superior) REFERENCES plano_classificacao_item (id)						
);
