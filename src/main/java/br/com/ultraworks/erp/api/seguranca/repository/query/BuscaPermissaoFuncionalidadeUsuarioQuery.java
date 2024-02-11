package br.com.ultraworks.erp.api.seguranca.repository.query;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.vo.PermissaoFuncionalidadeUsuarioVO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaPermissaoFuncionalidadeUsuarioQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PermissaoFuncionalidadeUsuarioVO> executeSQL(long empresaId, long empresaFilialId, long usuarioId,
			String tag) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscaPermissaoFuncionalidadeUsuario"), Tuple.class)
				.setParameter("empresaId", empresaId).setParameter("empresaFilialId", empresaFilialId)
				.setParameter("usuarioId", usuarioId).setParameter("tag", tag)
				.getResultList();

		new PermissaoFuncionalidadeUsuarioVO();
		return resultTuples.stream()
				.map(t -> PermissaoFuncionalidadeUsuarioVO.builder()
						.funcionalidadeId(t.get("funcionalidadeId", Long.class).longValue())
						.consultar(t.get("consultar", Boolean.class).booleanValue())
						.inserir(t.get("inserir", Boolean.class).booleanValue())
						.alterar(t.get("alterar", Boolean.class).booleanValue())
						.excluir(t.get("excluir", Boolean.class).booleanValue())
						.liberado(t.get("liberado", Boolean.class).booleanValue())
						.crud(t.get("crud", Boolean.class).booleanValue())
						.order(t.get("order", Integer.class).intValue()).build())
				.collect(Collectors.toList());
	}
}
