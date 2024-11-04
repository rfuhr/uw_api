select * from 
(select	1 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     config.empresa_filial_id = :empresaFilialId
and     config.departamento_id = :departamentoId
and     config.grupo_contabil_id = :grupoContabilId
union all 
select	2 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     config.empresa_filial_id = :empresaFilialId
and     config.departamento_id = :departamentoId
and     gc."general" = true
union all 
select	3 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     config.empresa_filial_id = :empresaFilialId
and     d."general" = true
and     config.grupo_contabil_id = :grupoContabilId
union all 
select	4 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     config.empresa_filial_id = :empresaFilialId
and     d."general" = true
and     gc."general" = true
union all 
select	5 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     ef."general" = true
and     config.departamento_id = :departamentoId
and     config.grupo_contabil_id = :grupoContabilId
union all 
select	6 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     ef."general" = true
and     d."general" = true
and     config.grupo_contabil_id = :grupoContabilId
union all 
select	7 as ordem, config.*
from	config_autorizacao_solicitacao_mercadoria config
		join grupo_contabil gc on gc.id = config.grupo_contabil_id
		join departamento d on d.id = config.departamento_id
		join empresa_filial ef on ef.id = config.empresa_filial_id
		join empresa e on e.id = config.empresa_id
where	config.empresa_id = :empresaId
and     ef."general" = true
and     d."general" = true
and     gc."general" = true
) as valores
order by ordem
limit 1