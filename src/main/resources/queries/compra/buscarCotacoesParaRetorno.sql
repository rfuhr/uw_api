select 	cm.id as cotacaoMercadoriaId, 
		cm.numero as cotacaoMercadoriaNumero, 
		cm.data_cotacao as cotacaoMercadoriaData,
		cm.situacao_cotacao_mercadoria as cotacaoMercadoriaSituacao,
		d.sigla as departamentoSigla,
		p.nome_razao_social as parceiroNomeRazaoSocial,
		pl.nome_local as parceiroLocalNome,
		cmp.id as cotacaoMercadoriaParceiroId,
		scm."name"  as situacaoCotacaoMercadoriaName
from cotacao_mercadoria cm
	 join cotacao_mercadoria_parceiro cmp on cmp.cotacao_mercadoria_id = cm.id
	 join departamento d on d.id = cm.depto_cotacao_id 
	 join parceiro_local pl on pl.id = cmp.parceiro_local_id 
	 join parceiro p on p.id = pl.parceiro_id
	 join situacao_cotacao_mercadoria scm on scm.value = cm.situacao_cotacao_mercadoria 
where situacao_cotacao_mercadoria in ('3', '4')