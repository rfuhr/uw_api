 select cp.id, cp.config_markup_item_id
from config_markup_item_indice cp
inner join config_markup_item cf on cp.config_markup_item_id = cf.id
where indice_markup_id = :indiceMarkupId 
and cf.item_id = :itemId
and ((:dataInicio between cp.data_inicio_vigencia and cp.data_final_vigencia) 
     or (:dataFinal between cp.data_inicio_vigencia and cp.data_final_vigencia)
     or (:dataInicio <= cp.data_inicio_vigencia and :dataFinal >= cp.data_inicio_vigencia))
and (1 = :validaId or cp.id <> :id)                