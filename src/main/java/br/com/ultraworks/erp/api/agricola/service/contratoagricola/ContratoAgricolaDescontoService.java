package br.com.ultraworks.erp.api.agricola.service.contratoagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDesconto;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDescontoDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaDescontoMapper;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaDescontoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContratoAgricolaDescontoService extends GenericService<ContratoAgricolaDesconto, Long, ContratoAgricolaDescontoDTO> {

	@Autowired
	public ContratoAgricolaDescontoService(ContratoAgricolaDescontoRepository repository, ContratoAgricolaDescontoMapper mapper) {
		super(repository, mapper);
	}

	public List<ContratoAgricolaDesconto> getAllByContratoAgricola(Long id) {
		List<ContratoAgricolaDesconto> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ContratoAgricolaDescontoRepository) repository).findByContratoAgricolaId(id));
		return listRegistros;
	}
	
	public void persistList(Long contratoAgricolaId, List<ContratoAgricolaDesconto> descontos) {
		List<ContratoAgricolaDesconto> descSalvos = ((ContratoAgricolaDescontoRepository) repository)
				.findByContratoAgricolaId(contratoAgricolaId);
		if (descontos != null)
			descontos.stream().forEach(desc -> {
				repository.save(desc);
			});
		List<ContratoAgricolaDesconto> descExcluir = (List<ContratoAgricolaDesconto>) ListUtils
				.compararListasERetornaDiferenca(descSalvos, descontos);
		descExcluir.stream().forEach(desc -> repository.deleteById(desc.getId()));
	}
}