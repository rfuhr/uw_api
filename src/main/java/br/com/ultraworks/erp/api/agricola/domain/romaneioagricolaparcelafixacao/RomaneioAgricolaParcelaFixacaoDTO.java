package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RomaneioAgricolaParcelaFixacaoDTO {

	private Long id;
	private Long romaneioAgricolaId;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
}
