package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmailDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalEmailRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipoemail.TipoEmail;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalEmailMapper extends GenericMapper<ParceiroLocalEmail, ParceiroLocalEmailDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;

	public ParceiroLocalEmailMapper(ParceiroLocalEmailRepository parceiroEmailRepository,
			ParceiroLocalRepository parceiroLocalRepository) {
		super(parceiroEmailRepository, ParceiroLocalEmail::new, ParceiroLocalEmailDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalEmailDTO dto, ParceiroLocalEmail entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setTipoEmail(TipoEmail.fromCodigo(dto.getTipoEmail()));
		entity.setIdentificacao(dto.getIdentificacao());
		entity.setEmail(dto.getEmail());

	}

	@Override
	protected void setValuesToDto(ParceiroLocalEmail entity, ParceiroLocalEmailDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setTipoEmail(entity.getTipoEmail().getCodigo());
		dto.setIdentificacao(entity.getIdentificacao());
		dto.setEmail(entity.getEmail());
	}
}
