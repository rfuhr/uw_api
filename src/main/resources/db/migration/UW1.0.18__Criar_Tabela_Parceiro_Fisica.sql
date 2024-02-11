create sequence seq_parceiro_fisica;

create table parceiro_local_fisica (
    id 					bigint       not null constraint parceiro_fisica_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    sexo_id 			bigint NOT NULL,
    rg 					varchar(40),
    nacionalidade_id 	bigint,
    profissao_id 		bigint,
    estado_civil_id 	bigint NOT NULL,
    data_nascimento 	date,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_fisica_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id),
    CONSTRAINT parceiro_fisica_sexo_id_foreign FOREIGN KEY (sexo_id) REFERENCES sexo (id),
    CONSTRAINT parceiro_fisica_nacionalidade_id_foreign FOREIGN KEY (nacionalidade_id) REFERENCES nacionalidade (id),
    CONSTRAINT parceiro_fisica_profissao_id_foreign FOREIGN KEY (profissao_id) REFERENCES profissao (id),
    CONSTRAINT parceiro_fisica_estado_civil_id_foreign FOREIGN KEY (estado_civil_id) REFERENCES estado_civil (id)
);