package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscalDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaFiscalMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaFiscalService
		extends GenericService<OperacaoInternaFiscal, Long, OperacaoInternaFiscalDTO> {

	@Autowired
	public OperacaoInternaFiscalService(OperacaoInternaFiscalRepository repository,
			OperacaoInternaFiscalMapper mapper) {
		super(repository, mapper);
	}

	public OperacaoInternaFiscal findByOperacaoInternaId(Long operacaoInternaId) {
		return ((OperacaoInternaFiscalRepository) repository).findByOperacaoInternaId(operacaoInternaId);
	}
}