package br.com.ultraworks.erp.api.estoque.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueResponse;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.Tuple;

@Repository
public interface MovimentoEstoqueRepository extends UWRepository<MovimentoEstoque, Long> {

	@Query(value = " select s.empresa_filial_id as empresaFilialId, ef.nome as empresaFilialNome, s.local_estoque_id as localEstoqueId, le.codigo as localEstoqueCodigo, le.nome as localEstoqueNome, "
			+ "       s.grupo_contabil_id as grupoContabilId, gc.codigo as grupoContabilCodigo, gc.nome as grupoContabilNome, s.item_id as itemId, i.codigo as itemCodigo, i.nome as itemNome, um.sigla as unidadeMedidaSigla, s.data,"
			+ "       tp.name as tipoMovto, s.entrada, s.documento, oper.id as operacaoInternaId, oper.nome as operacaoInternaNome, oper.sigla as operacaoInternaSigla,"
			+ "       s.quantidade, opfis.credito_debito as credDebQtde, s.valor, opfin.credito_debito as credDebValor, s.custo_medio as custoMedio"
			+ " from movimento_estoque s "
			+ "  inner join empresa_filial ef on s.empresa_filial_id = ef.id "
			+ "  inner join local_estoque le on s.local_estoque_id = le.id"
			+ "  inner join grupo_contabil gc on s.grupo_contabil_id = gc.id"
			+ "  inner join item i on s.item_id = i.id"
			+ "  left join unidade_medida um on i.unidade_medida_estoque_id = um.id"
			+ "  inner join tipo_movimento_estoque tp on s.tipo_movimento_estoque = tp.value"
			+ "  inner join operacao_estoque opfis ON opfis.value = s.operacao_estoque_fisico"
			+ "  inner join operacao_estoque opfin ON opfin.value = s.operacao_estoque_financeiro"
			+ "  inner join operacao_interna oper on oper.id = s.operacao_interna_id "
			+ " where s.data between TO_DATE(:dataInicio, 'YYYY-MM-DD') and TO_DATE(:dataFinal, 'YYYY-MM-DD') "
			+ " and (1 = :validaEmpresaFilialId or s.empresa_filial_id = :empresaFilialId) "
			+ " and (1 = :validaLocalEstoque or s.local_estoque_id = :localEstoqueId) "
			+ " and (1 = :validaGrupoContabil or s.grupo_contabil_id = :grupoContabilId) "
			+ " and (1 = :validaItem or s.item_id = :itemId) "
			+ " and (1 = :validaOperacaoInterna or s.operacao_interna_id = :operacaoInternaId) "
			+ " and (1 = :validaDocumento or s.documento = :documento) "
			+ " order by s.empresa_filial_id, s.local_estoque_id, s.grupo_contabil_id, s.item_id, s.data, tp.value, s.documento ", nativeQuery = true)
	List<Tuple> buscaMovimentoEstoque(String dataInicio, String dataFinal, int validaEmpresaFilialId, Long empresaFilialId, int validaLocalEstoque, Long localEstoqueId,
			int validaGrupoContabil, Long grupoContabilId, int validaItem, Long itemId, int validaOperacaoInterna, Long operacaoInternaId, int validaDocumento, String documento);
	
	default List<MovimentoEstoqueResponse> buscaMovimentoEstoqueResponse(String dataInicio, String dataFinal, int validaEmpresaFilialId, Long empresaFilialId, int validaLocalEstoque, Long localEstoqueId,
			int validaGrupoContabil, Long grupoContabilId, int validaItem, Long itemId, int validaOperacaoInterna, Long operacaoInternaId, int validaDocumento, String documento) {
		List<Tuple> tuples = buscaMovimentoEstoque(dataInicio, dataFinal, validaEmpresaFilialId, empresaFilialId, validaLocalEstoque, localEstoqueId,
				validaGrupoContabil, grupoContabilId, validaItem, itemId, validaOperacaoInterna, operacaoInternaId, validaDocumento, documento);
		return tuples.stream().map(tuple -> {
			MovimentoEstoqueResponse saldo = new MovimentoEstoqueResponse();
			saldo.setEmpresaFilialId(tuple.get("empresaFilialId", Long.class));
			saldo.setLocalEstoqueId(tuple.get("localEstoqueId", Long.class));
			saldo.setGrupoContabilId(tuple.get("grupoContabilId", Long.class));
			saldo.setItemId(tuple.get("itemId", Long.class));
			saldo.setLocalEstoqueCodigo(tuple.get("localEstoqueCodigo", Integer.class));
			saldo.setGrupoContabilCodigo(tuple.get("grupoContabilCodigo", Integer.class));
			saldo.setItemCodigo(tuple.get("itemCodigo", Integer.class));
			saldo.setOperacaoInternaId(tuple.get("operacaoInternaId", Long.class));
			saldo.setEmpresaFilialNome(tuple.get("empresaFilialNome", String.class));
			saldo.setLocalEstoqueNome(tuple.get("localEstoqueNome", String.class));
			saldo.setGrupoContabilNome(tuple.get("grupoContabilNome", String.class));
			saldo.setItemNome(tuple.get("itemNome", String.class));
			saldo.setUnidadeMedidaSigla(tuple.get("unidadeMedidaSigla", String.class));
			saldo.setOperacaoInternaNome(tuple.get("operacaoInternaNome", String.class));
			saldo.setOperacaoInternaSigla(tuple.get("operacaoInternaSigla", String.class));
			String data = tuple.get("data", Date.class).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(data, formatter);
			saldo.setData(localDate);
			saldo.setDocumento(tuple.get("documento", String.class));
			saldo.setEntrada(tuple.get("entrada", Boolean.class));
			saldo.setTipoMovimentoEstoque(tuple.get("tipoMovto", String.class));
			saldo.setCreditoDebitoQuantidade(tuple.get("credDebQtde", Character.class).toString());
			saldo.setQuantidade(tuple.get("quantidade", BigDecimal.class));
			saldo.setValor(tuple.get("valor", BigDecimal.class));
			saldo.setCreditoDebitoValor(tuple.get("credDebValor", Character.class).toString());
			saldo.setCustoMedio(tuple.get("custoMedio", BigDecimal.class));
			return saldo;
		}).collect(Collectors.toList());
	}
	
}
