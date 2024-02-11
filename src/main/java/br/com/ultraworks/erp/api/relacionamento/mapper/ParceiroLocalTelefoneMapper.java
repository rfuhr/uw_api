package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefoneDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalTelefoneRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.TipoTelefone;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalTelefoneMapper extends GenericMapper<ParceiroLocalTelefone, ParceiroLocalTelefoneDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;

	public ParceiroLocalTelefoneMapper(ParceiroLocalTelefoneRepository parceiroTelefoneRepository,
			ParceiroLocalRepository parceiroLocalRepository) {
		super(parceiroTelefoneRepository, ParceiroLocalTelefone::new, ParceiroLocalTelefoneDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalTelefoneDTO dto, ParceiroLocalTelefone entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setTipoTelefone(TipoTelefone.fromCodigo(dto.getTipoTelefone()));
		entity.setIdentificacao(dto.getIdentificacao());
		entity.setNumero(dto.getNumero());

	}

	@Override
	protected void setValuesToDto(ParceiroLocalTelefone entity, ParceiroLocalTelefoneDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setTipoTelefone(entity.getTipoTelefone().getCodigo());
		dto.setIdentificacao(entity.getIdentificacao());
		dto.setNumero(entity.getNumero());
	}
}
