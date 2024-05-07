package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTribDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalGrupoTribMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalGrupoTribRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalGrupoTribQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalGrupoTribService extends GenericService<ConfigMensagemFiscalGrupoTrib, Long, ConfigMensagemFiscalGrupoTribDTO> {

	ConfigMensagemFiscalGrupoTribRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalGrupoTribQuery verificaDuplicidadeConfigMensagemFiscalGrupoTribQuery;
	
	@Autowired
	public ConfigMensagemFiscalGrupoTribService(ConfigMensagemFiscalGrupoTribRepository repository, ConfigMensagemFiscalGrupoTribMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalGrupoTribQuery verificaDuplicidadeConfigMensagemFiscalGrupoTribQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalGrupoTribQuery = verificaDuplicidadeConfigMensagemFiscalGrupoTribQuery;
	}

	public List<ConfigMensagemFiscalGrupoTrib> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalGrupoTrib> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalGrupoTrib save(ConfigMensagemFiscalGrupoTrib entity) {
		
		validarSeGrupoTributacaoEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalGrupoTrib(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalGrupoTrib(ConfigMensagemFiscalGrupoTrib entity) {
		verificaDuplicidadeConfigMensagemFiscalGrupoTribQuery.executeSQL(entity);
	}

	private void validarSeGrupoTributacaoEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalGrupoTrib entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getGrupoTributacao().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getGrupoTributacao().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getGrupoTributacao().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getGrupoTributacao().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}