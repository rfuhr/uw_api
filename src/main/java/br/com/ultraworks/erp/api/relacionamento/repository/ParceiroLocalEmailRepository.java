package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalEmailRepository extends UWRepository<ParceiroLocalEmail, Long> {

	List<ParceiroLocalEmail> findByParceiroLocalId(Long parceiroLocalId);
}
