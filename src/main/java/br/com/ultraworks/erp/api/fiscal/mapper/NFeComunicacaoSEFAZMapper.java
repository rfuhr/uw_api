package br.com.ultraworks.erp.api.fiscal.mapper;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeComunicacaoSEFAZ;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.NFeComunicacaoSEFAZResponse;
import br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe.TipoComunicacaoNfe;
import br.com.ultraworks.erp.api.fiscal.repository.NFeComunicacaoSEFAZRepository;
import br.com.ultraworks.erp.api.fiscal.repository.NFeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class NFeComunicacaoSEFAZMapper extends GenericMapper<NFeComunicacaoSEFAZ, NFeComunicacaoSEFAZResponse> {
	
	NFeRepository nFeRepository;

	public NFeComunicacaoSEFAZMapper(NFeComunicacaoSEFAZRepository nFeComunicacaoSEFAZRepository, NFeRepository nFeRepository) {
		super(nFeComunicacaoSEFAZRepository, NFeComunicacaoSEFAZ::new, NFeComunicacaoSEFAZResponse::new);
		this.nFeRepository = nFeRepository;
    }

	@Override
	protected void setValuesToEntity(NFeComunicacaoSEFAZResponse dto, NFeComunicacaoSEFAZ entity) {
		entity.setId(dto.getId());
		if (dto.getNfeId() != null) {
			entity.setNfe(nFeRepository.findById(dto.getNfeId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado NFe com id " + dto.getNfeId())));			
		}
		entity.setTipoComunicacaoNfe(TipoComunicacaoNfe.fromValue(dto.getTipoComunicacaoNfe()));
		entity.setNprotnfe(dto.getNprotnfe());
		entity.setNrecibo(dto.getNrecibo());
		entity.setStatus(dto.getStatus());
		entity.setXmlEnvio(dto.getXmlEnvio().getBytes());
		entity.setXmlRetorno(dto.getXmlRetorno().getBytes());
		entity.setCstat(dto.getCstat());
		entity.setCriadoEm(dto.getCriadoEm());
		entity.setXmotivo(dto.getXmotivo());
	}

	@Override
	protected void setValuesToDto(NFeComunicacaoSEFAZ entity, NFeComunicacaoSEFAZResponse dto) {
		dto.setId(entity.getId());
		if (entity.getNfe() != null) {
			dto.setNfeId(entity.getNfe().getId());
		}
		dto.setNprotnfe(entity.getNprotnfe());
		dto.setNrecibo(entity.getNrecibo());
		dto.setStatus(entity.getStatus());
		dto.setXmlEnvio(new String(entity.getXmlEnvio(), StandardCharsets.UTF_8));
		dto.setXmlRetorno(new String(entity.getXmlRetorno(), StandardCharsets.UTF_8));
		dto.setCstat(entity.getCstat());
		dto.setCriadoEm(entity.getCriadoEm());
		dto.setXmotivo(entity.getXmotivo());
		dto.setTipoComunicacaoNfe(entity.getTipoComunicacaoNfe().getValue());
		dto.setTipoComunicacaoNfeName(entity.getTipoComunicacaoNfe().getName());
	}	
}
