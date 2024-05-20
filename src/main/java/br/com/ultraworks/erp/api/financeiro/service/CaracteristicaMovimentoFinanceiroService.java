package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CaracteristicaMovimentoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CaracteristicaMovimentoFinanceiroService
		extends GenericService<CaracteristicaMovimentoFinanceiro, Long, CaracteristicaMovimentoFinanceiroDTO> {

	@Autowired
	public CaracteristicaMovimentoFinanceiroService(CaracteristicaMovimentoFinanceiroRepository repository,
			CaracteristicaMovimentoFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((CaracteristicaMovimentoFinanceiroRepository) repository).getProximoCodigo();
	}

}