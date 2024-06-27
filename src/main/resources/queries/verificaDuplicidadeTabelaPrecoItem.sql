 select tpi.id, tp.codigo
from tabela_preco_item tpi
inner join tabela_preco tp on tpi.tabela_preco_id = tp.id
where tpi.item_id = :itemId 
and tp.tipo_preco_id = :tipoPrecoId
and ((:dataInicio between tpi.data_inicio_vigencia and tpi.data_final_vigencia) 
     or (:dataFinal between tpi.data_inicio_vigencia and tpi.data_final_vigencia)
     or (:dataInicio <= tpi.data_inicio_vigencia and :dataFinal >= tpi.data_inicio_vigencia)) 
and (1 = :validaId or tpi.id <> :id)        