package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.TipoIncentivoFiscalRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoIncentivoFiscalMapper extends GenericMapper<TipoIncentivoFiscal, TipoIncentivoFiscalDTO> {

	public TipoIncentivoFiscalMapper(TipoIncentivoFiscalRepository TipoIncentivoFiscalRepository) {
		super(TipoIncentivoFiscalRepository, TipoIncentivoFiscal::new, TipoIncentivoFiscalDTO::new);
    }

	@Override
	protected void setValuesToEntity(TipoIncentivoFiscalDTO dto, TipoIncentivoFiscal entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(TipoIncentivoFiscal entity, TipoIncentivoFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}	
}
