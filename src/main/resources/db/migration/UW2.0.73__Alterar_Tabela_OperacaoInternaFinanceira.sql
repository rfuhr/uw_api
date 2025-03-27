alter table operacao_interna_financeiro add column carteira_financeira_id bigint not null default 1;
alter table operacao_interna_financeiro add column fato_gerador_id bigint not null default 1;
alter table operacao_interna_financeiro add  CONSTRAINT operacao_interna_financeiro_carteira_financeira_id_foreign FOREIGN KEY (carteira_financeira_id) REFERENCES carteira_financeira (id);
alter table operacao_interna_financeiro add  CONSTRAINT operacao_interna_financeiro_fato_gerador_id_foreign FOREIGN KEY (fato_gerador_id) REFERENCES fato_gerador (id);