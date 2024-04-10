package br.com.ultraworks.erp.api.tabela.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscalDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfop;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaFiscalMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalCfopRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaFiscalService
		extends GenericService<OperacaoInternaFiscal, Long, OperacaoInternaFiscalDTO> {

	private OperacaoInternaFiscalCfopService operacaoInternaFiscalCfopService;

	@Autowired
	public OperacaoInternaFiscalService(OperacaoInternaFiscalRepository repository, OperacaoInternaFiscalMapper mapper,
			OperacaoInternaFiscalCfopService operacaoInternaFiscalCfopService) {
		super(repository, mapper);
		this.operacaoInternaFiscalCfopService = operacaoInternaFiscalCfopService;
	}

	@Override
	public Optional<OperacaoInternaFiscal> getById(Long id) {
		Optional<OperacaoInternaFiscal> optional = repository.findById(id);
		if (optional.isPresent()) {
			getDadosListasDependentes(optional.get());
		}
		return optional;
	}

	public List<OperacaoInternaFiscal> getAllByOperacaoInterna(Long id) {
		List<OperacaoInternaFiscal> listRegistros = new ArrayList<>();

		((OperacaoInternaFiscalRepository) repository).findByOperacaoInternaId(id).forEach(operacaoInternaLocal -> {
			getDadosListasDependentes(operacaoInternaLocal);
			listRegistros.add(operacaoInternaLocal);
		});
		return listRegistros;
	}

	private void getDadosListasDependentes(OperacaoInternaFiscal operacaoInternaFiscal) {
		if (operacaoInternaFiscal.getCfops() == null) {
			operacaoInternaFiscal.setCfops(new ArrayList<>());
		}
		operacaoInternaFiscal.getCfops().addAll(
				operacaoInternaFiscalCfopService.getAllByOperacaoInternaFiscalId(operacaoInternaFiscal.getId()));
	}

	public void persistList(Long operacaoInternaId, List<OperacaoInternaFiscal> operacoesInternasFiscal) {
		List<OperacaoInternaFiscal> oiFiscalSalvos = ((OperacaoInternaFiscalRepository) repository)
				.findByOperacaoInternaId(operacaoInternaId);
		if (operacoesInternasFiscal != null)
			operacoesInternasFiscal.stream().forEach(oif -> {
				repository.save(oif);
				if (oif.getCfops() != null)
					oif.getCfops().stream().forEach(c -> {
						c.setOperacaoInternaFiscal(oif);
					});
				operacaoInternaFiscalCfopService.persistList(oif.getId(), oif.getCfops());
			});
		List<OperacaoInternaFiscal> oiFiscalExcluir = (List<OperacaoInternaFiscal>) ListUtils
				.compararListasERetornaDiferenca(oiFiscalSalvos, operacoesInternasFiscal);
		oiFiscalExcluir.stream().forEach(oif -> repository.deleteById(oif.getId()));
	}

	public void deleteList(List<OperacaoInternaFiscal> list) {
		if (list != null && !list.isEmpty() ) {
			list.stream().forEach(l -> {
				operacaoInternaFiscalCfopService.deleteList(l.getCfops());
				repository.deleteById(l.getId());
			});
		}
	}
}