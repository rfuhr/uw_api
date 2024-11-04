package br.com.ultraworks.erp.api.compras.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SolicitacaoMercadoriaMapper extends GenericMapper<SolicitacaoMercadoria, SolicitacaoMercadoriaDTO> {

	private DepartamentoRepository departamentoRepository;
	private GrupoContabilRepository grupoContabilRepository;
	private SolicitacaoMercadoriaItemMapper solicitacaoMercadoriaItemMapper;

	public SolicitacaoMercadoriaMapper(SolicitacaoMercadoriaRepository solicitacaoMercadoriaRepository,
			DepartamentoRepository departamentoRepository, GrupoContabilRepository grupoContabilRepository,
			SolicitacaoMercadoriaItemMapper solicitacaoMercadoriaItemMapper) {
		super(solicitacaoMercadoriaRepository, SolicitacaoMercadoria::new, SolicitacaoMercadoriaDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.grupoContabilRepository = grupoContabilRepository;
		this.solicitacaoMercadoriaItemMapper = solicitacaoMercadoriaItemMapper;
	}

	@Override
	protected void setValuesToEntity(SolicitacaoMercadoriaDTO dto, SolicitacaoMercadoria entity) {
		entity.setId(dto.getId());
		entity.setNumero(dto.getNumero());
		entity.setDepartamentoSolicitante(departamentoRepository.findById(dto.getDepartamentoSolicitanteId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento solicitante com id " + dto.getDepartamentoSolicitanteId())));
		entity.setGrupoContabilDestino(grupoContabilRepository.findById(dto.getGrupoContabilDestinoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo contábil destino com id " + dto.getGrupoContabilDestinoId())));
		entity.setDepartamentoSolicitado(departamentoRepository.findById(dto.getDepartamentoSolicitadoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento solicitado com id " + dto.getDepartamentoSolicitadoId())));
		entity.setDataSolicitacao(dto.getDataSolicitacao());
		entity.setSituacaoSolicitacaoMercadoria(
				SituacaoSolicitacaoMercadoria.fromValue(dto.getSituacaoSolicitacaoMercadoria()));
		entity.setObservacao(dto.getObservacao());
		if (dto.getItens() != null && dto.getItens().size() > 0) {
			entity.setItens(new ArrayList<>());
			entity.getItens().addAll(solicitacaoMercadoriaItemMapper.toEntity(dto.getItens()));
		}
	}

	@Override
	protected void setValuesToDto(SolicitacaoMercadoria entity, SolicitacaoMercadoriaDTO dto) {
		dto.setId(entity.getId());
		dto.setNumero(entity.getNumero());
		dto.setDepartamentoSolicitanteId(entity.getDepartamentoSolicitante().getId());
		dto.setDepartamentoSolicitanteNome(entity.getDepartamentoSolicitante().getNome());
		dto.setGrupoContabilDestinoId(entity.getGrupoContabilDestino().getId());
		dto.setGrupoContabilDestinoNome(entity.getGrupoContabilDestino().getNome());
		dto.setDepartamentoSolicitadoId(entity.getDepartamentoSolicitado().getId());
		dto.setDepartamentoSolicitadoNome(entity.getDepartamentoSolicitado().getNome());
		dto.setDataSolicitacao(entity.getDataSolicitacao());
		dto.setSituacaoSolicitacaoMercadoria(entity.getSituacaoSolicitacaoMercadoria().getValue());
		dto.setSituacaoSolicitacaoMercadoriaNome(entity.getSituacaoSolicitacaoMercadoria().getName());
		dto.setObservacao(entity.getObservacao());
		
		dto.setItens(new ArrayList<>());
		if (entity.getItens() != null) {
			dto.getItens().addAll(solicitacaoMercadoriaItemMapper.toDto(entity.getItens()));
		}
		
		dto.setEmpresaSolicitanteNome(entity.getDepartamentoSolicitante().getEmpresaFilial().getEmpresa().getNome());
		dto.setEmpresaFilialSolicitanteNome(entity.getDepartamentoSolicitante().getEmpresaFilial().getNome());
		
		dto.setEmpresaSolicitadoNome(entity.getDepartamentoSolicitado().getEmpresaFilial().getEmpresa().getNome());
		dto.setEmpresaFilialSolicitadoNome(entity.getDepartamentoSolicitado().getEmpresaFilial().getNome());
	}
}
