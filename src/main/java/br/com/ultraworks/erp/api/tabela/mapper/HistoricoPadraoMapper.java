package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadraoDTO;
import br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade.TipoObrigatoriedade;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class HistoricoPadraoMapper extends GenericMapper<HistoricoPadrao, HistoricoPadraoDTO> {

	public HistoricoPadraoMapper(HistoricoPadraoRepository repository) {
		super(repository, HistoricoPadrao::new, HistoricoPadraoDTO::new);
	}

	@Override
	protected void setValuesToEntity(HistoricoPadraoDTO dto, HistoricoPadrao entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
		entity.setSigla(dto.getSigla());
		entity.setInformaDocumento(TipoObrigatoriedade.fromValue(dto.getInformaDocumento()));
	}

	@Override
	protected void setValuesToDto(HistoricoPadrao entity, HistoricoPadraoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
		dto.setSigla(entity.getSigla());
		dto.setInformaDocumento(entity.getInformaDocumento().getValue());
	}
}
