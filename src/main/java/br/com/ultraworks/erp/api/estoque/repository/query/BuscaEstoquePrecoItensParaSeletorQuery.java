package br.com.ultraworks.erp.api.estoque.repository.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ItemMapper;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaEstoquePrecoItensParaSeletorQuery {

	EntityManager em;
	ItemMapper itemMapper;

	@SuppressWarnings({ "unchecked", "removal" })
	public List<ItemDTO> executeSQL(Long empresaFilialId, Long grupoContabilId, ItemService itemService) {
		List<ItemDTO> listaItens = new ArrayList<>();
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscaEstoquePrecoItensParaSeletor"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId)
				.setParameter("grupoContabilId", grupoContabilId)
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			for (Tuple tuple : resultTuples) {
				ItemDTO item = new ItemDTO();
				Optional<Item> itemBanco = itemService.getById(new Long(tuple.get("id").toString()));
				item = itemMapper.toDto(itemBanco.get());
				if (tuple.get("valor") != null) {
					item.setValor(new BigDecimal(tuple.get("valor").toString()));
				}
				if (tuple.get("quantidade") != null) {
					item.setSaldoEstoque(new BigDecimal(tuple.get("quantidade").toString()));
				}
				if (tuple.get("valor_atual") != null) {
					item.setValorAtual(new BigDecimal(tuple.get("valor_atual").toString()));
				}
				if (tuple.get("promocional") != null) {
					item.setPromocional(new Boolean(tuple.get("promocional").toString()));
				}
				listaItens.add(item);
			}
		}
		return listaItens;
	}
}
