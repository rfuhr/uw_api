package br.com.ultraworks.erp.api.estoque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.DataEstruturaPlanoClassificacaoItem;
import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItem;
import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.PlanoClassificacaoItemMapper;
import br.com.ultraworks.erp.api.estoque.repository.PlanoClassificacaoItemRepository;
import br.com.ultraworks.erp.core.dto.EstruturaContaResponse;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.interfaces.IDataEstruturaContaResponse;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PlanoClassificacaoItemService
		extends GenericService<PlanoClassificacaoItem, Long, PlanoClassificacaoItemDTO> {

	@Autowired
	public PlanoClassificacaoItemService(PlanoClassificacaoItemRepository repository,
			PlanoClassificacaoItemMapper mapper) {
		super(repository, mapper);
	}

	public PlanoClassificacaoItem save(PlanoClassificacaoItem entity) {
		entity = repository.save(entity);
		if (entity.getContaSuperior() != null) {
			((PlanoClassificacaoItemRepository) repository).atualizarSinteticaPorId(entity.getContaSuperior().getId(),
					true);
		}
		return entity;
	}

	public List<EstruturaContaResponse> getEstruturaConta() {
		List<EstruturaContaResponse> listaEstruturaConta = new ArrayList<>();
		
		List<PlanoClassificacaoItem> registros = repository.findAll();
		
		List<PlanoClassificacaoItem> niveis1 = registros.stream().filter(f -> f.getNivel() == 1).collect(Collectors.toList());
		
		for (PlanoClassificacaoItem conta : niveis1) {
			EstruturaContaResponse estruturaContaResponse = EstruturaContaResponse.builder()
				.key(conta.getId())
				.data((IDataEstruturaContaResponse) DataEstruturaPlanoClassificacaoItem.builder()
						.id(conta.getId())
						.codigo(conta.getCodigo()).nome(conta.getNome()).sintetica(conta.isSintetica())
						.build())
 				.children(getEstruturaContaChildren(conta, registros))
				.build();
			
			listaEstruturaConta.add(estruturaContaResponse);
		}
		
		return listaEstruturaConta;
	}
	
	public List<EstruturaContaResponse> getEstruturaContaChildren(PlanoClassificacaoItem contaSuperior, List<PlanoClassificacaoItem> registros) {
		List<PlanoClassificacaoItem> todosRegistrosNivelMaior = registros.stream().filter(f -> f.getNivel() > contaSuperior.getNivel()).collect(Collectors.toList());
		
		List<PlanoClassificacaoItem> registrosFilho = todosRegistrosNivelMaior.stream().filter(f -> f.getContaSuperior().getId() == contaSuperior.getId()).collect(Collectors.toList());
		
		List<EstruturaContaResponse> listaEstruturaConta = new ArrayList<>();

		for (PlanoClassificacaoItem conta : registrosFilho) {
			EstruturaContaResponse estruturaContaResponse = EstruturaContaResponse.builder()
				.key(conta.getId())
				.data((IDataEstruturaContaResponse) DataEstruturaPlanoClassificacaoItem.builder()
						.id(conta.getId())
						.codigo(conta.getCodigo()).nome(conta.getNome()).sintetica(conta.isSintetica())
						.build())
				.children(getEstruturaContaChildren(conta, todosRegistrosNivelMaior))
				.build();
			listaEstruturaConta.add(estruturaContaResponse);
		}
		
		if (listaEstruturaConta.size() > 0)
				return listaEstruturaConta;
		
		return null;
	}

}