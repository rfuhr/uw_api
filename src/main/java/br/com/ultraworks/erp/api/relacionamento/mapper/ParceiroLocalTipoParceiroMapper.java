package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalTipoParceiroRepository;
import br.com.ultraworks.erp.api.tabela.repository.TipoParceiroRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalTipoParceiroMapper extends GenericMapper<ParceiroLocalTipoParceiro, ParceiroLocalTipoParceiroDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;
	private TipoParceiroRepository tipoParceiroRepository;

	public ParceiroLocalTipoParceiroMapper(ParceiroLocalTipoParceiroRepository parceiroTipoParceiroRepository,
			ParceiroLocalRepository parceiroLocalRepository, TipoParceiroRepository tipoParceiroRepository) {
		super(parceiroTipoParceiroRepository, ParceiroLocalTipoParceiro::new, ParceiroLocalTipoParceiroDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.tipoParceiroRepository = tipoParceiroRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalTipoParceiroDTO dto, ParceiroLocalTipoParceiro entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setTipoParceiro(tipoParceiroRepository.findById(dto.getTipoParceiroId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado tipo de parceiro com id " + dto.getTipoParceiroId())));
	}

	@Override
	protected void setValuesToDto(ParceiroLocalTipoParceiro entity, ParceiroLocalTipoParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setTipoParceiroId(entity.getTipoParceiro() != null ? entity.getTipoParceiro().getId() : null );
		dto.setTipoParceiroNome(entity.getTipoParceiro() != null ? entity.getTipoParceiro().getNome() : null );
	}
}
