package br.com.ultraworks.erp.api.tabela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfop;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfopDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaFiscalCfopMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalCfopRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaFiscalCfopService
		extends GenericService<OperacaoInternaFiscalCfop, Long, OperacaoInternaFiscalCfopDTO> {

	@Autowired
	public OperacaoInternaFiscalCfopService(OperacaoInternaFiscalCfopRepository repository,
			OperacaoInternaFiscalCfopMapper mapper) {
		super(repository, mapper);
	}

	public List<OperacaoInternaFiscalCfop> getAllByOperacaoInternaFiscalId(Long operacaoInternaFiscalId) {
		return ((OperacaoInternaFiscalCfopRepository) repository)
				.findByOperacaoInternaFiscalId(operacaoInternaFiscalId);
	}

	public void persistList(Long operacaoInternaFiscalId, List<OperacaoInternaFiscalCfop> cfops) {
		List<OperacaoInternaFiscalCfop> cfopsSalvos = ((OperacaoInternaFiscalCfopRepository) repository)
				.findByOperacaoInternaFiscalId(operacaoInternaFiscalId);
		if (cfops != null)
			cfops.stream().forEach(c -> repository.save(c));

		List<OperacaoInternaFiscalCfop> cfopsExcluir = (List<OperacaoInternaFiscalCfop>) ListUtils
				.compararListasERetornaDiferenca(cfopsSalvos, cfops);
		if (cfopsExcluir.size() > 0) {
			cfopsExcluir.forEach(cfop -> {
				repository.deleteById(cfop.getId());
			});
		}

	}

	public void deleteList(List<OperacaoInternaFiscalCfop> cfops) {
		if (cfops != null && !cfops.isEmpty()) {
			cfops.forEach(c -> repository.deleteById(c.getId()));
		}
		
	}
}