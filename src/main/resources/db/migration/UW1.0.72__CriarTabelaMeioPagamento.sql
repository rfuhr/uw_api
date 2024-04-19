create table meio_pagamento (
	value				char(3) not null constraint meio_pagamento_pkey primary key,
	name				varchar(250) not null
);

insert into meio_pagamento values ('01', 'Dinheiro');
insert into meio_pagamento values ('02', 'Cheque');
insert into meio_pagamento values ('03', 'Cartão de Crédito');
insert into meio_pagamento values ('04', 'Cartão de Débito');
insert into meio_pagamento values ('05', 'Crédito Loja');
insert into meio_pagamento values ('10', 'Vale Alimentação');
insert into meio_pagamento values ('11', 'Vale Refeição');
insert into meio_pagamento values ('12', 'Vale Presente');
insert into meio_pagamento values ('13', 'Vale Combustível');
insert into meio_pagamento values ('15', 'Boleto Bancário');
insert into meio_pagamento values ('16', 'Depósito Bancário');
insert into meio_pagamento values ('17', 'PIX');
insert into meio_pagamento values ('18', 'Transferência Bancária, Carteira Digital');
insert into meio_pagamento values ('19', 'Program de Fidelidade, Cashback, Crédito Virtual');
insert into meio_pagamento values ('90', 'Sem Pagamento');
insert into meio_pagamento values ('99', 'Outros');
