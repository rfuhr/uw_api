package br.com.ultraworks.erp.api.comercial.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoRepository;
import br.com.ultraworks.erp.api.comercial.repository.TipoPrecoRepository;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigCalculoPrecoMapper extends GenericMapper<ConfigCalculoPreco, ConfigCalculoPrecoDTO> {

	TipoPrecoRepository tipoPrecoRepository;
	ConfigCalculoPrecoOperInternaMapper configMarkupTipoPrecoOperInternaMapper;
	OperacaoInternaRepository operacaoInternaRepository;
	EmpresaFilialRepository empresaFilialRepository;
	GrupoContabilRepository grupoContabilRepository;
	
	public ConfigCalculoPrecoMapper(ConfigCalculoPrecoRepository repository,
			TipoPrecoRepository tipoPrecoRepository,
			ConfigCalculoPrecoOperInternaMapper configMarkupTipoPrecoOperInternaMapper,
			OperacaoInternaRepository operacaoInternaRepository,
			EmpresaFilialRepository empresaFilialRepository,
			GrupoContabilRepository grupoContabilRepository) {
		super(repository, ConfigCalculoPreco::new, ConfigCalculoPrecoDTO::new);
		this.tipoPrecoRepository = tipoPrecoRepository;
		this.configMarkupTipoPrecoOperInternaMapper = configMarkupTipoPrecoOperInternaMapper;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.empresaFilialRepository = empresaFilialRepository;
		this.grupoContabilRepository = grupoContabilRepository;
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
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado a Filial da Empresa com id " + dto.getEmpresaFilialId())));
		entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado a Operação Interna com id " + dto.getOperacaoInternaId())));
		entity.setGrupoContabil(grupoContabilRepository.findById(dto.getGrupoContabilId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Grupo Contábil com id " + dto.getGrupoContabilId())));
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
		if (entity.getEmpresaFilial() != null) {
			dto.setEmpresaFilialSigla(entity.getEmpresaFilial().getSigla());
			dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
			dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		}
		if (entity.getTipoPreco() != null) {
			dto.setTipoPrecoCodigo(entity.getTipoPreco().getCodigo());
			dto.setTipoPrecoNome(entity.getTipoPreco().getNome());
			dto.setTipoPrecoId(entity.getTipoPreco().getId());
		}
		if (entity.getGrupoContabil() != null) {
			dto.setGrupoContabilCodigo(entity.getGrupoContabil().getCodigo());
			dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
			dto.setGrupoContabilId(entity.getGrupoContabil().getId());
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
