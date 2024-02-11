select * from
(
select distinct f.id  as funcionalidadeId, uf.consultar , uf.inserir , uf.alterar, uf.excluir, uf.liberado, f.crud, 0 as order
from usuario_funcionalidade uf, funcionalidade f, empresa_filial ef 
where 	cast(ef.id as varchar(250)) = ANY(string_to_array(regexp_replace(uf.filiais_id, '\[|\]', '', 'g'), ','))
and 	f.id = uf.funcionalidade_id 
and 	(uf.filiais_id is not null and uf.filiais_id <> '')
and 	uf.usuario_id  = :usuarioId
and 	uf.empresa_id  = :empresaId
and 	ef.id = :empresaFilialId
and 	f.tag = :tag
union
select distinct f.id  as funcionalidadeId, uf.consultar , uf.inserir , uf.alterar, uf.excluir, uf.liberado, f.crud,1 as order
from usuario_funcionalidade uf, funcionalidade f
where 	f.id = uf.funcionalidade_id 
and 	(uf.filiais_id is null or uf.filiais_id = '')
and 	uf.usuario_id  = :usuarioId
and 	uf.empresa_id  = :empresaId
and 	f.tag = :tag
union
select distinct pf.funcionalidade_id  as funcionalidadeId, pf.consultar , pf.inserir , pf.alterar, pf.excluir, true as liberado, f2.crud,2 as order
from usuario_permissao up, perfil p2, perfil_funcionalidade pf, funcionalidade f2, empresa_filial ef 
where cast(p2.id as varchar(250)) = ANY(string_to_array(regexp_replace(up.perfis_id, '\[|\]', '', 'g'), ','))
and   cast(ef.id as varchar(250)) = ANY(string_to_array(regexp_replace(up.filiais_id, '\[|\]', '', 'g'), ','))
and   pf.perfil_id  = p2.id 
and f2.id = pf.funcionalidade_id 
and (up.filiais_id is not null and up.filiais_id <> '')
and up.usuario_id  = :usuarioId
and up.empresa_id  = :empresaId 
and ef.id = :empresaFilialId
and f2.tag = :tag
union 
select distinct pf.funcionalidade_id  as funcionalidadeId, pf.consultar , pf.inserir , pf.alterar, pf.excluir, true as liberado, f2.crud, 3 as order
from usuario_permissao up, perfil p2, perfil_funcionalidade pf, funcionalidade f2
where cast(p2.id as varchar(250)) = ANY(string_to_array(regexp_replace(up.perfis_id, '\[|\]', '', 'g'), ','))
and   pf.perfil_id  = p2.id 
and f2.id = pf.funcionalidade_id
and (up.filiais_id is null or up.filiais_id = '')
and up.usuario_id  = :usuarioId
and up.empresa_id  = :empresaId 
and f2.tag = :tag
) as permissoes
order by permissoes.order


