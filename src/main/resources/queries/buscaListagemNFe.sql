select	n.id as nfeId,
		ide.dhemi as dataHoraEmissao,
		n.chave_nfe as chaveNFe,
		ide.serie,
		ide.nnf as numero,
		s.name as situacao
from	nfe n
		join ide_nfe ide on ide.nfeid  = n.id
		join situacao_documento s on s.value = n.situacao
where	n.empresa_filial_id = :empresaFilialId