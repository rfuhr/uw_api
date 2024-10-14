select	oca.identificacao_documento_agricola,
		oca.ordem,
		oca.tipo_calculo_agricola_id,
		oca.data_inicio_vigencia,
		tca.base_calculo_agricola,
		tca.indicador_dc 
from	ordem_calculo_agricola oca
		join tipo_calculo_agricola tca on tca.id  = oca.tipo_calculo_agricola_id 
			and :dataBase between tca.data_inicio_vigencia and tca.data_final_vigencia 
where   oca.item_id = :itemId
and     oca.identificacao_documento_agricola = 
		(select oia.identificacao_documento_agricola  
		 from operacao_interna oi join operacao_interna_agricola oia on oia.operacao_interna_id = oi.id and oi.id = :operacaoInternaId )
and     oca.tipo_calculo_agricola_id = 
		(select tipo_calculo_agricola_id_romaneio  
		 from item_classificacao_agricola ica 
		 where ica.id  = :itemClassificacaoAgricolaId
		 and :dataBase between ica.data_inicio_vigencia and ica.data_final_vigencia)
and     :dataBase between oca.data_inicio_vigencia and oca.data_final_vigencia 
order by oca.ordem, oca.data_inicio_vigencia desc