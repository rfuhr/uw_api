select distinct e.id as empresaId,
                e.nome as empresaNome,
                ef.id as empresaFilialId,
                ef.nome as empresaFilialNome
from usuario_permissao up, empresa_filial ef, empresa e, usuario u
where e.id = up.empresa_id 
and   e.id = ef.empresa_id 
and up.usuario_id  = :usuarioId
and up.usuario_id = u.id
and (up.filiais_id is null or up.filiais_id = '')
and u.admin = false
union
select distinct e.id as empresaId,
                e.nome as empresaNome,
                ef.id as empresaFilialId,
                ef.nome as empresaFilialNome
from usuario_permissao up, empresa_filial ef, empresa e, usuario u
where cast(ef.id as varchar(250)) = ANY(string_to_array(regexp_replace(up.filiais_id, '\[|\]', '', 'g'), ','))
and   e.id = ef.empresa_id 
and up.usuario_id  = :usuarioId
and up.usuario_id = u.id
and (up.filiais_id is not null AND up.filiais_id <> '')
and u.admin = false
union
select distinct e.id as empresaId,
                e.nome as empresaNome,
                ef.id as empresaFilialId,
                ef.nome as empresaFilialNome
from usuario_funcionalidade uf, empresa_filial ef, empresa e, usuario u
where e.id = uf.empresa_id 
and   e.id = ef.empresa_id 
and uf.usuario_id  = :usuarioId
and uf.usuario_id = u.id
and (uf.filiais_id is null or uf.filiais_id = '')
and u.admin = false
and uf.liberado = true
union
select distinct e.id as empresaId,
                e.nome as empresaNome,
                ef.id as empresaFilialId,
                ef.nome as empresaFilialNome
from usuario_funcionalidade uf, empresa_filial ef, empresa e, usuario u
where cast(ef.id as varchar(250)) = ANY(string_to_array(regexp_replace(uf.filiais_id, '\[|\]', '', 'g'), ','))
and   e.id = ef.empresa_id 
and uf.usuario_id  = :usuarioId
and uf.usuario_id = u.id
and (uf.filiais_id is not null AND uf.filiais_id <> '')
and u.admin = false
and uf.liberado = true
UNION
select distinct e.id as empresaId,
                e.nome as empresaNome,
                ef.id as empresaFilialId,
                ef.nome as empresaFilialNome
from empresa_filial ef, empresa e, usuario u
where  e.id = ef.empresa_id 
and u.id  = :usuarioId
and u.admin = true
order by empresaNome, empresaFilialNome