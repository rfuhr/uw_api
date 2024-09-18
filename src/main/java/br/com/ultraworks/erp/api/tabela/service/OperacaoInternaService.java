package br.com.ultraworks.erp.api.tabela.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaService extends GenericService<OperacaoInterna, Long, OperacaoInternaDTO> {

	OperacaoInternaFiscalService operacaoInternaFiscalService;
	OperacaoInternaEstoqueService operacaoInternaEstoqueService;
	OperacaoInternaAgricolaService operacaoInternaAgricolaService;

	@Autowired
	public OperacaoInternaService(OperacaoInternaRepository repository, OperacaoInternaMapper mapper,
			OperacaoInternaFiscalService operacaoInternaFiscalService,
			OperacaoInternaEstoqueService operacaoInternaEstoqueService,
			OperacaoInternaAgricolaService operacaoInternaAgricolaService) {
		super(repository, mapper);
		this.operacaoInternaFiscalService = operacaoInternaFiscalService;
		this.operacaoInternaEstoqueService = operacaoInternaEstoqueService;
		this.operacaoInternaAgricolaService = operacaoInternaAgricolaService;
	}

	@Override
	public Optional<OperacaoInterna> getById(Long id) {
		Optional<OperacaoInterna> operacaoInternaOptional = repository.findById(id);
		if (operacaoInternaOptional.isPresent()) {
			getDadosListasDependentes(operacaoInternaOptional.get());
			operacaoInternaOptional.get().setOperacaoInternaEstoque(operacaoInternaEstoqueService.getByOperacaoInterna(id));
			operacaoInternaOptional.get().setOperacaoInternaAgricola(operacaoInternaAgricolaService.getByOperacaoInterna(id));
		}
		return operacaoInternaOptional;
	}

	@Override
	public OperacaoInterna save(OperacaoInterna entity) {
		OperacaoInterna newEntity = repository.save(entity);
		if (!entity.isCaracteristicaFiscal()) {
			List<OperacaoInternaFiscal> lista = operacaoInternaFiscalService.getAllByOperacaoInterna(newEntity.getId());
			if (lista != null && lista.size() > 0) {
				operacaoInternaFiscalService.deleteList(lista);				
			}
		} else if (entity.getOperacoesInternasFiscal() != null) {
			entity.getOperacoesInternasFiscal().forEach(oi -> oi.setOperacaoInterna(newEntity));
			operacaoInternaFiscalService.persistList(newEntity.getId(), entity.getOperacoesInternasFiscal());
		}
		
		if (!entity.isCaracteristicaEstoque()) {
			OperacaoInternaEstoque oper = operacaoInternaEstoqueService.getByOperacaoInterna(newEntity.getId());
			if (oper != null) {
				operacaoInternaEstoqueService.delete(oper.getId());
			}
		} else if (entity.getOperacaoInternaEstoque() != null) {
			entity.getOperacaoInternaEstoque().setOperacaoInterna(newEntity);
			operacaoInternaEstoqueService.save(entity.getOperacaoInternaEstoque());
		}
		
		if (!entity.isCaracteristicaAgricola()) {
			OperacaoInternaAgricola oper = operacaoInternaAgricolaService.getByOperacaoInterna(newEntity.getId());
			if (oper != null) {
				operacaoInternaAgricolaService.delete(oper.getId());
			}
		} else if (entity.getOperacaoInternaAgricola() != null) {
			entity.getOperacaoInternaAgricola().setOperacaoInterna(newEntity);
			operacaoInternaAgricolaService.save(entity.getOperacaoInternaAgricola());
		}
		
		return entity;
	}

	@Override
	public void delete(Long id) {
		Optional<OperacaoInterna> operOptionalOptional = this.getById(id);
		if (operOptionalOptional.isPresent()) {
			operacaoInternaFiscalService.deleteList(operOptionalOptional.get().getOperacoesInternasFiscal());
			if (operOptionalOptional.get().getOperacaoInternaEstoque() != null) {
				operacaoInternaEstoqueService.delete(operOptionalOptional.get().getOperacaoInternaEstoque().getId());
			}
			if (operOptionalOptional.get().getOperacaoInternaAgricola() != null) {
				operacaoInternaAgricolaService.delete(operOptionalOptional.get().getOperacaoInternaAgricola().getId());
			}
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