package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiroDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigIncentivoFiscalParceiroMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalParceiroRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigIncentivoFiscalParceiroQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigIncentivoFiscalParceiroService extends GenericService<ConfigIncentivoFiscalParceiro, Long, ConfigIncentivoFiscalParceiroDTO> {

	ConfigIncentivoFiscalParceiroRepository repository;
	VerificaDuplicidadeConfigIncentivoFiscalParceiroQuery verificaDuplicidadeConfigIncentivoFiscalParceiroQuery;
	
	@Autowired
	public ConfigIncentivoFiscalParceiroService(ConfigIncentivoFiscalParceiroRepository repository, 
			ConfigIncentivoFiscalParceiroMapper mapper,
			VerificaDuplicidadeConfigIncentivoFiscalParceiroQuery verificaDuplicidadeConfigIncentivoFiscalParceiroQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigIncentivoFiscalParceiroQuery = verificaDuplicidadeConfigIncentivoFiscalParceiroQuery;
	}

	public List<ConfigIncentivoFiscalParceiro> getAllByConfigIncentivoFiscal(Long id) {
		List<ConfigIncentivoFiscalParceiro> listRegistros = new ArrayList<>();

		repository.findByConfigIncentivoFiscalId(id).forEach(configParceiro -> {
			listRegistros.add(configParceiro);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigIncentivoFiscalParceiro save(ConfigIncentivoFiscalParceiro entity) {
		
		validarSeParceiroEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigIncentivoFiscalParceiro(entity);
		
		return super.save(entity);
	}

	private void validarDuplicidadeConfigIncentivoFiscalParceiro(ConfigIncentivoFiscalParceiro entity) {
		this.verificaDuplicidadeConfigIncentivoFiscalParceiroQuery.executeSQL(entity);
	}

	private void validarSeParceiroEstaDentroDaVigenciaDaConfiguracao(ConfigIncentivoFiscalParceiro entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para o Parceiro " + entity.getParceiroLocal().getParceiro().getNomeRazaoSocial() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para o Parceiro " + entity.getParceiroLocal().getParceiro().getNomeRazaoSocial() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigIncentivoFiscal().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigIncentivoFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para o Parceiro " + entity.getParceiroLocal().getParceiro().getNomeRazaoSocial() + ", está fora do Período da Configuração do Incentivo Fiscal.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigIncentivoFiscal().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigIncentivoFiscal().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para o Parceiro " + entity.getParceiroLocal().getParceiro().getNomeRazaoSocial() + ", está fora do Período da Configuração do Incentivo Fiscal.");
		}
	}
}