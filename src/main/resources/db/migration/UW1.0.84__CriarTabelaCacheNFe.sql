create sequence seq_cache_nfe;

create table cache_nfe (
	id					bigint not null constraint cache_nfe_pkey primary key,
	nfe_id				bigint not null,
	cache     			bytea  not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	CONSTRAINT nfe_cache_foreign FOREIGN KEY (nfe_id) REFERENCES nfe (id)
);