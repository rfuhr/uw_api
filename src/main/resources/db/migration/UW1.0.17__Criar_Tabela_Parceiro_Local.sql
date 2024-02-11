create sequence seq_parceiro_local;

create table parceiro_local (
    id 					bigint       not null constraint parceiro_local_pkey primary key,
    parceiro_id 		bigint NOT NULL,
    cpf_cnpj 			varchar(14) NOT NULL,
    nome_local 			varchar(120) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_cidade_id_foreign FOREIGN KEY (cidade_id) REFERENCES cidade (id),
    CONSTRAINT parceiro_local_pais_id_foreign FOREIGN KEY (pais_id) REFERENCES pais (id),
    CONSTRAINT parceiro_local_parceiro_id_foreign FOREIGN KEY (parceiro_id) REFERENCES parceiro (id)
);