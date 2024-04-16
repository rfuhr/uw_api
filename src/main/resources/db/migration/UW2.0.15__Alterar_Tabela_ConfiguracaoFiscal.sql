alter table configuracao_fiscal drop column entrada_saida;
alter table configuracao_fiscal add column indicador_operacao varchar(1) NOT NULL;
alter table configuracao_fiscal add CONSTRAINT config_fiscal_indicador_operacao_id_foreign FOREIGN KEY (indicador_operacao) REFERENCES indicador_operacao (value);