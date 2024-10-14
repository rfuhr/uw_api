package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividadeDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRegraAtividadeRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroRegraAtividadeMapper extends GenericMapper<ParceiroRegraAtividade, ParceiroRegraAtividadeDTO> {

	private ParceiroRepository parceiroRepository;
	private RegraAtividadeRepository regraAtividadeRepository;

	public ParceiroRegraAtividadeMapper(ParceiroRegraAtividadeRepository parceiroRegraAtividadeRepository,
			ParceiroRepository parceiroRepository, RegraAtividadeRepository regraAtividadeRepository) {
		super(parceiroRegraAtividadeRepository, ParceiroRegraAtividade::new, ParceiroRegraAtividadeDTO::new);
		this.parceiroRepository = parceiroRepository;
		this.regraAtividadeRepository = regraAtividadeRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroRegraAtividadeDTO dto, ParceiroRegraAtividade entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroId() != null) {
			entity.setParceiro(parceiroRepository.findById(dto.getParceiroId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado de parceiro com id " + dto.getParceiroId())));
		}
		entity.setRegraAtividade(regraAtividadeRepository.findById(dto.getRegraAtividadeId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado regra de atividade com id " + dto.getRegraAtividadeId())));
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ParceiroRegraAtividade entity, ParceiroRegraAtividadeDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroId(entity.getParceiro().getId());
		dto.setRegraAtividadeId(entity.getRegraAtividade() != null ? entity.getRegraAtividade().getId() : null);
		dto.setRegraAtividadeNome(entity.getRegraAtividade() != null ? entity.getRegraAtividade().getNome() : null);
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
