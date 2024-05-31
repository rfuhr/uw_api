package br.com.ultraworks.erp.api.comercial.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPrecoDTO;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoRepository;
import br.com.ultraworks.erp.api.comercial.repository.TipoPrecoRepository;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TabelaPrecoMapper extends GenericMapper<TabelaPreco, TabelaPrecoDTO> {

	TipoPrecoRepository tipoPrecoRepository;
	TabelaPrecoEmpresaFilialMapper tabelaPrecoEmpresaFilialMapper;
	TabelaPrecoItemMapper tabelaPrecoItemMapper;
	EmpresaFilialRepository empresaFilialRepository;
	GrupoContabilRepository grupoContabilRepository;
	
	public TabelaPrecoMapper(TabelaPrecoRepository repository,
			TipoPrecoRepository tipoPrecoRepository,
			TabelaPrecoEmpresaFilialMapper tabelaPrecoEmpresaFilialMapper,
			TabelaPrecoItemMapper tabelaPrecoItemMapper,
			EmpresaFilialRepository empresaFilialRepository,
			GrupoContabilRepository grupoContabilRepository) {
		super(repository, TabelaPreco::new, TabelaPrecoDTO::new);
		this.tipoPrecoRepository = tipoPrecoRepository;
		this.tabelaPrecoEmpresaFilialMapper = tabelaPrecoEmpresaFilialMapper;
		this.tabelaPrecoItemMapper = tabelaPrecoItemMapper;
		this.empresaFilialRepository = empresaFilialRepository;
		this.grupoContabilRepository = grupoContabilRepository;
	}

	@Override
	protected void setValuesToEntity(TabelaPrecoDTO dto, TabelaPreco entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setTipoPreco(tipoPrecoRepository.findById(dto.getTipoPrecoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o TipoPreco com id " + dto.getTipoPrecoId())));	
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado a Filial da Empresa com id " + dto.getEmpresaFilialId())));
		if (dto.getGrupoContabilId() != null) {
			entity.setGrupoContabil(grupoContabilRepository.findById(dto.getGrupoContabilId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Grupo Contábil com id " + dto.getGrupoContabilId())));			
		}
		entity.setPromocional(dto.isPromocional());
		if (dto.getTabelaPrecoEmpresaFiliais() != null && dto.getTabelaPrecoEmpresaFiliais().size() > 0) {
			entity.setTabelaPrecoEmpresaFiliais(new ArrayList<>());
			entity.getTabelaPrecoEmpresaFiliais().addAll(tabelaPrecoEmpresaFilialMapper.toEntity(dto.getTabelaPrecoEmpresaFiliais()));
		}
		if (dto.getTabelaPrecoItens() != null && dto.getTabelaPrecoItens().size() > 0) {
			entity.setTabelaPrecoItens(new ArrayList<>());
			entity.getTabelaPrecoItens().addAll(tabelaPrecoItemMapper.toEntity(dto.getTabelaPrecoItens()));
		}
	}

	@Override
	protected void setValuesToDto(TabelaPreco entity, TabelaPrecoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
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
		dto.setPromocional(entity.isPromocional());
		dto.setTabelaPrecoEmpresaFiliais(new ArrayList<>());
		if (entity.getTabelaPrecoEmpresaFiliais() != null) {
			dto.getTabelaPrecoEmpresaFiliais().addAll(tabelaPrecoEmpresaFilialMapper.toDto(entity.getTabelaPrecoEmpresaFiliais()));
		}
		dto.setTabelaPrecoItens(new ArrayList<>());
		if (entity.getTabelaPrecoItens() != null) {
			dto.getTabelaPrecoItens().addAll(tabelaPrecoItemMapper.toDto(entity.getTabelaPrecoItens()));
		}
	}
}
