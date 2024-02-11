create sequence seq_parceiro;

create table parceiro (
	id					bigint       not null constraint parceiro_pkey primary key,
	nome_razao_social 	varchar(120) NOT NULL,
    nome_fantasia 		varchar(120) NOT NULL,
    tipo_pessoa 		varchar(2) NOT NULL,
    raiz_cnpj_cpf 		varchar(11) NOT NULL,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone
);