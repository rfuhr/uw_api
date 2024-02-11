create sequence seq_parceiro_juridica;

create table parceiro_local_juridica (
    id 					bigint       not null constraint parceiro_juridica_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    inscricao_estadual	varchar(20),
    data_fundacao 		date,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_fisica_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);