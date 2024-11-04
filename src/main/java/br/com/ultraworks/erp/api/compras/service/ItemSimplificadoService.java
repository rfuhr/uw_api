package br.com.ultraworks.erp.api.compras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificadoDTO;
import br.com.ultraworks.erp.api.compras.mapper.ItemSimplificadoMapper;
import br.com.ultraworks.erp.api.compras.repository.ItemSimplificadoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ItemSimplificadoService extends GenericService<ItemSimplificado, Long, ItemSimplificadoDTO> {

	@Autowired
	public ItemSimplificadoService(ItemSimplificadoRepository repository, ItemSimplificadoMapper mapper) {
		super(repository, mapper);
	}
	
	public int getProximoCodigo() {
		return ((ItemSimplificadoRepository) repository).getProximoCodigo();
	}

}