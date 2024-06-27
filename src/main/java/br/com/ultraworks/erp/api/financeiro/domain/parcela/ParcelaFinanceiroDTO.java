package br.com.ultraworks.erp.api.financeiro.domain.parcela;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiroDTO;
import lombok.Data;

@Data
public class ParcelaFinanceiroDTO {

	private Long id;
	private Long tituloId;
	private int numParcela;
	private int seqParcela;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
	private int ultimaSeqMvto;
	private BigDecimal saldoParcela;
	
	private List<MovimentoFinanceiroDTO> movimentos;
}
