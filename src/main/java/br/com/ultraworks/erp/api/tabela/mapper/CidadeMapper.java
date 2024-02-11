package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.cidade.CidadeDTO;
import br.com.ultraworks.erp.api.tabela.repository.CidadeRepository;
import br.com.ultraworks.erp.api.tabela.service.PaisService;
import br.com.ultraworks.erp.api.tabela.service.UfService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CidadeMapper extends GenericMapper<Cidade, CidadeDTO> {

	UfMapper ufMapper;
	PaisMapper paisMapper;
	PaisService paisService;
	UfService ufService;
	
	public CidadeMapper(CidadeRepository cidadeRepository, UfMapper ufMapper, PaisMapper paisMapper, PaisService paisService, UfService ufService) {
		super(cidadeRepository, Cidade::new, CidadeDTO::new);
		this.ufMapper = ufMapper;
		this.paisMapper = paisMapper;
		this.paisService = paisService;
		this.ufService = ufService;
    }

	@Override
	protected void setValuesToEntity(CidadeDTO dto, Cidade entity) {
		entity.setId(dto.getId());
		entity.setCodigoIBGE(dto.getCodigoIBGE());
		entity.setNome(dto.getNome());
		entity.setPais(paisService.getById(dto.getPais().getId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado país com id " + dto.getPais().getId())));
		entity.setUf(ufService.getById(dto.getUf().getId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado uf com id " + dto.getUf().getId())));
		entity.setLatitude(dto.getLatitude());
		entity.setLongitude(dto.getLongitude());
		entity.setCapital(dto.getCapital());
	}

	@Override
	protected void setValuesToDto(Cidade entity, CidadeDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigoIBGE(entity.getCodigoIBGE());
		dto.setNome(entity.getNome());
		dto.setSiglaPais(entity.getPais().getSigla());
		dto.setSiglaUF(entity.getUf().getSigla());
		dto.setUf(ufMapper.toDto(entity.getUf()));
		dto.setPais(paisMapper.toDto(entity.getPais()));
		dto.setLatitude(entity.getLatitude());
		dto.setLongitude(entity.getLongitude());
		dto.setCapital(entity.getCapital());
		
	}


	
}