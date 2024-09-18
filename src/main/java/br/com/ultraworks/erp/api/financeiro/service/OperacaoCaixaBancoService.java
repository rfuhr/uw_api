package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoCaixaBancoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoCaixaBancoService
		extends GenericService<OperacaoCaixaBanco, Long, OperacaoCaixaBancoDTO> {

	@Autowired
	public OperacaoCaixaBancoService(OperacaoCaixaBancoRepository repository,
			OperacaoCaixaBancoMapper mapper) {
		super(repository, mapper);
	}

	public Object getProximoCodigo() {
		return ((OperacaoCaixaBancoRepository) repository).getProximoCodigo();
	}

}