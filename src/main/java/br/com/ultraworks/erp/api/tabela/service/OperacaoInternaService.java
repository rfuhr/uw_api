package br.com.ultraworks.erp.api.tabela.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaService extends GenericService<OperacaoInterna, Long, OperacaoInternaDTO> {

	OperacaoInternaFiscalService operacaoInternaFiscalService;

	@Autowired
	public OperacaoInternaService(OperacaoInternaRepository repository, OperacaoInternaMapper mapper,
			OperacaoInternaFiscalService operacaoInternaFiscalService) {
		super(repository, mapper);
		this.operacaoInternaFiscalService = operacaoInternaFiscalService;
	}

	@Override
	public Optional<OperacaoInterna> getById(Long id) {
		Optional<OperacaoInterna> operacaoInternaOptional = repository.findById(id);
		if (operacaoInternaOptional.isPresent()) {
			getDadosListasDependentes(operacaoInternaOptional.get());
		}
		return operacaoInternaOptional;
	}

	@Override
	public OperacaoInterna save(OperacaoInterna entity) {
		OperacaoInterna newEntity = repository.save(entity);
		if (entity.getOperacoesInternasFiscal() != null)
			entity.getOperacoesInternasFiscal().forEach(oi -> oi.setOperacaoInterna(newEntity));
		operacaoInternaFiscalService.persistList(newEntity.getId(), entity.getOperacoesInternasFiscal());

		return entity;
	}

	@Override
	public void delete(Long id) {
		Optional<OperacaoInterna> operOptionalOptional = this.getById(id);
		if (operOptionalOptional.isPresent()) {
			operacaoInternaFiscalService.deleteList(operOptionalOptional.get().getOperacoesInternasFiscal());
			repository.deleteById(id);
		}
	}

	private void getDadosListasDependentes(OperacaoInterna operacaoInterna) {
		if (operacaoInterna.getOperacoesInternasFiscal() == null) {
			operacaoInterna.setOperacoesInternasFiscal(new ArrayList<>());
		}
		operacaoInterna.getOperacoesInternasFiscal()
				.addAll(operacaoInternaFiscalService.getAllByOperacaoInterna(operacaoInterna.getId()));
	}
}