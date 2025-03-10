alter table cotacao_mercadoria_parceiro add column previsao_dias_entrega integer;
alter table cotacao_mercadoria_parceiro add column condicao_pagamento_id bigint;
alter table cotacao_mercadoria_parceiro add column meio_pagamento varchar(3);

alter table cotacao_mercadoria_parceiro add CONSTRAINT condpagto_cotacao_mercadoria_parceiro_foreign FOREIGN KEY (condicao_pagamento_id) REFERENCES condicao_pagamento (id);
alter table cotacao_mercadoria_parceiro add CONSTRAINT meiopagto_cotacao_mercadoria_parceiro_foreign FOREIGN KEY (meio_pagamento) REFERENCES meio_pagamento (value);

alter table cotacao_mercadoria_item add column quantidade_cotada decimal(15,5);
alter table cotacao_mercadoria_item add column valor_unitario decimal(15,2);

