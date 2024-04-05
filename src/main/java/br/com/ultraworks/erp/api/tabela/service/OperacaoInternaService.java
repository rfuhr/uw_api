package br.com.ultraworks.erp.api.tabela.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
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
			operacaoInternaOptional.get().setOperacaoInternaFiscal(
					operacaoInternaFiscalService.findByOperacaoInternaId(operacaoInternaOptional.get().getId()));
		}
		return operacaoInternaOptional;
	}
	
	@Override
	public OperacaoInterna save(OperacaoInterna entity) {
		OperacaoInternaFiscal operacaoInternaFiscalSalvo = null;
		if (entity.getId() != null) {
			operacaoInternaFiscalSalvo = operacaoInternaFiscalService.findByOperacaoInternaId(entity.getId());
		}
		repository.save(entity);

		if (entity.getOperacaoInternaFiscal() != null) {
				entity.getOperacaoInternaFiscal().setOperacaoInterna(entity);
				entity.setOperacaoInternaFiscal(operacaoInternaFiscalService.save(entity.getOperacaoInternaFiscal()));
		}

		if (operacaoInternaFiscalSalvo != null && !entity.getOperacaoInternaFiscal().getId().equals(operacaoInternaFiscalSalvo.getId())) {
			operacaoInternaFiscalService.delete(operacaoInternaFiscalSalvo.getId());
		}

		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<OperacaoInterna> operOptionalOptional = this.getById(id);
		if (operOptionalOptional.isPresent()) {
			if (operOptionalOptional.get().getOperacaoInternaFiscal() != null)
				operacaoInternaFiscalService.delete(operOptionalOptional.get().getOperacaoInternaFiscal().getId());
			repository.deleteById(id);
		}
	}
}