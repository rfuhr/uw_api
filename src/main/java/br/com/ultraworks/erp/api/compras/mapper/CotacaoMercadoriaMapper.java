package br.com.ultraworks.erp.api.compras.mapper;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoria.SituacaoCotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CotacaoMercadoriaMapper extends GenericMapper<CotacaoMercadoria, CotacaoMercadoriaDTO> {

	private DepartamentoRepository departamentoRepository;
	private ParceiroLocalRepository parceiroLocalRepository;
	private CotacaoMercadoriaParceiroMapper cotacaoMercadoriaParceiroMapper;

	public CotacaoMercadoriaMapper(CotacaoMercadoriaRepository cotacaoMercadoriaRepository,
			DepartamentoRepository departamentoRepository, ParceiroLocalRepository parceiroLocalRepository,
			CotacaoMercadoriaParceiroMapper cotacaoMercadoriaParceiroMapper) {
		super(cotacaoMercadoriaRepository, CotacaoMercadoria::new, CotacaoMercadoriaDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.cotacaoMercadoriaParceiroMapper = cotacaoMercadoriaParceiroMapper;
	}

	@Override
	protected void setValuesToEntity(CotacaoMercadoriaDTO dto, CotacaoMercadoria entity) {
		entity.setId(dto.getId());
		entity.setNumero(dto.getNumero());
		entity.setDepartamentoCotacao(departamentoRepository.findById(dto.getDepartamentoCotacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento cotação com id " + dto.getDepartamentoCotacaoId())));
		entity.setDataCotacao(dto.getDataCotacao());
		if (StringUtils.isNotBlank(dto.getSituacaoCotacaoMercadoria()))
			entity.setSituacaoCotacaoMercadoria(
					SituacaoCotacaoMercadoria.fromValue(dto.getSituacaoCotacaoMercadoria()));
		if (dto.getParceiros() != null && dto.getParceiros().size() > 0) {
			entity.setParceiros(new ArrayList<>());
			entity.getParceiros().addAll(cotacaoMercadoriaParceiroMapper.toEntity(dto.getParceiros()));
		}
	}

	@Override
	protected void setValuesToDto(CotacaoMercadoria entity, CotacaoMercadoriaDTO dto) {
		dto.setId(entity.getId());
		dto.setNumero(entity.getNumero());
		dto.setDepartamentoCotacaoId(entity.getDepartamentoCotacao().getId());
		dto.setDepartamentoCotacaoNome(entity.getDepartamentoCotacao().getNome());
		dto.setDataCotacao(entity.getDataCotacao());
		if (entity.getSituacaoCotacaoMercadoria() != null) {
			dto.setSituacaoCotacaoMercadoria(entity.getSituacaoCotacaoMercadoria().getValue());
			dto.setSituacaoCotacaoMercadoriaNome(entity.getSituacaoCotacaoMercadoria().getName());
		}
		dto.setParceiros(new ArrayList<>());
		if (entity.getParceiros() != null) {
			dto.getParceiros().addAll(cotacaoMercadoriaParceiroMapper.toDto(entity.getParceiros()));
		}
	}
}
