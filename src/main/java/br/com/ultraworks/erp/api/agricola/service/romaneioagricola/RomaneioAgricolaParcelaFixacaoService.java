package br.com.ultraworks.erp.api.agricola.service.romaneioagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacaoDTO;
import br.com.ultraworks.erp.api.agricola.mapper.RomaneioAgricolaParcelaFixacaoMapper;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaParcelaFixacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RomaneioAgricolaParcelaFixacaoService extends GenericService<RomaneioAgricolaParcelaFixacao, Long, RomaneioAgricolaParcelaFixacaoDTO> {

	@Autowired
	public RomaneioAgricolaParcelaFixacaoService(RomaneioAgricolaParcelaFixacaoRepository repository, RomaneioAgricolaParcelaFixacaoMapper mapper) {
		super(repository, mapper);
	}

	public List<RomaneioAgricolaParcelaFixacao> getAllByRomaneioAgricola(Long id) {
		List<RomaneioAgricolaParcelaFixacao> listRegistros = new ArrayList<>();

		listRegistros.addAll(((RomaneioAgricolaParcelaFixacaoRepository) repository).findByRomaneioAgricolaId(id));
		return listRegistros;
	}

	public void persistList(Long romaneioAgricolaId, List<RomaneioAgricolaParcelaFixacao> notas) {
		List<RomaneioAgricolaParcelaFixacao> parcelasSalvos = ((RomaneioAgricolaParcelaFixacaoRepository) repository)
				.findByRomaneioAgricolaId(romaneioAgricolaId);
		if (notas != null)
			notas.stream().forEach(parcela -> {
				repository.save(parcela);
			});
		List<RomaneioAgricolaParcelaFixacao> notasExcluir = (List<RomaneioAgricolaParcelaFixacao>) ListUtils
				.compararListasERetornaDiferenca(parcelasSalvos, notas);
		notasExcluir.stream().forEach(parcela -> repository.deleteById(parcela.getId()));
	}
}