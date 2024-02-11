package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalRepository extends UWRepository<ParceiroLocal, Long> {
	
	List<ParceiroLocal> findByParceiroId(Long parceiroId);
}
