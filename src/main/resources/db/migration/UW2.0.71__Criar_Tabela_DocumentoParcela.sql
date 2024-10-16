create sequence seq_documento_parcela start with 1;

create table documento_parcela (
	id						bigint not null constraint documento_parcela_pkey primary key,
	documento_id			bigint not null,
	meio_pagamento          varchar(2) not null,
	indicador_forma_pagamento varchar(2) not null,
	numero	 				integer not null,
	data_vencimento		 	date NOT NULL,
	valor					decimal(15,2) NOT NULL,
	user_create     		bigint 		 not null,
	date_create				timestamp with time zone 	 not null,
	user_update     		bigint,
	date_update				timestamp with time zone,
	CONSTRAINT documento_parcela_documento_id_foreign FOREIGN KEY (documento_id) REFERENCES documento (id)
);