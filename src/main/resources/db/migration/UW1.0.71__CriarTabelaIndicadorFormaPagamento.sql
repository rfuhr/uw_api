create table indicador_forma_pagamento (
	value				char(3) not null constraint indicador_forma_pagamento_pkey primary key,
	name				varchar(250) not null
);

insert into indicador_forma_pagamento values ('1', 'Pagamento à Vista');
insert into indicador_forma_pagamento values ('2', 'Pagamento à Prazo');
