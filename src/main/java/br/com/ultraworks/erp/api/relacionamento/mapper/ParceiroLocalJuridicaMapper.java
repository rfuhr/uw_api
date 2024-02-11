package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisicaDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridicaDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalJuridicaRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalJuridicaMapper extends GenericMapper<ParceiroLocalJuridica, ParceiroLocalJuridicaDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;

	public ParceiroLocalJuridicaMapper(ParceiroLocalJuridicaRepository parceiroJuridicaRepository,
			ParceiroLocalRepository parceiroLocalRepository) {
		super(parceiroJuridicaRepository, ParceiroLocalJuridica::new, ParceiroLocalJuridicaDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalJuridicaDTO dto, ParceiroLocalJuridica entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setDataFundacao(dto.getDataFundacao());
		entity.setInscricaoEstadual(dto.getInscricaoEstadual());
	}

	@Override
	protected void setValuesToDto(ParceiroLocalJuridica entity, ParceiroLocalJuridicaDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setInscricaoEstadual(entity.getInscricaoEstadual());
		dto.setDataFundacao(entity.getDataFundacao());
	}
}
