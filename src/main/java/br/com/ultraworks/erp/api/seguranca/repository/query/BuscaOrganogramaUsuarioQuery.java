package br.com.ultraworks.erp.api.seguranca.repository.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.vo.OrganogramaUsuarioVO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaOrganogramaUsuarioQuery {
	
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<OrganogramaUsuarioVO> executeSQL(long usuarioId) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("buscaOrganogramaUsuario"), Tuple.class)
				.setParameter("usuarioId", usuarioId).getResultList();

		return resultTuples.stream()
				.map(t -> new OrganogramaUsuarioVO(t.get("empresaId", Long.class),
						t.get("empresaNome", String.class), t.get("empresaFilialId", Long.class),
						t.get("empresaFilialNome", String.class)))
				.collect(Collectors.toList());
	}	
}
