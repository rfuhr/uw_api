package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RomaneioAgricolaNotaDTO {

	private Long id;
	private Long romaneioAgricolaId;
	private Long operacaoInternaId;
	private Long cfopId;
	private int numeroNota;
	private int serie;
	private LocalDate dataEmissao;
	private String chaveNFe;
	private Long tipoDocumentoId;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;

	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	private int cfopCodigo;
	private String cfopNome;
	private String tipoDocumentoNome;
	private String tipoDocumentoSigla;
}
