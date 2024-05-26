package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndiceDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupPlanoItemIndiceMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupPlanoItemIndiceRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigMarkupPlanoItemIndiceQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMarkupPlanoItemIndiceService extends GenericService<ConfigMarkupPlanoItemIndice, Long, ConfigMarkupPlanoItemIndiceDTO> {

	ConfigMarkupPlanoItemIndiceRepository repository;
	VerificaDuplicidadeConfigMarkupPlanoItemIndiceQuery verificaDuplicidadeConfigMarkupPlanoItemIndiceQuery;
	
	@Autowired
	public ConfigMarkupPlanoItemIndiceService(ConfigMarkupPlanoItemIndiceRepository repository, 
			ConfigMarkupPlanoItemIndiceMapper mapper,
			VerificaDuplicidadeConfigMarkupPlanoItemIndiceQuery verificaDuplicidadeConfigMarkupPlanoItemIndiceQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMarkupPlanoItemIndiceQuery = verificaDuplicidadeConfigMarkupPlanoItemIndiceQuery;
	}

	public List<ConfigMarkupPlanoItemIndice> getAllByConfigMarkupPlanoItem(Long id) {
		List<ConfigMarkupPlanoItemIndice> listRegistros = new ArrayList<>();

		repository.findByConfigMarkupPlanoItemId(id).forEach(config -> {
			listRegistros.add(config);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMarkupPlanoItemIndice save(ConfigMarkupPlanoItemIndice entity) {
		
		validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMarkupPlanoItemIndice(entity);
		
		return super.save(entity);
	}

	private void validarDuplicidadeConfigMarkupPlanoItemIndice(ConfigMarkupPlanoItemIndice entity) {
		this.verificaDuplicidadeConfigMarkupPlanoItemIndiceQuery.executeSQL(entity);
	}

	private void validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(ConfigMarkupPlanoItemIndice entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMarkupPlanoItem().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMarkupPlanoItem().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ", está fora do Período da Configuração do Mark Up do Plano de Classificação do Item.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMarkupPlanoItem().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMarkupPlanoItem().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ", está fora do Período da Configuração do Mark Up do Plano de Classificação do Item.");
		}
	}
}