CREATE OR REPLACE VIEW public.vw_fin_posicaotituloaberto
AS SELECT distinct t.id AS tituloid,
    pf.id AS parcelafinanceiraid,
    pf.seq_parcela AS parcelafinanceirasequencia,
    mf.id AS movimentofinanceiroid,
    mf.seq_mvto AS movimentofinanceirosequencia,
    tt.id AS tipotituloid,
    tt.codigo AS tipotitulocodigo,
    tt.nome AS tipotitulonome,
    tt.sigla AS tipotitulosigla,
    p.id AS parceiroid,
    p.nome_razao_social AS parceironomerazaosocial,
    pl.id AS parceirolocalid,
    pl.cpf_cnpj AS parceirolocalcpfcnpj,
    pl.nome_local AS parceirolocalnome,
    cmf.id AS caracteristicamovimentofinanceiroid,
    cmf.codigo AS caracteristicamovimentofinanceirocodigo,
    cmf.nome AS caracteristicamovimentofinanceironome,
    cmf.sigla AS caracteristicamovimentofinanceirosigla,
    d.id as departamentoid,
    d.nome AS departamentonome,
    d.sigla AS departamentosigla,
    ef.id as empresafilialid,
    ef.nome AS empresafilialnome,
    ef.sigla AS empresafilialsigla,
    e.id as empresaid,
    e.nome AS empresanome,
    e.sigla AS empresasigla,
    cf.id AS carteiraid,
    cf.codigo AS carteiracodigo,
    cf.nome AS carteiranome,
    cf.sigla AS carteirasigla,
    gf.codigo AS grupofinanceirocodigo,
    gf.nome AS grupofinanceironome,
    gf.sigla AS grupofinanceirosigla,
    pf.data_vencimento AS parcelafinanceiradatavencimento,
    t.data_documento AS titulodatadocumento,
    mf.data_movimento AS movimentofinanceirodatamovimento,
    t.documento AS titulodocumento,
    t.nosso_numero AS titulonossonumero,
    pf.num_parcela AS parcelafinanceiranumero,
    fg.codigo AS fatogeradorcodigo,
    fg.nome AS fatogeradornome,
    fg.sigla AS fatogeradorsigla,
    t.observacao AS tituloobservacao,
    hp.codigo AS historicopadraocodigo,
    hp.nome AS historicopadraonome,
    hp.sigla AS historicopadraosigla,
        CASE
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = false THEN mf.saldo_parcela
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = true THEN mf.saldo_parcela * '-1'::integer::numeric
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = false THEN mf.saldo_parcela * '-1'::integer::numeric
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = true THEN mf.saldo_parcela
            ELSE NULL::numeric
        END AS valoressaldoparcela,
        CASE
            WHEN (CURRENT_DATE - pf.data_vencimento) < 0 THEN 0
            ELSE CURRENT_DATE - pf.data_vencimento
        END AS atraso,
        CASE
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = false THEN 'C'::text
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = true THEN 'D'::text
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = false THEN 'D'::text
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = true THEN 'C'::text
            ELSE NULL::text
        END AS indicadordc,
    vwfininctitulo.data_movimento_inclusao AS titulodatamovimentoinclusao,
    u.nome as usuarioNome,
    u2.username as usuarioUsername,
    tof.id as tipoOperacaoFinanceiraId,
    tof.nome as tipoOperacaoFinanceiraNome,
    tof.sigla  as tipoOperacaoFinanceiraSigla
   FROM movimento_financeiro mf
     JOIN parcela_financeiro pf ON pf.id = mf.parcela_id
     JOIN titulo t ON t.id = pf.titulo_id
     JOIN tipo_titulo tt ON tt.id = t.tipo_titulo_id
     JOIN caracteristica_movimento_financeiro cmf ON cmf.id = t.caracteristica_movimento_financeiro_id
     JOIN carteira_financeira cf ON cf.id = mf.carteira_financeira_id
     JOIN parceiro_local pl ON pl.id = t.parceiro_local_id
     JOIN parceiro p ON p.id = pl.parceiro_id
     JOIN departamento d ON d.id = t.departamento_id
     JOIN empresa_filial ef ON ef.id = d.empresa_filial_id
     JOIN empresa e ON e.id = ef.empresa_id
     JOIN grupo_financeiro gf ON gf.id = t.grupo_financeiro_id
     JOIN fato_gerador fg ON fg.id = t.fato_gerador_id
     join tipo_operacao_financeira tof on tof.id = mf.tipo_operacao_financeira_id 
     JOIN vw_fin_infoinclusaotitulo vwfininctitulo ON vwfininctitulo.titulo_id = t.id
     join usuario u on u.user_id = mf.user_create 
     join users u2 on u2.id = mf.user_create 
     LEFT JOIN historico_padrao hp ON hp.id = t.historico_padrao_id
  WHERE pf.ult_seq_mvto = mf.seq_mvto 
  		AND cf.lista_pos_titulo = true 
  		AND pf.seq_parcela = (( SELECT max(pfmax.seq_parcela) AS max
           FROM parcela_financeiro pfmax
          WHERE pfmax.titulo_id = t.id AND pfmax.num_parcela = pf.num_parcela))
        and mf.saldo_parcela <> 0;