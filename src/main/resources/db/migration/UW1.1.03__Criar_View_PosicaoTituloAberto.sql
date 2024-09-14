create or replace view vw_fin_posicaotitulobaixados
AS
select 
	tituloid,
    parcelafinanceiraid,
    parcelafinanceirasequencia,
    tipotituloid,
    tipotitulocodigo,
    tipotitulonome,
    tipotitulosigla,
    parceiroid,
    parceironomerazaosocial,
    parceirolocalid,
    parceirolocalcpfcnpj,
    parceirolocalnome,
    caracteristicamovimentofinanceiroid,
    caracteristicamovimentofinanceirocodigo,
    caracteristicamovimentofinanceironome,
    caracteristicamovimentofinanceirosigla,
    departamentoid,
    departamentonome,
    departamentosigla,
    empresafilialid,
    empresafilialnome,
    empresafilialsigla,
    empresaid,
    empresanome,
    empresasigla,
    carteiraid,
    carteiracodigo,
    carteiranome,
    carteirasigla,
    grupofinanceirocodigo,
    grupofinanceironome,
    grupofinanceirosigla,
    parcelafinanceiradatavencimento,
    titulodatadocumento,
    movimentofinanceirodatamovimento,
    titulodocumento,
    titulonossonumero,
    parcelafinanceiranumero,
    fatogeradorcodigo,
    fatogeradornome,
    fatogeradorsigla,
    tituloobservacao,
    historicopadraocodigo,
    historicopadraonome,
    historicopadraosigla,
    SUM(valormovimento) as VALORMOVIMENTO,
	atraso,
    titulodatamovimentoinclusao,
    usuarionome,
    usuariousername,
    tipooperacaofinanceiraid,
    tipooperacaofinanceiranome,
    tipooperacaofinanceirasigla
from 
(
SELECT DISTINCT t.id AS tituloid,
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
    d.id AS departamentoid,
    d.nome AS departamentonome,
    d.sigla AS departamentosigla,
    ef.id AS empresafilialid,
    ef.nome AS empresafilialnome,
    ef.sigla AS empresafilialsigla,
    e.id AS empresaid,
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
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = false THEN mf.valor_movimento 
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = true THEN mf.valor_movimento * '-1'::integer::numeric
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = false THEN mf.valor_movimento * '-1'::integer::numeric
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = true THEN mf.valor_movimento
            ELSE NULL::numeric
        END AS valormovimento,
        CASE
            WHEN (PF.data_vencimento - mf.data_movimento) > 0 THEN 0
            ELSE pf.data_vencimento  - mf.data_movimento 
        END AS atraso,
        CASE
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = false THEN 'C'::text
            WHEN tt.indicador_dc = 'C'::bpchar AND cmf.inverte_indicador = true THEN 'D'::text
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = false THEN 'D'::text
            WHEN tt.indicador_dc = 'D'::bpchar AND cmf.inverte_indicador = true THEN 'C'::text
            ELSE NULL::text
        END AS indicadordc,
    vwfininctitulo.data_movimento_inclusao AS titulodatamovimentoinclusao,
    u.nome AS usuarionome,
    u2.username AS usuariousername,
    tof.id AS tipooperacaofinanceiraid,
    tof.nome AS tipooperacaofinanceiranome,
    tof.sigla AS tipooperacaofinanceirasigla
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
     JOIN tipo_operacao_financeira tof ON tof.id = mf.tipo_operacao_financeira_id
     JOIN vw_fin_infoinclusaotitulo vwfininctitulo ON vwfininctitulo.titulo_id = t.id
     JOIN usuario u ON u.user_id = mf.user_create
     JOIN users u2 ON u2.id = mf.user_create
     LEFT JOIN historico_padrao hp ON hp.id = t.historico_padrao_id
where MF.DATA_MOVIMENTO <= current_date 
and   tof.IDN_LISTA_POSTITBAIXA = true
) as VALORES
group by 
tituloid,
    parcelafinanceiraid,
    parcelafinanceirasequencia,
    tipotituloid,
    tipotitulocodigo,
    tipotitulonome,
    tipotitulosigla,
    parceiroid,
    parceironomerazaosocial,
    parceirolocalid,
    parceirolocalcpfcnpj,
    parceirolocalnome,
    caracteristicamovimentofinanceiroid,
    caracteristicamovimentofinanceirocodigo,
    caracteristicamovimentofinanceironome,
    caracteristicamovimentofinanceirosigla,
    departamentoid,
    departamentonome,
    departamentosigla,
    empresafilialid,
    empresafilialnome,
    empresafilialsigla,
    empresaid,
    empresanome,
    empresasigla,
    carteiraid,
    carteiracodigo,
    carteiranome,
    carteirasigla,
    grupofinanceirocodigo,
    grupofinanceironome,
    grupofinanceirosigla,
    parcelafinanceiradatavencimento,
    titulodatadocumento,
    movimentofinanceirodatamovimento,
    titulodocumento,
    titulonossonumero,
    parcelafinanceiranumero,
    fatogeradorcodigo,
    fatogeradornome,
    fatogeradorsigla,
    tituloobservacao,
    historicopadraocodigo,
    historicopadraonome,
    historicopadraosigla,
	atraso,
    titulodatamovimentoinclusao,
    usuarionome,
    usuariousername,
    tipooperacaofinanceiraid,
    tipooperacaofinanceiranome,
    tipooperacaofinanceirasigla





