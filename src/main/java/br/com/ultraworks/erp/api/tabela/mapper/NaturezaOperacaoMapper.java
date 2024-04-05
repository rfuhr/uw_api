package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacao;
import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.repository.NaturezaOperacaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class NaturezaOperacaoMapper extends GenericMapper<NaturezaOperacao, NaturezaOperacaoDTO> {

	public NaturezaOperacaoMapper(NaturezaOperacaoRepository repository) {
		super(repository, NaturezaOperacao::new, NaturezaOperacaoDTO::new);
	}

	@Override
	protected void setValuesToEntity(NaturezaOperacaoDTO dto, NaturezaOperacao entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setIndicadorOperacao(IndicadorOperacao.fromValue(dto.getIndicadorOperacao()));
	}

	@Override
	protected void setValuesToDto(NaturezaOperacao entity, NaturezaOperacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setIndicadorOperacao(entity.getIndicadorOperacao().getValue());
	}
}
