package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItemDTO;
import br.com.ultraworks.erp.api.comercial.mapper.TabelaPrecoItemMapper;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeTabelaPrecoItemQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TabelaPrecoItemService extends GenericService<TabelaPrecoItem, Long, TabelaPrecoItemDTO> {

	TabelaPrecoItemRepository repository;
	VerificaDuplicidadeTabelaPrecoItemQuery verificaDuplicidadeTabelaPrecoItemQuery;
	
	@Autowired
	public TabelaPrecoItemService(TabelaPrecoItemRepository repository, 
			TabelaPrecoItemMapper mapper,
			VerificaDuplicidadeTabelaPrecoItemQuery verificaDuplicidadeTabelaPrecoItemQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeTabelaPrecoItemQuery = verificaDuplicidadeTabelaPrecoItemQuery;
	}

	public List<TabelaPrecoItem> getAllByTabelaPreco(Long id) {
		List<TabelaPrecoItem> listRegistros = new ArrayList<>();

		repository.findByTabelaPrecoId(id).forEach(config -> {
			listRegistros.add(config);
		});
		return listRegistros;
	}
	
	@Override
	public TabelaPrecoItem save(TabelaPrecoItem entity) {
		
		validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeTabelaPrecoItem(entity);
		
		return super.save(entity);
	}

	private void validarDuplicidadeTabelaPrecoItem(TabelaPrecoItem entity) {
		this.verificaDuplicidadeTabelaPrecoItemQuery.executeSQL(entity);
	}

	private void validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(TabelaPrecoItem entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Item " + entity.getItem().getNome() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Item " + entity.getItem().getNome() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Item " + entity.getItem().getNome() + ", está fora da Vigência da Tabela de Preço.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Item " + entity.getItem().getNome() + ", está fora da Vigência da Tabela de Preço.");
		}
	}
}