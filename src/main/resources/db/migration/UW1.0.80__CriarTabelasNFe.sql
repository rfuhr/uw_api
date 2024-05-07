create sequence seq_nfe;

create table nfe (
	id					bigint not null constraint nfe_pkey primary key,
	empresa_filial_id   bigint not null,
	chave_nfe			varchar(47) not null,
	cstat				varchar(5),
	crejeicao			varchar(5),
	cdenegado			varchar(5),
	nprotnfe			varchar(100),
	nrecibo				varchar(100),
	xmotivo				varchar(500),
	xml					BYTEA,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,	
	constraint empresa_filial_nfe_id_foreign foreign key (empresa_filial_id) references empresa_filial (id)
);

create sequence seq_ide_nfe;

create table ide_nfe (
	id					bigint not null constraint ide_nfe_pkey primary key,
	nfe_id				bigint not null,
	cuf 				integer not null,
	cnf					integer not null,
	natop				varchar(60) not null,
	mod					integer not null,
	serie				integer not null,
	nnf					integer not null,
	dhemi				timestamp with time zone not null,
	dhsaient			timestamp with time zone not null,
	tpnf				integer,
	iddest				integer,
	cmunfg				integer,
	tpimp				integer not null,
	tpemis				integer not null,
	cdv					integer not null,
	tpamb				integer not null,
	finnfe				integer,
	indfinal			integer,
	indpres				integer,
	indintermed			integer,
	procemi				integer not null,
	verproc				varchar(20) not null,
	dhcont				timestamp with time zone,
	xjust				varchar(256),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_ide_nfe_foreign foreign key (nfe_id) references nfe (id)
);

create sequence seq_nfe_ref;

create table nfe_ref (
	id					bigint not null constraint nfe_ref_pkey primary key,
	ide_nfe_id			bigint not null,
	refnfe				varchar(44),
	cuf					integer,
	aamm				integer,
	cnpj				varchar(14),
	mod					integer,
	serie				integer,
	nnf					integer,
	cpf					varchar(11),
	ie					varchar(14),
	refcte				varchar(44),
	necf				varchar(3),
	ncoo				varchar(6),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint ide_nfe_id_nfe_ref_foreign foreign key (ide_nfe_id) references ide_nfe (id)
);

create sequence seq_emit_nfe;

create table emit_nfe (
	id					bigint not null constraint emit_nfe_pkey primary key,
	nfe_id				bigint not null,
	cnpj				varchar(14),
	cpf					varchar(11),
	xnome				varchar(60),
	xfant				varchar(60),
	xlgr				varchar(60),
	nro					varchar(60),
	xcpl				varchar(60),
	xbairro				varchar(60),
	cmun				integer,
	xmun				varchar(60),
	uf					varchar(2),
	cep					varchar(8),
	cpais				varchar(4),
	xpais				varchar(60),
	fone				varchar(14),
	ie					varchar(14),
	iest				varchar(14),
	im					varchar(15),
	cnae				varchar(8),
	crt					integer,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_emit_nfe_foreign foreign key (nfe_id) references nfe (id)	
);

create sequence seq_dest_nfe;

create table dest_nfe (
	id					bigint not null constraint dest_nfe_pkey primary key,
	nfe_id				bigint not null,
	cnpj				varchar(14),
	cpf					varchar(11),
	idestrangeiro		varchar(20),
	xnome				varchar(60),
	xlgr				varchar(60),
	nro					varchar(60),
	xcpl				varchar(60),
	xbairro				varchar(60),
	cmun				integer,
	xmun				varchar(60),
	uf					varchar(2),
	cep					varchar(8),
	cpais				varchar(4),
	xpais				varchar(60),
	fone				varchar(14),
	indiedest			integer,
	ie					varchar(14),
	isuf				varchar(9),
	im					varchar(15),
	email				varchar(60),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_dest_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_local_retirada_nfe;

create table local_retirada_nfe (
	id					bigint not null constraint local_retirada_nfe_pkey primary key,
	nfe_id				bigint not null,
	cnpj				varchar(14),
	cpf					varchar(11),
	xnome				varchar(60),
	xlgr				varchar(60),
	nro					varchar(60),
	xcpl				varchar(60),
	xbairro				varchar(60),
	cmun				integer,
	xmun				varchar(60),
	uf					varchar(2),
	cep					varchar(8),
	cpais				varchar(4),
	xpais				varchar(60),
	fone				varchar(14),
	email				varchar(60),
	ie					varchar(14),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_local_retirada_nfe_foreign foreign key (nfe_id) references nfe (id)	
);

create sequence seq_local_entrega_nfe;

create table local_entrega_nfe (
	id					bigint not null constraint local_entrega_nfe_pkey primary key,
	nfe_id				bigint not null,
	cnpj				varchar(14),
	cpf					varchar(11),
	xnome				varchar(60),
	xlgr				varchar(60),
	nro					varchar(60),
	xcpl				varchar(60),
	xbairro				varchar(60),
	cmun				integer,
	xmun				varchar(60),
	uf					varchar(2),
	cep					varchar(8),
	cpais				varchar(4),
	xpais				varchar(60),
	fone				varchar(14),
	email				varchar(60),
	ie					varchar(14),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_local_entrega_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_aut_xml_nfe;

create table aut_xml_nfe (
	id					bigint not null constraint aut_xml_nfe_pkey primary key,
	nfe_id				bigint not null,
	cnpj				varchar(14),
	cpf					varchar(11),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_aut_xml_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_det_item_nfe;

create table det_item_nfe (
	id					bigint not null constraint det_item_nfe_pkey primary key,
	nfe_id				bigint not null,
	nitem				integer not null,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint nfe_id_det_item_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_prod_item_nfe;

create table prod_item_nfe (
	id					bigint not null constraint prod_item_nfe_pkey primary key,
	det_item_nfe_id		bigint not null,
	cprod				varchar(60),
	cean				varchar(14),
	xprod				varchar(120),
	ncm					varchar(8),
	nve					varchar(6),
	cest				varchar(7),
	indescala			varchar(1),
	cnpjfab				varchar(14),
	cbenef				varchar(10),
	extipi				varchar(3),
	cfop				varchar(4),
	ucom				varchar(6),
	qcom				decimal(15,5),
	vuncom				decimal(20,10),
	vprod				decimal(15,5),
	ceantrib			varchar(14),
	utrib				varchar(6),
	qtrib				decimal(15,5),
	vuntrib				decimal(20,10),
	vfrete				decimal(15,5),
	vseg				decimal(15,5),
	vdesc				decimal(15,5),
	voutro				decimal(15,5),
	indtot				integer,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint det_item_nfe_id_prod_item_nfe_foreign foreign key (det_item_nfe_id) references det_item_nfe (id)		
);
	
create sequence seq_di_item_nfe;

create table di_item_nfe (
	id					bigint not null constraint di_item_nfe_pkey primary key,
	prod_item_nfe_id	bigint not null,	
	ndi					varchar(12),
	ddi					varchar(10),
	xlocdesemb			varchar(60),
	ufdesemb			varchar(2),
	ddesemb				varchar(10),
	tpviatransp			integer,
	vafrmm				decimal(15,5),
	tpintermedio		integer,
	cnpj				varchar(14),
	ufterceiro			varchar(2),
	cexportador			varchar(60),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint prod_item_nfe_id_prod_item_nfe_foreign foreign key (prod_item_nfe_id) references prod_item_nfe (id)			
);

create sequence seq_adicao_di_item_nfe;

create table adicao_di_item_nfe (
	id					bigint not null constraint adicao_di_item_nfe_pkey primary key,
	di_item_nfe_id		bigint not null,	
	nadicao				integer,
	nseqadic			integer,
	cfabricante			varchar(60),
	vdescdi				decimal(15,2),
	ndraw				varchar(11),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint adicao_di_item_nfe_id_di_item_nfe_foreign foreign key (di_item_nfe_id) references di_item_nfe (id)	
);

create sequence seq_export_item_nfe;

create table export_item_nfe (
	id					bigint not null constraint export_item_nfe_pkey primary key,
	prod_item_nfe_id	bigint not null,
	ndraw				varchar(11),
	nre					varchar(12),
	chnfe				varchar(44),
	qexport				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint export_item_nfe_id_prod_item_nfe_foreign foreign key (prod_item_nfe_id) references prod_item_nfe (id)			
);

create sequence seq_ped_compra_item_nfe;

create table ped_compra_item_nfe (
	id					bigint not null constraint ped_compra_item_nfe_pkey primary key,
	prod_item_nfe_id	bigint not null,
	xped				varchar(15),
	nitemped			varchar(6),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint ped_compra_item_nfe_id_prod_item_nfe_foreign foreign key (prod_item_nfe_id) references prod_item_nfe (id)			
);

create sequence seq_grupo_div_item_nfe;

create table grupo_div_item_nfe (
	id					bigint not null constraint grupo_div_item_nfe_pkey primary key,
	prod_item_nfe_id	bigint not null,
	nfci				varchar(36),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint grupo_div_item_nfe_id_prod_item_nfe_foreign foreign key (prod_item_nfe_id) references prod_item_nfe (id)			
);

create sequence seq_rastreab_item_nfe;

create table rastreab_item_nfe (
	id					bigint not null constraint rastreab_item_nfe_pkey primary key,
	prod_item_nfe_id	bigint not null,
	nlote				varchar(20),
	qlote				decimal(15,5),
	dfab				date,
	dval				date,
	cagreg				varchar(20),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint rastreab_item_nfe_id_prod_item_nfe_foreign foreign key (prod_item_nfe_id) references prod_item_nfe (id)			
);

create sequence seq_impostos_item_nfe;

create table impostos_item_nfe (
	id					bigint not null constraint impostos_nfe_pkey primary key,
	det_item_nfe_id		bigint not null,
	vtottrib			decimal(20,10),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint impostos_item_nfe_id_det_item_nfe_foreign foreign key (det_item_nfe_id) references det_item_nfe (id)			
);

create sequence seq_icms_item_nfe;

create table icms_item_nfe (
	id						bigint not null constraint icms_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	cst						varchar(2),
	orig					integer,
	modbc					integer,
	vbc						decimal(15,5),
	picms					decimal(15,5),
	vicms					decimal(15,5),
	vbcfcp					decimal(15,5),
	pfcp					decimal(15,5),
	vfcp					decimal(15,5),
	modbcst					integer,
	pmvast					decimal(15,5),
	predbcst				decimal(15,5),
	vbcst					decimal(15,5),
	picmsst					decimal(15,5),
	vicmsst					decimal(15,5),
	vbcfcpst				decimal(15,5),
	pfcpst					decimal(15,5),
	vfcpst					decimal(15,5),
	predbc					decimal(15,5),
	vicmsdeson				decimal(15,5),
	motdesicms				integer,
	vicmsop					decimal(15,5),
	pdif					decimal(15,5),
	vicmsdif				decimal(15,5),
	vbcstret				decimal(15,5),
	pst						decimal(15,5),
	vicmssubstituto			decimal(15,5),
	vicmsstret				decimal(15,5),
	vbcfcpstret				decimal(15,5),
	pfcpstret				decimal(15,5),
	vfcpstret				decimal(15,5),
	predbcefet				decimal(15,5),
	vbcefet					decimal(15,5),
	picmsefet				decimal(15,5),
	vicmsefet				decimal(15,5),
	csosn					integer,
	pcredsn					decimal(15,5),
	vcredicmssn				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint icms_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)		
	
);

create sequence seq_icms_uf_dest_item_nfe;

create table icms_uf_dest_item_nfe (
	id						bigint not null constraint icms_uf_dest_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	vbcufdest				decimal(15,5),
	vbcfcpufdest			decimal(15,5),
	pfcpufdest				decimal(15,5),
	picmsufdest				decimal(15,5),
	picmsinter				decimal(15,5),
	picmsinterpart			decimal(15,5),
	vfcpufdest				decimal(15,5),
	vicmsufdest				decimal(15,5),
	vicmsufremet			decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone 	 not null,
	constraint icms_uf_dest_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)				
);

create sequence seq_ipi_item_nfe;

create table ipi_item_nfe (
	id						bigint not null constraint ipi_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	cnpjprod				varchar(14),
	cselo					varchar(60),
	qselo					integer,
	cenq					integer,
	cst						varchar(2),
	vbc						decimal(15,5),
	pipi					decimal(15,5),
	qunid					decimal(15,5),
	vunid					decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint ipi_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)				
);

create sequence seq_ii_item_nfe;

create table ii_item_nfe (
	id						bigint not null constraint ii_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	vbc						decimal(15,5),
	vdespadu				decimal(15,5),
	vii						decimal(15,5),
	viof					decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint ii_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)				
);

create sequence seq_pis_item_nfe;

create table pis_item_nfe (
	id						bigint not null constraint pis_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	cst						varchar(2),
	vbc						decimal(15,5),
	ppis					decimal(15,5),
	vpis					decimal(15,5),
	qbcprod					decimal(15,5),
	valiqprod				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint pis_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)				
);

create sequence seq_cofins_item_nfe;

create table cofins_item_nfe (
	id						bigint not null constraint cofins_item_nfe_pkey primary key,
	impostos_item_nfe_id	bigint not null,
	cst						varchar(2),
	vbc						decimal(15,5),
	pcofins					decimal(15,5),
	vcofins					decimal(15,5),
	qbcprod					decimal(15,5),
	valiqprod				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint cofins_item_nfe_id_impostos_item_nfe_foreign foreign key (impostos_item_nfe_id) references impostos_item_nfe (id)				
);
	
	
create sequence seq_trib_incid_item_nfe;

create table trib_incid_item_nfe (
	id					bigint not null constraint trib_incid_item_nfe_pkey primary key,
	det_item_nfe_id		bigint not null,
	pdevol				decimal(15,5),
	vipidevol			decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint trib_incid_item_nfe_id_det_item_nfe_foreign foreign key (det_item_nfe_id) references det_item_nfe (id)				
);

create sequence seq_info_adic_item_nfe;

create table info_adic_item_nfe (
	id					bigint not null constraint info_adic_item_nfe_pkey primary key,
	det_item_nfe_id		bigint not null,
	infadprod			varchar(500),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint info_adic_item_nfe_id_det_item_nfe_foreign foreign key (det_item_nfe_id) references det_item_nfe (id)
);

create sequence seq_totais_nfe;

create table totais_nfe (
	id					bigint not null constraint totais_nfe_pkey primary key,
	nfe_id				bigint not null,
	vbc					decimal(15,5),
	vicms				decimal(15,5),
	vicmsdeson			decimal(15,5),
	vfcpufdest			decimal(15,5),
	vicmsufdest			decimal(15,5),
	vicmsufremet		decimal(15,5),
	vfcp				decimal(15,5),
	vbcst				decimal(15,5),
	vst					decimal(15,5),
	vfcpst				decimal(15,5),
	vfcpstret			decimal(15,5),
	vprod				decimal(15,5),
	vfrete				decimal(15,5),
	vseg				decimal(15,5),
	vdesc				decimal(15,5),
	vii					decimal(15,5),
	vipi				decimal(15,5),
	vipidevol			decimal(15,5),
	vpis				decimal(15,5),
	vcofins				decimal(15,5),
	voutro				decimal(15,5),
	vnf					decimal(15,5),
	vtottrib			decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint totais_nfe_id_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_transp_nfe;

create table transp_nfe (
	id					bigint not null constraint transp_nfe_pkey primary key,
	nfe_id				bigint not null,
	modfrete			integer,
	cnpj				varchar(14),
	cpf					varchar(11),
	xnome				varchar(60),
	ie					varchar(14),
	xender				varchar(60),
	xmun				varchar(60),
	uf					varchar(2),
	vserv				decimal(15,5),
	vbcret				decimal(15,5),
	picmsret			decimal(15,5),
	vicmsret			decimal(15,5),
	cfop				varchar(4),
	cmunfg				varchar(7),
	placa				varchar(7),
	ufplaca				varchar(2),
	rntc				varchar(20),
	vagao				varchar(20),
	balsa				varchar(20),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint transp_nfe_id_nfe_foreign foreign key (nfe_id) references nfe (id)			
);

create sequence seq_reb_transp_nfe;

create table reb_transp_nfe (
	id					bigint not null constraint reb_transp_nfe_pkey primary key,
	transp_nfe_id		bigint not null,
	placa				varchar(7),
	ufplaca				varchar(2),
	rntc				varchar(20),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint reb_nfe_id_transp_nfe_foreign foreign key (transp_nfe_id) references transp_nfe (id)		
);	

create sequence seq_vol_transp_nfe;

create table vol_transp_nfe (
	id					bigint not null constraint vol_transp_nfe_pkey primary key,
	transp_nfe_id	bigint not null,
	qvol				decimal(15,5),
	esp					varchar(60),
	marca				varchar(60),
	nvol				varchar(60),
	pesol				decimal(15,5),
	pesob				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint volume_nfe_id_transp_nfe_foreign foreign key (transp_nfe_id) references transp_nfe (id)			
);

create sequence seq_lacres_transp_nfe;

create table lacres_transp_nfe (
	id					bigint not null constraint lacres_transp_nfe_pkey primary key,
	vol_transp_nfe_id	bigint not null,
	nlacre				varchar(60),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint lacre_nfe_id_vol_transp_nfe_foreign foreign key (vol_transp_nfe_id) references vol_transp_nfe (id)		
);

create sequence seq_pagamentos_nfe;

create table pagamentos_nfe (
	id					bigint not null constraint pagamentos_nfe_pkey primary key,
	nfe_id				bigint not null,
	vtroco				decimal(15,5),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint pagamentos_nfe_id_nfe_foreign foreign key (nfe_id) references nfe (id)		
);

create sequence seq_det_pag_nfe;

create table det_pag_nfe (
	id					bigint not null constraint det_pag_nfe_pkey primary key,
	pagamentos_nfe_id	bigint not null,
	indpag				integer,
	tpag				varchar(2),
	vpag				decimal(15,5),
	tpintegra			integer,
	cnpj				varchar(14),
	tband				varchar(2),
	caut				varchar(20),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint det_pag_nfe_id_pagamentos_nfe_foreign foreign key (pagamentos_nfe_id) references pagamentos_nfe (id)
);

create sequence seq_info_adic_nfe;

create table info_adic_nfe (
	id					bigint not null constraint info_adic_nfe_pkey primary key,
	nfe_id				bigint not null,
	infadfisco			varchar(2000),
	infcpl				varchar(5000),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint info_adic_nfe_id_nfe_foreign foreign key (nfe_id) references nfe (id)
);

create sequence seq_obs_cont_info_adic_nfe;

create table obs_cont_info_adic_nfe (
	id					bigint not null constraint obs_cont_info_adic_nfe_pkey primary key,
	info_adic_nfe_id	bigint not null,
	xcampo				varchar(20),
	xtexto				varchar(60),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone		,
	constraint obs_cont_nfe_id_info_adic_nfe_foreign foreign key (info_adic_nfe_id) references info_adic_nfe (id)
);

create sequence seq_obs_fisco_info_adic_nfe;

create table obs_fisco_info_adic_nfe (
	id					bigint not null constraint obs_fisco_info_adic_nfe_pkey primary key,
	info_adic_nfe_id	bigint not null,
	xcampo				varchar(20),
	xtexto				varchar(60),
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint obs_fisco_nfe_id_info_adic_nfe_foreign foreign key (info_adic_nfe_id) references info_adic_nfe (id)
);

create sequence seq_proc_ref_info_adic_nfe;

create table proc_ref_info_adic_nfe (
	id					bigint not null constraint proc_ref_info_adic_nfe_pkey primary key,
	info_adic_nfe_id	bigint not null,
	nproc				varchar(60),
	indproc				integer,
	user_create     	bigint 		 not null,
	date_create			timestamp with time zone 	 not null,
	user_update     	bigint,
	date_update			timestamp with time zone,
	constraint proc_ref_nfe_id_info_adic_nfe_foreign foreign key (info_adic_nfe_id) references info_adic_nfe (id)		
);