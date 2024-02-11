create sequence seq_parceiro_local_endereco;

create table parceiro_local_endereco (
    id 					bigint       not null constraint parceiro_local_endereco_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    tipo_endereco		varchar(3) NOT NULL,
    identificacao		varchar(120) NOT NULL,
    cep 				varchar(8),
    cidade_id 			bigint,
    endereco 			varchar(250) NOT NULL,
    complemento 		varchar(120),
    numero 				varchar(10),
    bairro 				varchar(80),
    pais_id 			bigint,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_endereco_parceiro_local_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES parceiro_local (id)
);