package br.com.ultraworks.erp.api.comercial.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInternaDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoOperInternaRepository;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoRepository;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigCalculoPrecoOperInternaMapper extends GenericMapper<ConfigCalculoPrecoOperInterna, ConfigCalculoPrecoOperInternaDTO> {

	ConfigCalculoPrecoRepository configCalculoPrecoRepository;
	OperacaoInternaRepository operacaoInternaRepository;
	
	public ConfigCalculoPrecoOperInternaMapper(ConfigCalculoPrecoOperInternaRepository repository,
			ConfigCalculoPrecoRepository configCalculoPrecoRepository,
			OperacaoInternaRepository operacaoInternaRepository) {
		super(repository, ConfigCalculoPrecoOperInterna::new, ConfigCalculoPrecoOperInternaDTO::new);
		this.configCalculoPrecoRepository = configCalculoPrecoRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigCalculoPrecoOperInternaDTO dto, ConfigCalculoPrecoOperInterna entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigCalculoPrecoId() != null) {
			entity.setConfigCalculoPreco(configCalculoPrecoRepository.findById(dto.getConfigCalculoPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração de Cálculo de Preço com id " + dto.getConfigCalculoPrecoId())));			
		}
		entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado a Operação Interna com id " + dto.getOperacaoInternaId())));	
		if (StringUtils.isNotBlank(dto.getOperacaoEstoque()))
			entity.setOperacaoEstoque(OperacaoEstoque.fromValue(dto.getOperacaoEstoque()));
	}

	@Override
	protected void setValuesToDto(ConfigCalculoPrecoOperInterna entity, ConfigCalculoPrecoOperInternaDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setConfigCalculoPrecoId(entity.getConfigCalculoPreco().getId());
		if (entity.getOperacaoInterna() != null) {
			dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
			dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
			dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		}
		if (entity.getOperacaoEstoque() != null) {
			dto.setOperacaoEstoque(entity.getOperacaoEstoque().getValue());
			dto.setOperacaoEstoqueName(entity.getOperacaoEstoque().getName());
		}
	}
}
