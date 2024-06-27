with itens as (
    select i.id, i.codigo, i.nome, um.sigla as unidadeMedidaSigla 
    from item i inner join unidade_medida um on i.unidade_medida_comercial_id = um.id
    where i.id = :itemId
),
configuracao as (
    select ccp.id, ccp.operacao_interna_id, ccp.empresa_filial_id, ccp.grupo_contabil_id, ccp.dias_busca_precos,
           ccp.aplica_indices_markup, ccp.aplica_percentual_fixo, ccp.percentual
    from config_calculo_preco ccp
    where ccp.tipo_preco_id = :tipoPrecoId and current_date between ccp.data_inicio_vigencia and ccp.data_final_vigencia
),
valores as (
    select me.item_id,
           SUM( CASE oe.credito_debito WHEN 'C' THEN me.quantidade WHEN 'D' THEN -me.quantidade ELSE 0 END) as quantidade,
           SUM( CASE oe.credito_debito WHEN 'C' THEN me.valor WHEN 'D' THEN -me.valor ELSE 0 END) as valor
    from configuracao, movimento_estoque me 
       inner join config_calculo_preco_oper_interna ci on me.operacao_interna_id = ci.operacao_interna_id
       inner join operacao_estoque oe on ci.operacao_estoque = oe.value 
       inner join itens i on i.id = me.item_id  
    where me.grupo_contabil_id = configuracao.grupo_contabil_id and ci.config_calculo_preco_id = configuracao.id
          and me.data between (current_date - (configuracao.dias_busca_precos * INTERVAL '1 day')) and current_date
    group by me.item_id
)
select i.id as itemId, i.codigo as itemCodigo, i.nome as itemNome, i.unidadeMedidaSigla,
       valores.valor / valores.quantidade as precoBase,
       configuracao.id as configCalculoPrecoId, configuracao.empresa_filial_id as empresaFilialId,
       configuracao.operacao_interna_id as operacaoInternaId, configuracao.grupo_contabil_id as grupoContabilId,
       configuracao.dias_busca_precos as diasBuscaPrecos, configuracao.aplica_indices_markup as aplicaIndicesMarkup,
       configuracao.aplica_percentual_fixo as aplicaPercentualFixo, configuracao.percentual
from configuracao, itens i
   left join valores on i.id = valores.item_id