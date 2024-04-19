create table tipo_integracao_pagamento (
	value				char(3) not null constraint tipo_integracao_pagamento_pkey primary key,
	name				varchar(250) not null
);

insert into tipo_integracao_pagamento values ('1', 'Automação da Empresa');
insert into tipo_integracao_pagamento values ('2', 'Equipamento POS');
