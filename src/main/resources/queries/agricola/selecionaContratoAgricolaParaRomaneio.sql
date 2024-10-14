select	p.id as parceiroId,
		p.nome_razao_social as parceiroNome,
		pl.id as parceiroLocalId,
		pl.nome_local as parceiroLocalNome,
		i.codigo as itemCodigo,
		d.id as departamentoId,
		d.sigla as departamentoSigla,
		d.nome as departamentoNome,
		c.numero as contratoNumero,
		(c.qtd_contratada - c.qtd_baixada) as contratoSaldo,
		c.data_documento as contratoDataDocumento,
		oi.id as operacaoInternaId,
		oi.sigla as operacaoInternaSigla,
		oi.nome as operacaoInternaNome,
		c.observacao as contratoObservacao,
		goa.id as grupoOperacaoAgricolaId,
		goa.nome as grupoOperacaoAgricolaNome,
		c.valor_unitario_liquido as contratoValorUnitarioLiquido,
		c.valor_unitario_bruto as contratoValorUnitarioBruto,
		uf.id as ufId,
		uf.sigla as ufSigla,
		c.id as contratoId
from	contrato_agricola c
		join parceiro_local pl on pl.id = c.parceiro_local_id
		join parceiro p on p.id = pl.parceiro_id
		join item i on i.id = c.item_id
		join departamento d on d.id = c.departamento_id
		join operacao_interna oi on oi.id = c.operacao_interna_id
		join grupo_operacao_agricola goa on goa.id = c.grupo_operacao_agricola_id
		join parceiro_local_endereco ple on ple.parceiro_local_id = pl.id and ple.principal = true
		join cidade cid on cid.id = ple.cidade_id 
		join uf uf on uf.id = cid.uf_id 
where   p.id = :parceiroId
and     c.item_id = :itemId
and     c.safra_id = :safraId
and     (c.qtd_contratada - c.qtd_baixada) > 0
order by c.data_documento, contratoSaldo
