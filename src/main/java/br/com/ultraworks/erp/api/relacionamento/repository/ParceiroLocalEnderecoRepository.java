package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalEnderecoRepository extends UWRepository<ParceiroLocalEndereco, Long> {
	
	List<ParceiroLocalEndereco> findByParceiroLocalId(Long parceiroLocalId);
}
