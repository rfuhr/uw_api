create sequence seq_parceiro_local_propriedade;

create table parceiro_local_propriedade (
    id 					bigint       not null constraint parceiro_local_propriedade_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    identificacao		varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_propriedade_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);