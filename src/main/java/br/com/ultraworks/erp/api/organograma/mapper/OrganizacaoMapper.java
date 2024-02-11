package br.com.ultraworks.erp.api.organograma.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.organograma.domain.organizacao.Organizacao;
import br.com.ultraworks.erp.api.organograma.domain.organizacao.OrganizacaoDTO;
import br.com.ultraworks.erp.api.organograma.repository.OrganizacaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OrganizacaoMapper extends GenericMapper<Organizacao, OrganizacaoDTO> {

	public OrganizacaoMapper(OrganizacaoRepository organizacaoRepository) {
		super(organizacaoRepository, Organizacao::new, OrganizacaoDTO::new);
	}

	@Override
	protected void setValuesToEntity(OrganizacaoDTO dto, Organizacao entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setTenant(dto.getTenant());
	}

	@Override
	protected void setValuesToDto(Organizacao entity, OrganizacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setTenant(entity.getTenant());

	}
}
