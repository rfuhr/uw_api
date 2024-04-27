create sequence seq_documento;

create table documento (
	id					bigint not null constraint documento_pkey primary key,
	departamento_id     bigint not null,
	numero				bigint not null,
	data_documento		date   not null,
	operacao_interna_id bigint not null,
	parceiro_local_id   bigint,
	tipo_documento_id   bigint not null,
	origem_documento    bigint not null,
	documento_origem_id bigint,
	nfe_id				bigint,
	cancelado			boolean not null default false,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT departamento_documento_id_foreign FOREIGN KEY (departamento_id) REFERENCES departamento (id),
	CONSTRAINT documento_origem_documento_id_foreign FOREIGN KEY (documento_origem_id) REFERENCES public.documento(id),
	CONSTRAINT operacao_interna_documento_foreign FOREIGN KEY (operacao_interna_id) REFERENCES public.operacao_interna(id),
	CONSTRAINT parceiro_local_documento_id_foreign FOREIGN KEY (parceiro_local_id) REFERENCES public.parceiro_local(id),
	CONSTRAINT tipo_documento_documento_id_foreign FOREIGN KEY (tipo_documento_id) REFERENCES public.tipo_documento(id),
	CONSTRAINT nfe_documento_foreign FOREIGN KEY (nfe_id) REFERENCES public.nfe(id)
);