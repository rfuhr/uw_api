package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CarteiraFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CarteiraFinanceiraService extends GenericService<CarteiraFinanceira, Long, CarteiraFinanceiraDTO> {

	@Autowired
	public CarteiraFinanceiraService(CarteiraFinanceiraRepository repository, CarteiraFinanceiraMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((CarteiraFinanceiraRepository) repository).getProximoCodigo();
	}
}