package br.com.ultraworks.erp.api.organograma.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.organograma.domain.organizacao.Organizacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OrganizacaoRepository extends UWRepository<Organizacao, Long>{

}
