package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.repository.PesagemClassificacaoRepository;
import br.com.ultraworks.erp.api.agricola.repository.PesagemRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PesagemClassificacaoMapper extends GenericMapper<PesagemClassificacao, PesagemClassificacaoDTO> {

	private PesagemRepository pesagemRepository;
	private TipoClassificacaoAgricolaRepository tipoClassificacaoAgricolaRepository;

	public PesagemClassificacaoMapper(PesagemClassificacaoRepository pesagemClassificacaoRepository,
			PesagemRepository pesagemRepository,
			TipoClassificacaoAgricolaRepository tipoClassificacaoAgricolaRepository) {
		super(pesagemClassificacaoRepository, PesagemClassificacao::new, PesagemClassificacaoDTO::new);
		this.pesagemRepository = pesagemRepository;
		this.tipoClassificacaoAgricolaRepository = tipoClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(PesagemClassificacaoDTO dto, PesagemClassificacao entity) {
		entity.setId(dto.getId());
		if (dto.getPesagemId() != null) {
			entity.setPesagem(pesagemRepository.findById(dto.getPesagemId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado pesagem com id " + dto.getPesagemId())));
		}
		if (dto.getTipoClassificacaoAgricolaId() != null) {
			entity.setTipoClassificacaoAgricola(tipoClassificacaoAgricolaRepository
					.findById(dto.getTipoClassificacaoAgricolaId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de classificação com id " + dto.getTipoClassificacaoAgricolaId())));
		}
		entity.setValor(dto.getValor());
		entity.setPercentualDesconto(dto.getPercentualDesconto());
		entity.setDesconto(dto.getDesconto());
	}

	@Override
	protected void setValuesToDto(PesagemClassificacao entity, PesagemClassificacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setPesagemId(entity.getPesagem().getId());
		dto.setTipoClassificacaoAgricolaId(entity.getTipoClassificacaoAgricola().getId());
		dto.setValor(entity.getValor());
		dto.setPercentualDesconto(entity.getPercentualDesconto());
		dto.setDesconto(entity.getDesconto());

		dto.setTipoClassificacaoAgricolaCodigo(entity.getTipoClassificacaoAgricola().getCodigo());
		dto.setTipoClassificacaoAgricolaNome(entity.getTipoClassificacaoAgricola().getNome());
	}
}
