package br.com.ultraworks.erp.api.financeiro.domain.parcela;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class ParcelaFinanceiroDTO {

	private Long id;
	private Long tituloId;
	private int numParcela;
	private int seqParcela;
	private Date dataVencimento;
	private BigDecimal valorParcela;
	private int ultimaSeqMvto;
}
