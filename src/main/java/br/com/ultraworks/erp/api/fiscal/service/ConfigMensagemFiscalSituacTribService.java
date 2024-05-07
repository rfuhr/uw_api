package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTribDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalSituacTribMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalSituacTribRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalSituacTribQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalSituacTribService extends GenericService<ConfigMensagemFiscalSituacTrib, Long, ConfigMensagemFiscalSituacTribDTO> {

	ConfigMensagemFiscalSituacTribRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalSituacTribQuery verificaDuplicidadeConfigMensagemFiscalSituacTribQuery;
	
	@Autowired
	public ConfigMensagemFiscalSituacTribService(ConfigMensagemFiscalSituacTribRepository repository, ConfigMensagemFiscalSituacTribMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalSituacTribQuery verificaDuplicidadeConfigMensagemFiscalSituacTribQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalSituacTribQuery = verificaDuplicidadeConfigMensagemFiscalSituacTribQuery;
	}

	public List<ConfigMensagemFiscalSituacTrib> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalSituacTrib> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalSituacTrib save(ConfigMensagemFiscalSituacTrib entity) {
		
		validarSeSituacaoTributariaEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalSituacTrib(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalSituacTrib(ConfigMensagemFiscalSituacTrib entity) {
		verificaDuplicidadeConfigMensagemFiscalSituacTribQuery.executeSQL(entity);
	}

	private void validarSeSituacaoTributariaEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalSituacTrib entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getSituacaoTributaria().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getSituacaoTributaria().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getSituacaoTributaria().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getSituacaoTributaria().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}