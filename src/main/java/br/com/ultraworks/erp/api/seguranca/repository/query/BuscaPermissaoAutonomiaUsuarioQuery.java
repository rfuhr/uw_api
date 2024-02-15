package br.com.ultraworks.erp.api.seguranca.repository.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.vo.PermissaoAutonomiaUsuarioVO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaPermissaoAutonomiaUsuarioQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PermissaoAutonomiaUsuarioVO> executeSQL(long empresaId, long empresaFilialId, long usuarioId,
			String tag) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscaPermissaoAutonomiaUsuario"), Tuple.class)
				.setParameter("empresaId", empresaId).setParameter("empresaFilialId", empresaFilialId)
				.setParameter("usuarioId", usuarioId).setParameter("tag", tag).getResultList();

		new PermissaoAutonomiaUsuarioVO();
		return resultTuples.stream()
				.map(t -> PermissaoAutonomiaUsuarioVO.builder()
						.autonomiaId(t.get("autonomiaId", Long.class).longValue())
						.order(t.get("order", Integer.class).intValue()).build())
				.collect(Collectors.toList());
	}
}
