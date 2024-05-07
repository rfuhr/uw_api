package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalConfigFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalConfigFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalConfigFiscalService extends GenericService<ConfigMensagemFiscalConfigFiscal, Long, ConfigMensagemFiscalConfigFiscalDTO> {

	ConfigMensagemFiscalConfigFiscalRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery verificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery;
	
	@Autowired
	public ConfigMensagemFiscalConfigFiscalService(ConfigMensagemFiscalConfigFiscalRepository repository, 
			ConfigMensagemFiscalConfigFiscalMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery verificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery = verificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery;
	}

	public List<ConfigMensagemFiscalConfigFiscal> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalConfigFiscal> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(configFiscal -> {
			listRegistros.add(configFiscal);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalConfigFiscal save(ConfigMensagemFiscalConfigFiscal entity) {
		
		validarSeConfiguracaoFiscalEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalConfigFiscal(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalConfigFiscal(ConfigMensagemFiscalConfigFiscal entity) {
		verificaDuplicidadeConfigMensagemFiscalConfigFiscalQuery.executeSQL(entity);
	}

	private void validarSeConfiguracaoFiscalEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalConfigFiscal entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getConfiguracaoFiscal().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getConfiguracaoFiscal().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getConfiguracaoFiscal().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getConfiguracaoFiscal().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}