select * from
(
select distinct f.id  as autonomiaId, 0 as order
from usuario_autonomia uf, autonomia f, empresa_filial ef 
where 	cast(ef.id as varchar(250)) = ANY(string_to_array(regexp_replace(uf.filiais_id, '\[|\]', '', 'g'), ','))
and 	f.id = uf.autonomia_id 
and 	(uf.filiais_id is not null and uf.filiais_id <> '')
and 	uf.usuario_id  = :usuarioId
and 	uf.empresa_id  = :empresaId
and 	ef.id = :empresaFilialId
and 	f.tag = :tag
union
select distinct f.id  as autonomiaId, 1 as order
from usuario_autonomia uf, autonomia f
where 	f.id = uf.autonomia_id 
and 	(uf.filiais_id is null or uf.filiais_id = '')
and 	uf.usuario_id  = :usuarioId
and 	uf.empresa_id  = :empresaId
and 	f.tag = :tag
) as permissoes
order by permissoes.order


