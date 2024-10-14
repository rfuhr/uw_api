select	ica.id,
		gca.id as grupoClassificacaoAgricolaId,
		ica.codigo,
		ica.nome,
		ica.tipo_calculo_agricola_id_romaneio  as tipoCalculoAgricolaIdRomaneio,
		vica.ordem_tela as ordem,
		gca.nome as grupoClassificacaoAgricolaNome,
		tca.nome as tipoCalculoAgricolaNomeRomaneio
from	valida_item_classificacao_agricola vica											
join 	item_classificacao_agricola ica on ica.id = vica.item_classificacao_agricola_id	
join 	grupo_classificacao_agricola gca on gca.id = ica.grupo_classificacao_agricola_id
left join tipo_calculo_agricola tca on tca.id = ica.tipo_calculo_agricola_id_romaneio 
where	:dataBase  between vica.data_inicio_vigencia and vica.data_final_vigencia 	
and     :dataBase  between ica.data_inicio_vigencia and ica.data_final_vigencia		
and     vica.tipo_uso_romaneio in ('DD', 'DC') 											
and     vica.item_id = :itemId	
order by vica.ordem_tela, ica.nome