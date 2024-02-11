package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CaracteristicaMovimentoFinanceiroMapper extends GenericMapper<CaracteristicaMovimentoFinanceiro, CaracteristicaMovimentoFinanceiroDTO> {

	public CaracteristicaMovimentoFinanceiroMapper(CaracteristicaMovimentoFinanceiroRepository repository) {
		super(repository, CaracteristicaMovimentoFinanceiro::new, CaracteristicaMovimentoFinanceiroDTO::new);
    }

	@Override
	protected void setValuesToEntity(CaracteristicaMovimentoFinanceiroDTO dto, CaracteristicaMovimentoFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(CaracteristicaMovimentoFinanceiro entity, CaracteristicaMovimentoFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

