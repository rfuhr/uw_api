package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.MensagemFiscalRepository;
import br.com.ultraworks.erp.api.tabela.repository.UfRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MensagemFiscalMapper extends GenericMapper<MensagemFiscal, MensagemFiscalDTO> {
	
	UfRepository ufRepository;

	public MensagemFiscalMapper(MensagemFiscalRepository MensagemFiscalRepository, UfRepository ufRepository) {
		super(MensagemFiscalRepository, MensagemFiscal::new, MensagemFiscalDTO::new);
		this.ufRepository = ufRepository;
    }

	@Override
	protected void setValuesToEntity(MensagemFiscalDTO dto, MensagemFiscal entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setCodigoAjuste(dto.getCodigoAjuste());
		entity.setSigla(dto.getSigla());
		entity.setObsFiscal(dto.getObsFiscal());
		if (dto.getUfId() != null) {
			entity.setUf(ufRepository.findById(dto.getUfId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada UF com id " + dto.getUfId())));
		} else {
			entity.setUf(null);
		}
	}

	@Override
	protected void setValuesToDto(MensagemFiscal entity, MensagemFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setCodigoAjuste(entity.getCodigoAjuste());
		dto.setSigla(entity.getSigla());
		dto.setObsFiscal(entity.getObsFiscal());
		if (entity.getUf() != null) {
			dto.setUfId(entity.getUf().getId());
			dto.setUfNome(entity.getUf().getNome());
			dto.setUfSigla(entity.getUf().getSigla());
		}
	}	
}
