package br.com.ultraworks.erp.api.agricola.service.romaneioagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.mapper.RomaneioAgricolaClassificacaoMapper;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaClassificacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RomaneioAgricolaClassificacaoService extends GenericService<RomaneioAgricolaClassificacao, Long, RomaneioAgricolaClassificacaoDTO> {

	@Autowired
	public RomaneioAgricolaClassificacaoService(RomaneioAgricolaClassificacaoRepository repository, RomaneioAgricolaClassificacaoMapper mapper) {
		super(repository, mapper);
	}

	public List<RomaneioAgricolaClassificacao> getAllByRomaneioAgricola(Long id) {
		List<RomaneioAgricolaClassificacao> listRegistros = new ArrayList<>();

		listRegistros.addAll(((RomaneioAgricolaClassificacaoRepository) repository).findByRomaneioAgricolaId(id));
		return listRegistros;
	}
	
	public void persistList(Long romaneioAgricolaId, List<RomaneioAgricolaClassificacao> classificacoes) {
		List<RomaneioAgricolaClassificacao> clasSalvos = ((RomaneioAgricolaClassificacaoRepository) repository)
				.findByRomaneioAgricolaId(romaneioAgricolaId);
		if (classificacoes != null)
			classificacoes.stream().forEach(clas -> {
				repository.save(clas);
			});
		List<RomaneioAgricolaClassificacao> clasExcluir = (List<RomaneioAgricolaClassificacao>) ListUtils
				.compararListasERetornaDiferenca(clasSalvos, classificacoes);
		clasExcluir.stream().forEach(oif -> repository.deleteById(oif.getId()));
	}
}