alter table configuracao_fiscal_pis drop column modalidade_base_calculo;
alter table configuracao_fiscal_pis alter column tipo_calculo drop NOT NULL;
alter table configuracao_fiscal_pis alter column aliquota drop NOT NULL;