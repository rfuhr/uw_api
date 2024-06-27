package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndiceDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupItemIndiceMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupItemIndiceRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigMarkupItemIndiceQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMarkupItemIndiceService extends GenericService<ConfigMarkupItemIndice, Long, ConfigMarkupItemIndiceDTO> {

	ConfigMarkupItemIndiceRepository repository;
	VerificaDuplicidadeConfigMarkupItemIndiceQuery verificaDuplicidadeConfigMarkupItemIndiceQuery;
	
	@Autowired
	public ConfigMarkupItemIndiceService(ConfigMarkupItemIndiceRepository repository, 
			ConfigMarkupItemIndiceMapper mapper,
			VerificaDuplicidadeConfigMarkupItemIndiceQuery verificaDuplicidadeConfigMarkupItemIndiceQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMarkupItemIndiceQuery = verificaDuplicidadeConfigMarkupItemIndiceQuery;
	}

	public List<ConfigMarkupItemIndice> getAllByConfigMarkupItem(Long id) {
		List<ConfigMarkupItemIndice> listRegistros = new ArrayList<>();

		repository.findByConfigMarkupItemId(id).forEach(config -> {
			listRegistros.add(config);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMarkupItemIndice save(ConfigMarkupItemIndice entity) {
		
		validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMarkupItemIndice(entity);
		
		return super.save(entity);
	}

	private void validarDuplicidadeConfigMarkupItemIndice(ConfigMarkupItemIndice entity) {
		this.verificaDuplicidadeConfigMarkupItemIndiceQuery.executeSQL(entity);
	}

	private void validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(ConfigMarkupItemIndice entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMarkupItem().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMarkupItem().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ", está fora do Período da Configuração do Mark Up do Item.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMarkupItem().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMarkupItem().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Índice " + entity.getIndiceMarkup().getNome() + ", está fora do Período da Configuração do Mark Up do Item.");
		}
	}
}