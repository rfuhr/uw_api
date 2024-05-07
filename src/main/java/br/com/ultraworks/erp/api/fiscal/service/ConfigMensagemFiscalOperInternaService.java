package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInternaDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalOperInternaMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalOperInternaRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalOperInternaQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalOperInternaService extends GenericService<ConfigMensagemFiscalOperInterna, Long, ConfigMensagemFiscalOperInternaDTO> {

	ConfigMensagemFiscalOperInternaRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalOperInternaQuery verificaDuplicidadeConfigMensagemFiscalOperInternaQuery;
	
	@Autowired
	public ConfigMensagemFiscalOperInternaService(ConfigMensagemFiscalOperInternaRepository repository, ConfigMensagemFiscalOperInternaMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalOperInternaQuery verificaDuplicidadeConfigMensagemFiscalOperInternaQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalOperInternaQuery = verificaDuplicidadeConfigMensagemFiscalOperInternaQuery;
	}

	public List<ConfigMensagemFiscalOperInterna> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalOperInterna> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(operInterna -> {
			listRegistros.add(operInterna);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalOperInterna save(ConfigMensagemFiscalOperInterna entity) {
		
		validarSeOperacaoInternaEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalOperInterna(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalOperInterna(ConfigMensagemFiscalOperInterna entity) {
		verificaDuplicidadeConfigMensagemFiscalOperInternaQuery.executeSQL(entity);
	}

	private void validarSeOperacaoInternaEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalOperInterna entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getOperacaoInterna().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getOperacaoInterna().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getOperacaoInterna().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getOperacaoInterna().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}