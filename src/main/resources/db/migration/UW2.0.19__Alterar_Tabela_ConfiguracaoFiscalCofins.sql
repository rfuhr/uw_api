alter table configuracao_fiscal_cofins drop column modalidade_base_calculo;
alter table configuracao_fiscal_cofins alter column tipo_calculo drop NOT NULL;
alter table configuracao_fiscal_cofins alter column aliquota drop NOT NULL;