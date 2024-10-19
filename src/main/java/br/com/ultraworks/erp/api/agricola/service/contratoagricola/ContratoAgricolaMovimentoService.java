package br.com.ultraworks.erp.api.agricola.service.contratoagricola;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimento;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimentoDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaMovimentoMapper;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaMovimentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContratoAgricolaMovimentoService
		extends GenericService<ContratoAgricolaMovimento, Long, ContratoAgricolaMovimentoDTO> {

	@Autowired
	public ContratoAgricolaMovimentoService(ContratoAgricolaMovimentoRepository repository,
			ContratoAgricolaMovimentoMapper mapper) {
		super(repository, mapper);
	}

	public List<ContratoAgricolaMovimento> getAllByContratoAgricola(Long id) {
		List<ContratoAgricolaMovimento> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ContratoAgricolaMovimentoRepository) repository).findByContratoAgricolaId(id));
		return listRegistros;
	}

}