package br.com.ultraworks.erp.api.comercial.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoRepository;
import br.com.ultraworks.erp.api.comercial.repository.TipoPrecoRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigCalculoPrecoMapper extends GenericMapper<ConfigCalculoPreco, ConfigCalculoPrecoDTO> {

	TipoPrecoRepository tipoPrecoRepository;
	ConfigCalculoPrecoOperInternaMapper configMarkupTipoPrecoOperInternaMapper;
	OperacaoInternaRepository operacaoInternaRepository;
	
	public ConfigCalculoPrecoMapper(ConfigCalculoPrecoRepository repository,
			TipoPrecoRepository tipoPrecoRepository,
			ConfigCalculoPrecoOperInternaMapper configMarkupTipoPrecoOperInternaMapper,
			OperacaoInternaRepository operacaoInternaRepository) {
		super(repository, ConfigCalculoPreco::new, ConfigCalculoPrecoDTO::new);
		this.tipoPrecoRepository = tipoPrecoRepository;
		this.configMarkupTipoPrecoOperInternaMapper = configMarkupTipoPrecoOperInternaMapper;
		this.operacaoInternaRepository = operacaoInternaRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigCalculoPrecoDTO dto, ConfigCalculoPreco entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setTipoPreco(tipoPrecoRepository.findById(dto.getTipoPrecoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o TipoPreco com id " + dto.getTipoPrecoId())));	
		if (dto.getConfigCalculoPrecoOperInternas() != null && dto.getConfigCalculoPrecoOperInternas().size() > 0) {
			entity.setConfigCalculoPrecoOperInternas(new ArrayList<>());
			entity.getConfigCalculoPrecoOperInternas().addAll(configMarkupTipoPrecoOperInternaMapper.toEntity(dto.getConfigCalculoPrecoOperInternas()));
		}
		entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado a Operação Interna com id " + dto.getOperacaoInternaId())));	
		entity.setAplicaIndicesMarkup(dto.isAplicaIndicesMarkup());
		entity.setAplicaPercentualFixo(dto.isAplicaPercentualFixo());
		entity.setDiasBuscaPrecos(dto.getDiasBuscaPrecos());
		entity.setPercentual(dto.getPercentual());
	}

	@Override
	protected void setValuesToDto(ConfigCalculoPreco entity, ConfigCalculoPrecoDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getTipoPreco() != null) {
			dto.setTipoPrecoCodigo(entity.getTipoPreco().getCodigo());
			dto.setTipoPrecoNome(entity.getTipoPreco().getNome());
			dto.setTipoPrecoId(entity.getTipoPreco().getId());
		}
		dto.setConfigCalculoPrecoOperInternas(new ArrayList<>());
		if (entity.getConfigCalculoPrecoOperInternas() != null) {
			dto.getConfigCalculoPrecoOperInternas().addAll(configMarkupTipoPrecoOperInternaMapper.toDto(entity.getConfigCalculoPrecoOperInternas()));
		}
		if (entity.getOperacaoInterna() != null) {
			dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
			dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
			dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		}
		dto.setAplicaIndicesMarkup(entity.isAplicaIndicesMarkup());
		dto.setAplicaPercentualFixo(entity.isAplicaPercentualFixo());
		dto.setDiasBuscaPrecos(entity.getDiasBuscaPrecos());
		dto.setPercentual(entity.getPercentual());
	}
}
