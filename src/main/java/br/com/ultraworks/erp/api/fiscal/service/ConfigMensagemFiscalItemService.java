package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItemDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalItemMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalItemRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalItemQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalItemService extends GenericService<ConfigMensagemFiscalItem, Long, ConfigMensagemFiscalItemDTO> {

	ConfigMensagemFiscalItemRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalItemQuery verificaDuplicidadeConfigMensagemFiscalItemQuery;
	
	@Autowired
	public ConfigMensagemFiscalItemService(ConfigMensagemFiscalItemRepository repository, ConfigMensagemFiscalItemMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalItemQuery verificaDuplicidadeConfigMensagemFiscalItemQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalItemQuery = verificaDuplicidadeConfigMensagemFiscalItemQuery;
	}

	public List<ConfigMensagemFiscalItem> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalItem> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalItem save(ConfigMensagemFiscalItem entity) {
		
		validarSeItemEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalItem(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalItem(ConfigMensagemFiscalItem entity) {
		verificaDuplicidadeConfigMensagemFiscalItemQuery.executeSQL(entity);
	}

	private void validarSeItemEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalItem entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getItem().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getItem().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getItem().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getItem().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}