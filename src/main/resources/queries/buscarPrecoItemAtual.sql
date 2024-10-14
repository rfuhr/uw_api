select tpi.valor
from tabela_preco_item tpi
inner join tabela_preco tp on tpi.tabela_preco_id = tp.id
where tpi.item_id = :itemId 
and tp.promocional = false
and tp.grupo_contabil_id is null
and current_date between tpi.data_inicio_vigencia and tpi.data_final_vigencia