with itens as (
    select i.id, i.codigo, i.nome, um.sigla as unidadeMedidaSigla, um.id as unidadeMedidaId
    from item i inner join unidade_medida um on i.unidade_medida_comercial_id = um.id
),
estoque as (
    select se.item_id, sum(saldo_quantidade) as quantidade
    from saldo_estoque se inner join itens i on se.item_id = i.id
    where empresa_filial_id = :empresaFilialId and grupo_contabil_id = :grupoContabilId 
        and se.data <= current_date
        and se.data = 
    (select max(s.data) from saldo_estoque s where s.item_id = se.item_id 
      and s.grupo_contabil_id = se.grupo_contabil_id and s.empresa_filial_id = se.empresa_filial_id
      and s.data <= current_date)
    group by se.item_id
),
preco as (
    select tpi.item_id, tpi.id, tp.promocional, tpi.valor, tpi.valor_atual, tpf.empresa_filial_id,
        ROW_NUMBER() OVER (
                PARTITION BY tpi.item_id
                ORDER BY tp.promocional DESC, tpi.data_inicio_vigencia DESC, tpf.empresa_filial_id desc, tpi.id DESC
            ) AS rn
    from tabela_preco tp 
    inner join tabela_preco_item tpi on tp.id = tpi.tabela_preco_id
    inner join itens i on i.id = tpi.item_id
    left join tabela_preco_empresa_filial tpf on tp.id = tpf.tabela_preco_id
    where (grupo_contabil_id is null or grupo_contabil_id = :grupoContabilId)
    and (tpf.id is null or tpf.empresa_filial_id = :empresaFilialId)
    and current_date between tpi.data_inicio_vigencia and tpi.data_final_vigencia
    
)
select i.*, e.quantidade, p.valor, p.promocional, p.valor_atual, p.id as tabela_preco_item_id
from itens i 
left join estoque e on i.id = e.item_id
left join preco p on i.id = p.item_id and rn = 1