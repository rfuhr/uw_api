package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.CfopDTO;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CfopMapper extends GenericMapper<Cfop, CfopDTO> {

	public CfopMapper(CfopRepository CfopRepository) {
		super(CfopRepository, Cfop::new, CfopDTO::new);
    }

	@Override
	protected void setValuesToEntity(CfopDTO dto, Cfop entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setDestinoOperacao(DestinoOperacao.fromValue(dto.getDestinoOperacao()));
	}

	@Override
	protected void setValuesToDto(Cfop entity, CfopDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setDestinoOperacao(entity.getDestinoOperacao().getValue());
	}	
}
