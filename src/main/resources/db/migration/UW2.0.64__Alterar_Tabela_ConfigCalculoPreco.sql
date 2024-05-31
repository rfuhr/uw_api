alter table config_calculo_preco add column empresa_filial_id bigint;
alter table config_calculo_preco add column grupo_contabil_id bigint;
alter table config_calculo_preco add CONSTRAINT config_calculo_preco_empresa_filial_id_foreign FOREIGN KEY (empresa_filial_id) REFERENCES empresa_filial (id);
alter table config_calculo_preco add CONSTRAINT config_calculo_preco_grupo_contabil_id_foreign FOREIGN KEY (grupo_contabil_id) REFERENCES grupo_contabil (id);