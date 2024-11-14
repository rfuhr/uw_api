package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CondicaoPagamentoRepository extends UWRepository<CondicaoPagamento, Long> {

	@Query(value = "SELECT proximo_codigo('condicao_pagamento', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
