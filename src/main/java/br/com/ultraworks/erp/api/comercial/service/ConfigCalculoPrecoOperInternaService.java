package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInternaDTO;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigCalculoPrecoOperInternaMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoOperInternaRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigCalculoPrecoOperInternaQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigCalculoPrecoOperInternaService extends GenericService<ConfigCalculoPrecoOperInterna, Long, ConfigCalculoPrecoOperInternaDTO> {

	ConfigCalculoPrecoOperInternaRepository repository;
	VerificaDuplicidadeConfigCalculoPrecoOperInternaQuery verificaDuplicidadeConfigCalculoPrecoOperInternaQuery;
	
	@Autowired
	public ConfigCalculoPrecoOperInternaService(ConfigCalculoPrecoOperInternaRepository repository, 
			ConfigCalculoPrecoOperInternaMapper mapper,
			VerificaDuplicidadeConfigCalculoPrecoOperInternaQuery verificaDuplicidadeConfigCalculoPrecoOperInternaQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.verificaDuplicidadeConfigCalculoPrecoOperInternaQuery = verificaDuplicidadeConfigCalculoPrecoOperInternaQuery;
	}

	public List<ConfigCalculoPrecoOperInterna> getAllByConfigCalculoPreco(Long id) {
		List<ConfigCalculoPrecoOperInterna> listRegistros = new ArrayList<>();

		repository.findByConfigCalculoPrecoId(id).forEach(config -> {
			listRegistros.add(config);
		});
		return listRegistros;
	}
	
	@Override
	public ConfigCalculoPrecoOperInterna save(ConfigCalculoPrecoOperInterna entity) {
		
		validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(entity);
		validarDuplicidadeConfigCalculoPrecoOperInterna(entity);
		
		return super.save(entity);
	}

	private void validarDuplicidadeConfigCalculoPrecoOperInterna(ConfigCalculoPrecoOperInterna entity) {
		this.verificaDuplicidadeConfigCalculoPrecoOperInternaQuery.executeSQL(entity);
	}

	private void validarSeIndiceEstaDentroDaVigenciaDaConfiguracao(ConfigCalculoPrecoOperInterna entity) {
		if (entity.getDataInicioVigencia() == null) {
			throw new BusinessException("Informe uma Data de Início de Vigência para a Operação Interna " + entity.getOperacaoInterna().getNome() + ".");
		}
		if (entity.getDataFinalVigencia() == null) {
			throw new BusinessException("Informe uma Data Final de Vigência para a Operação Interna " + entity.getOperacaoInterna().getNome() + ".");
		}
		if (entity.getDataInicioVigencia().isBefore(entity.getConfigCalculoPreco().getDataInicioVigencia()) ||
				entity.getDataInicioVigencia().isAfter(entity.getConfigCalculoPreco().getDataFinalVigencia())) {
			throw new BusinessException("Data de Início de Vigência para a Operação Interna " + entity.getOperacaoInterna().getNome() + ", está fora do Período da Configuração do Cálculo de Preço.");
		}
		if (entity.getDataFinalVigencia().isBefore(entity.getConfigCalculoPreco().getDataInicioVigencia()) ||
				entity.getDataFinalVigencia().isAfter(entity.getConfigCalculoPreco().getDataFinalVigencia())) {
			throw new BusinessException("Data Final de Vigência para a Operação Interna " + entity.getOperacaoInterna().getNome() + ", está fora do Período da Configuração do Cálculo de Preço.");
		}
	}
}