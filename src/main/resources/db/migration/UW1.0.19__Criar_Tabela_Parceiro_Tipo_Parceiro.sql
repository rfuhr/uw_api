create sequence seq_parceiro_tipo_parceiro;

create table parceiro_local_tipo_parceiro (
    id 					bigint       not null constraint parceiro_tipo_parceiro_pkey primary key,
    parceiro_local_id 	bigint NOT NULL,
    tipo_parceiro_id 	bigint NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
    CONSTRAINT parceiro_local_parceiro_id_foreign FOREIGN KEY (parceiro_id) REFERENCES parceiro (id),
    CONSTRAINT parceiro_local_tipo_parceiro_id_foreign FOREIGN KEY (tipo_parceiro_id) REFERENCES tipo_parceiro (id)
);
