package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CarteiraFinanceiraMapper extends GenericMapper<CarteiraFinanceira, CarteiraFinanceiraDTO> {

	public CarteiraFinanceiraMapper(CarteiraFinanceiraRepository repository) {
		super(repository, CarteiraFinanceira::new, CarteiraFinanceiraDTO::new);
	}

	@Override
	protected void setValuesToEntity(CarteiraFinanceiraDTO dto, CarteiraFinanceira entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setInformaBanco(dto.isInformaBanco());
		entity.setListaPosicaoTitulo(dto.isListaPosicaoTitulo());
	}

	@Override
	protected void setValuesToDto(CarteiraFinanceira entity, CarteiraFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setInformaBanco(entity.isInformaBanco());
		dto.setListaPosicaoTitulo(entity.isListaPosicaoTitulo());
	}
}
