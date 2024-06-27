package br.com.ultraworks.erp.api.financeiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueResponse;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoResponse;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.Tuple;

@Repository
public interface SaldoCaixaBancoRepository extends UWRepository<SaldoCaixaBanco, Long> {
	
	@Query(value = "SELECT public.atualiza_saldo_caixa_banco(:dataInicio, :dataFim, :contaId, :userId)", nativeQuery = true)
	void atualizaSaldoCaixaBanco(String dataInicio, String dataFim, Long contaId, Long userId);

	@Query(value = " select b.id as bancoId, b.nome as bancoNome, a.id as agenciaId, a.nome as agenciaNome, c.id as contaId, c.nome as contaNome, c.numero as contaNumero, " 
			+ "       s.data, s.saldo_valor as saldoValor"
			+ " from saldo_cxbco s "
			+ "  inner join conta c on c.id = s.conta_id "
			+ "  inner join agencia a on a.id = c.agencia_id "
			+ "  inner join banco b on b.id = a.banco_id "
			+ " where s.data between TO_DATE(:dataInicio, 'YYYY-MM-DD') and TO_DATE(:dataFinal, 'YYYY-MM-DD') "
			+ " and (1 = :validaBanco or b.id = :bancoId) "
			+ " and (1 = :validaAgencia or a.id = :agenciaId) "
			+ " and (1 = :validaConta or s.conta_id = :contaId) "
			+ " order by b.nome, a.nome, c.numero, s.data desc", nativeQuery = true)
	List<Tuple> buscaSaldoCaixaBanco(String dataInicio, String dataFinal, int validaBanco, Long bancoId, int validaAgencia, Long agenciaId,
			int validaConta, Long contaId);
	
	default List<SaldoCaixaBancoResponse> buscaSaldoCaixaBancoResponse(String dataInicio, String dataFinal, int validaBanco, Long bancoId, int validaAgencia, Long agenciaId,
			int validaConta, Long contaId) {
		List<Tuple> tuples = buscaSaldoCaixaBanco(dataInicio, dataFinal, validaBanco, bancoId, validaAgencia, agenciaId,
				validaConta, contaId);
		
		return tuples.stream().map(tuple -> {
			SaldoCaixaBancoResponse saldo = new SaldoCaixaBancoResponse();
			saldo.setBancoId(tuple.get("bancoId", Long.class));
			saldo.setAgenciaId(tuple.get("agenciaId", Long.class));
			saldo.setContaId(tuple.get("contaId", Long.class));
			saldo.setBancoNome(tuple.get("bancoNome", String.class));
			saldo.setAgenciaNome(tuple.get("agenciaNome", String.class));
			saldo.setContaNome(tuple.get("contaNome", String.class));
			saldo.setContaNumero(tuple.get("contaNumero", String.class));
			String data = tuple.get("data", Date.class).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(data, formatter);
			saldo.setData(localDate);
			saldo.setSaldoValor(tuple.get("saldoValor", BigDecimal.class));
			return saldo;
		}).collect(Collectors.toList());
	}
}
