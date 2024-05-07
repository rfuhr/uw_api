package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalTipoIncentFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalTipoIncentFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalTipoIncentFiscalService extends GenericService<ConfigMensagemFiscalTipoIncentFiscal, Long, ConfigMensagemFiscalTipoIncentFiscalDTO> {

	ConfigMensagemFiscalTipoIncentFiscalRepository repository;
	VerificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery verificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery;
	
	@Autowired
	public ConfigMensagemFiscalTipoIncentFiscalService(ConfigMensagemFiscalTipoIncentFiscalRepository repository, ConfigMensagemFiscalTipoIncentFiscalMapper mapper,
			VerificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery verificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery = verificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery;
	}

	public List<ConfigMensagemFiscalTipoIncentFiscal> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalTipoIncentFiscal> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigMensagemFiscalTipoIncentFiscal save(ConfigMensagemFiscalTipoIncentFiscal entity) {
		
		validarSeTipoIncentFiscalEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigMensagemFiscalTipoIncentFiscal(entity);
		
		return super.save(entity);
	}
	
	private void validarDuplicidadeConfigMensagemFiscalTipoIncentFiscal(ConfigMensagemFiscalTipoIncentFiscal entity) {
		verificaDuplicidadeConfigMensagemFiscalTipoIncentFiscalQuery.executeSQL(entity);
	}

	private void validarSeTipoIncentFiscalEstaDentroDaVigenciaDaConfiguracao(ConfigMensagemFiscalTipoIncentFiscal entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Identificador " + entity.getTipoIncentivoFiscal().getId() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Identificador " + entity.getTipoIncentivoFiscal().getId() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Identificador " + entity.getTipoIncentivoFiscal().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigMensagemFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigMensagemFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Identificador " + entity.getTipoIncentivoFiscal().getId() + ", está fora do Período da Configuração da Mensagem Fiscal.");
		}
	}
	
}