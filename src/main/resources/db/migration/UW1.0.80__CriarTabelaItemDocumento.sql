create sequence seq_documento_item;

create table documento_item (
	id					bigint not null constraint documento_item_pkey primary key,
	documento_id     	bigint not null,
	item_id				bigint not null,
	quantidade			decimal(15,5) not null default 0,
	valor_unitario		decimal(15,5) not null default 0,
	valor_bruto			decimal(15,5) not null default 0,
	perc_desconto		decimal(15,5) not null default 0,
	valor_desconto		decimal(15,5) not null default 0,
	valor_frete			decimal(15,5) not null default 0,
	valor_seguro		decimal(15,5) not null default 0,
	valor_outrasdesp	decimal(15,5) not null default 0,
	valor_liquido   	decimal(15,5) not null default 0,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT documento_documento_item_id_foreign FOREIGN KEY (documento_id) REFERENCES documento (id),
	CONSTRAINT item_documento_item_id_foreign FOREIGN KEY (item_id) REFERENCES public.item(id)
);