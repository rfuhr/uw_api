package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPrecoDTO;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilial;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.api.comercial.mapper.TabelaPrecoMapper;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TabelaPrecoService extends GenericService<TabelaPreco, Long, TabelaPrecoDTO> {

	TabelaPrecoItemService tabelaPrecoItemService;
	TabelaPrecoEmpresaFilialService tabelaPrecoEmpresaFilialService;
	
	@Autowired
	public TabelaPrecoService(TabelaPrecoRepository repository, TabelaPrecoMapper mapper,
			TabelaPrecoItemService tabelaPrecoItemService,
			TabelaPrecoEmpresaFilialService tabelaPrecoEmpresaFilialService) {
		super(repository, mapper);
		this.tabelaPrecoItemService = tabelaPrecoItemService;
		this.tabelaPrecoEmpresaFilialService = tabelaPrecoEmpresaFilialService;
	}
	
	@Override
	public Optional<TabelaPreco> getById(Long id) {
		Optional<TabelaPreco> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setTabelaPrecoItens(tabelaPrecoItemService.getAllByTabelaPreco(id));
			registro.get().setTabelaPrecoEmpresaFiliais(tabelaPrecoEmpresaFilialService.getAllByTabelaPreco(id));
		}
		return registro;
	}
	
	@Override
	public TabelaPreco save(TabelaPreco entity) {

		List<TabelaPrecoItem> dadosSalvosItens = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosItens = tabelaPrecoItemService.getAllByTabelaPreco(entity.getId());
		}
		
		List<TabelaPrecoEmpresaFilial> dadosSalvosFiliais = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosFiliais = tabelaPrecoEmpresaFilialService.getAllByTabelaPreco(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getTabelaPrecoItens() != null && entity.getTabelaPrecoItens().size() > 0) {
			entity.getTabelaPrecoItens().forEach(item -> {
				item.setTabelaPreco(entity);
				item = tabelaPrecoItemService.save(item);
			});
		}
		
		if (entity.getTabelaPrecoEmpresaFiliais() != null && entity.getTabelaPrecoEmpresaFiliais().size() > 0) {
			entity.getTabelaPrecoEmpresaFiliais().forEach(filial -> {
				filial.setTabelaPreco(entity);
				filial = tabelaPrecoEmpresaFilialService.save(filial);
			});
		}
		
		List<TabelaPrecoItem> dadosItensExcluir = (List<TabelaPrecoItem>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosItens, entity.getTabelaPrecoItens());
		if (dadosItensExcluir.size() > 0) {
			dadosItensExcluir.forEach(item -> {
				tabelaPrecoItemService.delete(item.getId());
			});
		}
		
		List<TabelaPrecoEmpresaFilial> dadosFiliaisExcluir = (List<TabelaPrecoEmpresaFilial>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosFiliais, entity.getTabelaPrecoEmpresaFiliais());
		if (dadosFiliaisExcluir.size() > 0) {
			dadosFiliaisExcluir.forEach(filial -> {
				tabelaPrecoEmpresaFilialService.delete(filial.getId());
			});
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<TabelaPreco> config = this.getById(id);
		if (config.isPresent()) {
			config.get().getTabelaPrecoItens().forEach(indices -> {
				tabelaPrecoItemService.delete(indices.getId());
			});
			repository.deleteById(id);
		}
	}

}