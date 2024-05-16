package br.com.ultraworks.erp.api.estoque.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueResponse;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.Tuple;

@Repository
public interface SaldoEstoqueRepository extends UWRepository<SaldoEstoque, Long> {
	
	@Query(value = "SELECT public.atualiza_saldo_estoque(:dataInicio, :dataFim, :itemId, :empresaFilialId, :localEstoqueId, :grupoContabilId, :userId)", nativeQuery = true)
	void atualizaSaldoEstoque(String dataInicio, String dataFim, Long itemId, Long empresaFilialId,
			Long localEstoqueId, Long grupoContabilId, Long userId);
	
	@Query(value = " select s.empresa_filial_id as empresaFilialId, ef.nome as empresaFilialNome, s.local_estoque_id as localEstoqueId, le.nome as localEstoqueNome, "
			+ "       s.grupo_contabil_id as grupoContabilId, gc.nome as grupoContabilNome, s.item_id as itemId, i.nome as itemNome, um.sigla as unidadeMedidaSigla, s.data, "
			+ "       s.saldo_quantidade as saldoQuantidade, s.saldo_valor as saldoValor, s.custo_medio as custoMedio "
			+ " from saldo_estoque s "
			+ "  inner join empresa_filial ef on s.empresa_filial_id = ef.id "
			+ "  inner join local_estoque le on s.local_estoque_id = le.id "
			+ "  inner join grupo_contabil gc on s.grupo_contabil_id = gc.id "
			+ "  inner join item i on s.item_id = i.id "
			+ "  left join unidade_medida um on i.unidade_medida_estoque_id = um.id "
			+ " where s.data between TO_DATE(:dataInicio, 'YYYY-MM-DD') and TO_DATE(:dataFinal, 'YYYY-MM-DD') "
			+ " and (1 = :validaEmpresaFilialId or s.empresa_filial_id = :empresaFilialId) "
			+ " and (1 = :validaLocalEstoque or s.local_estoque_id = :localEstoqueId) "
			+ " and (1 = :validaGrupoContabil or s.grupo_contabil_id = :grupoContabilId) "
			+ " and (1 = :validaItem or s.item_id = :itemId) "
			+ " order by s.empresa_filial_id, s.local_estoque_id, s.grupo_contabil_id, s.item_id, s.data ", nativeQuery = true)
	List<Tuple> buscaSaldoEstoque(String dataInicio, String dataFinal, int validaEmpresaFilialId, Long empresaFilialId, int validaLocalEstoque, Long localEstoqueId,
			int validaGrupoContabil, Long grupoContabilId, int validaItem, Long itemId);
	
	default List<SaldoEstoqueResponse> buscaSaldoEstoqueResponse(String dataInicio, String dataFinal, int validaEmpresaFilialId, Long empresaFilialId, int validaLocalEstoque, Long localEstoqueId,
			int validaGrupoContabil, Long grupoContabilId, int validaItem, Long itemId) {
		List<Tuple> tuples = buscaSaldoEstoque(dataInicio, dataFinal, validaEmpresaFilialId, empresaFilialId, validaLocalEstoque, localEstoqueId,
				validaGrupoContabil, grupoContabilId, validaItem, itemId);
		return tuples.stream().map(tuple -> {
			SaldoEstoqueResponse saldo = new SaldoEstoqueResponse();
			saldo.setEmpresaFilialId(tuple.get("empresaFilialId", Long.class));
			saldo.setLocalEstoqueId(tuple.get("localEstoqueId", Long.class));
			saldo.setGrupoContabilId(tuple.get("grupoContabilId", Long.class));
			saldo.setItemId(tuple.get("itemId", Long.class));
			saldo.setEmpresaFilialNome(tuple.get("empresaFilialNome", String.class));
			saldo.setLocalEstoqueNome(tuple.get("localEstoqueNome", String.class));
			saldo.setGrupoContabilNome(tuple.get("grupoContabilNome", String.class));
			saldo.setItemNome(tuple.get("itemNome", String.class));
			saldo.setUnidadeMedidaSigla(tuple.get("unidadeMedidaSigla", String.class));
			String data = tuple.get("data", Date.class).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(data, formatter);
			saldo.setData(localDate);
			saldo.setSaldoQuantidade(tuple.get("saldoQuantidade", BigDecimal.class));
			saldo.setSaldoValor(tuple.get("saldoValor", BigDecimal.class));
			saldo.setCustoMedio(tuple.get("custoMedio", BigDecimal.class));
			return saldo;
		}).collect(Collectors.toList());
	}

}
