package br.com.ultraworks.erp.api.agricola.service.romaneioagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNotaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.RomaneioAgricolaNotaMapper;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaNotaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RomaneioAgricolaNotaService extends GenericService<RomaneioAgricolaNota, Long, RomaneioAgricolaNotaDTO> {

	@Autowired
	public RomaneioAgricolaNotaService(RomaneioAgricolaNotaRepository repository, RomaneioAgricolaNotaMapper mapper) {
		super(repository, mapper);
	}

	public List<RomaneioAgricolaNota> getAllByRomaneioAgricola(Long id) {
		List<RomaneioAgricolaNota> listRegistros = new ArrayList<>();

		listRegistros.addAll(((RomaneioAgricolaNotaRepository) repository).findByRomaneioAgricolaId(id));
		return listRegistros;
	}

	public void persistList(Long romaneioAgricolaId, List<RomaneioAgricolaNota> notas) {
		List<RomaneioAgricolaNota> notasSalvos = ((RomaneioAgricolaNotaRepository) repository)
				.findByRomaneioAgricolaId(romaneioAgricolaId);
		if (notas != null)
			notas.stream().forEach(nota -> {
				repository.save(nota);
			});
		List<RomaneioAgricolaNota> notasExcluir = (List<RomaneioAgricolaNota>) ListUtils
				.compararListasERetornaDiferenca(notasSalvos, notas);
		notasExcluir.stream().forEach(nota -> repository.deleteById(nota.getId()));
	}
}