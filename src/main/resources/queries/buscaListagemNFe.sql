with notas as (
	select	n.id as nfeId,
			ide.dhemi as dataHoraEmissao,
			n.chave_nfe as chaveNFe,
			ide.serie,
			ide.nnf as numero,
			s.name as situacao,
			coalesce(dest.cpf, dest.cnpj) as cpfCnpj,
			dest.xnome,
			totais.vnf,
			op.sigla as siglaOperacaoInterna,
			doc.id as documentoId,
			n.tipo_nfe as tipoNfe
	from	nfe n
			left join ide_nfe ide on ide.nfe_id = n.id
			left join situacao_documento s on s.value = n.situacao
			left join dest_nfe dest on dest.nfe_id = n.id
			left join totais_nfe totais on totais.nfe_id = n.id
			left join documento doc on doc.nfe_id = n.id
			left join operacao_interna op on op.id = doc.operacao_interna_id
	where	n.empresa_filial_id = :empresaFilialId
),
cache_nfe as (
SELECT 
   n.nfeid,
   c.siglaOperacaoInterna,
   c.parceiroLocalId
FROM notas n
     join view_cache_nfe c on n.nfeid = c.nfe_id 
),
integra_estoque as (
	select n.nfeid, di.integrado
	from notas n 
	     join documento_integracao di on n.documentoId = di.documento_id
	where di.tipo_integracao = '1' and ((n.situacao = 'Cancelado' and di.cancelamento = true) or (n.situacao <> 'Cancelado' and di.cancelamento = false))
),
integra_financeiro as (
	select n.nfeid, di.integrado
	from notas n 
	     join documento_integracao di on n.documentoId = di.documento_id
	where di.tipo_integracao = '2' and ((n.situacao = 'Cancelado' and di.cancelamento = true) or (n.situacao <> 'Cancelado' and di.cancelamento = false))
)
select n.nfeid, n.datahoraemissao, n.serie, n.numero, pl.cpf_cnpj as cpfcnpj, p.nome_razao_social as nome, c.siglaoperacaointerna, n.vnf, 
       n.situacao, n.chavenfe, 
	   case when ie.integrado is null and ifin.integrado is null then 'Sem Integração' 
	     else 
		  (case when ie.integrado is not null and ifin.integrado is not null 
		    and ie.integrado = false and ifin.integrado = false then 'Estoque/Financeiro não Integrados' 
			else (case when ie.integrado is not null and ifin.integrado is not null
			       and ie.integrado = true and ifin.integrado = false then 'Financeiro não Integrado'
			else (case when ie.integrado is not null and ifin.integrado is not null
			       and ie.integrado = false and ifin.integrado = true then 'Estoque não Integrado'
			else (case when ie.integrado is not null and ie.integrado = false then 'Estoque não Integrado'
			else (case when ifin.integrado is not null and ifin.integrado = false then 'Financeiro não Integrado'
			else 'Integrados' end) end)
		   end)
		  end)
		 end)
	   end as situacaointegracao,
	   case n.tipoNfe when '1' then 'Própria' else 'Terceiro' end as tipoNfe
from notas n
     left join integra_estoque ie on n.nfeid = ie.nfeid 
	 left join integra_financeiro ifin on n.nfeid = ifin.nfeid
	 left join cache_nfe c on n.nfeid = c.nfeid
	 left join parceiro_local pl on c.parceiroLocalId = pl.id
	 left join parceiro p on pl.parceiro_id = p.id
order by n.datahoraemissao desc