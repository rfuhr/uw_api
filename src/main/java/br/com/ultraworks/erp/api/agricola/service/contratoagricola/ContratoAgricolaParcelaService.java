package br.com.ultraworks.erp.api.agricola.service.contratoagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDesconto;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcela;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcelaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaParcelaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaDescontoRepository;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaParcelaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContratoAgricolaParcelaService extends GenericService<ContratoAgricolaParcela, Long, ContratoAgricolaParcelaDTO> {

	@Autowired
	public ContratoAgricolaParcelaService(ContratoAgricolaParcelaRepository repository, ContratoAgricolaParcelaMapper mapper) {
		super(repository, mapper);
	}

	public List<ContratoAgricolaParcela> getAllByContratoAgricola(Long id) {
		List<ContratoAgricolaParcela> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ContratoAgricolaParcelaRepository) repository).findByContratoAgricolaId(id));
		return listRegistros;
	}
	
	public void persistList(Long contratoAgricolaId, List<ContratoAgricolaParcela> parcelas) {
		List<ContratoAgricolaParcela> parcSalvos = ((ContratoAgricolaParcelaRepository) repository)
				.findByContratoAgricolaId(contratoAgricolaId);
		if (parcelas != null)
			parcelas.stream().forEach(parc -> {
				repository.save(parc);
			});
		List<ContratoAgricolaParcela> parcExcluir = (List<ContratoAgricolaParcela>) ListUtils
				.compararListasERetornaDiferenca(parcSalvos, parcelas);
		parcExcluir.stream().forEach(parc -> repository.deleteById(parc.getId()));
	}
}