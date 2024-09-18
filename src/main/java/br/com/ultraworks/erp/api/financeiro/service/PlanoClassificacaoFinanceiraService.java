package br.com.ultraworks.erp.api.financeiro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.DataEstruturaPlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.PlanoClassificacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.PlanoClassificacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.dto.EstruturaContaResponse;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.interfaces.IDataEstruturaContaResponse;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PlanoClassificacaoFinanceiraService
		extends GenericService<PlanoClassificacaoFinanceira, Long, PlanoClassificacaoFinanceiraDTO> {

	@Autowired
	public PlanoClassificacaoFinanceiraService(PlanoClassificacaoFinanceiraRepository repository,
			PlanoClassificacaoFinanceiraMapper mapper) {
		super(repository, mapper);
	}

	public PlanoClassificacaoFinanceira save(PlanoClassificacaoFinanceira entity) {
		entity = repository.save(entity);
		if (entity.getContaSuperior() != null) {
			((PlanoClassificacaoFinanceiraRepository) repository)
					.atualizarSinteticaPorId(entity.getContaSuperior().getId(), true);
		}
		return entity;
	}

	public List<EstruturaContaResponse> getEstruturaConta() {
		List<EstruturaContaResponse> listaEstruturaConta = new ArrayList<>();

		List<PlanoClassificacaoFinanceira> registros = repository.findAll();

		List<PlanoClassificacaoFinanceira> niveis1 = registros.stream().filter(f -> f.getNivel() == 1)
				.collect(Collectors.toList());

		for (PlanoClassificacaoFinanceira conta : niveis1) {
			EstruturaContaResponse estruturaContaResponse = EstruturaContaResponse.builder().key(conta.getId())
					.data((IDataEstruturaContaResponse) DataEstruturaPlanoClassificacaoFinanceira.builder()
							.id(conta.getId()).codigo(conta.getCodigo()).nome(conta.getNome())
							.sintetica(conta.isSintetica()).build())
					.children(getEstruturaContaChildren(conta, registros)).build();

			listaEstruturaConta.add(estruturaContaResponse);
		}

		return listaEstruturaConta;
	}

	public List<EstruturaContaResponse> getEstruturaContaChildren(PlanoClassificacaoFinanceira contaSuperior,
			List<PlanoClassificacaoFinanceira> registros) {
		List<PlanoClassificacaoFinanceira> todosRegistrosNivelMaior = registros.stream()
				.filter(f -> f.getNivel() > contaSuperior.getNivel()).collect(Collectors.toList());

		List<PlanoClassificacaoFinanceira> registrosFilho = todosRegistrosNivelMaior.stream()
				.filter(f -> f.getContaSuperior().getId() == contaSuperior.getId()).collect(Collectors.toList());

		List<EstruturaContaResponse> listaEstruturaConta = new ArrayList<>();

		for (PlanoClassificacaoFinanceira conta : registrosFilho) {
			EstruturaContaResponse estruturaContaResponse = EstruturaContaResponse.builder().key(conta.getId())
					.data((IDataEstruturaContaResponse) DataEstruturaPlanoClassificacaoFinanceira.builder()
							.id(conta.getId()).codigo(conta.getCodigo()).nome(conta.getNome())
							.sintetica(conta.isSintetica()).build())
					.children(getEstruturaContaChildren(conta, todosRegistrosNivelMaior)).build();
			listaEstruturaConta.add(estruturaContaResponse);
		}

		if (listaEstruturaConta.size() > 0)
			return listaEstruturaConta;

		return null;
	}

}