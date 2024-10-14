package br.com.ultraworks.erp.api.agricola.domain.romaneioagricola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.validator.ValidaOrigemRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNotaDTO;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacaoDTO;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.validator.ValidaSituacaoRomaneio;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RomaneioAgricolaDTO {

	private Long id;

	@NotNull
	private Long departamentoId;
	@ValidaOrigemRomaneio
	private String origem;
	private Long numero;
	@NotNull
	private LocalDate dataDocumento;
	@NotNull
	private Long parceiroLocalId;
	private Long parceiroLocalPropriedadeId;
	@NotNull
	private Long itemId;
	private Long safraId;
	@NotNull
	private Long regraAtividadeId;
	
	@NotNull
	private Long grupoOperacaoAgricolaId;
	@NotNull
	private Long operacaoInternaId;
	@NotNull
	private Long origemProducaoAgricolaId;
	private Long balancaId;
	@NotNull
	private BigDecimal pesoEntrada;
	@NotNull
	private BigDecimal pesoSaida;
	@NotNull
	private BigDecimal pesoLiquido;
	@NotNull
	private BigDecimal descontosAcrescimo;
	@NotNull
	private BigDecimal pesoFinal;
	private boolean pesoEntradaManual;
	private String justificativaPesoEntrada;
	private boolean pesoSaidaManual;
	private String justificativaPesoSaida;

	private String placa1;
	private String placa2;
	private String placa3;
	private String nomeMotorista;
	private String contatoMotorista;
	private boolean entrada;
	private boolean fixarAutomatico;
	private String observacao;
	@ValidaSituacaoRomaneio
	private String situacao;
	private Long   contratoAgricolaId;
	private Long tipoPrecoAgricolaId;
	private Long nivel1PredPrecoId;
	private Long nivel2PredPrecoId;
	private Long nivel3PredPrecoId;
	private Long nivel4PredPrecoId;
	private BigDecimal precoDeposito;
	private BigDecimal pesoFinalFaturar;
	private BigDecimal saldoFixar;	
	private String controle;
	private int controleDv;
	private int numeroNfDeposito;
	
	private String departamentoNome;
	private String origemNome;
	private String parceiroLocalNome;
	private String parceiroNome;
	private String parceiroLocalPropriedadeNome;
	private String regraAtividadeNome;
	private String itemNome;
	private String safraNome;
	private String grupoOperacaoAgricolaNome;
	private String operacaoInternaNome;
	private String origemProducaoAgricolaNome;
	private String balancaNome;
	private String situacaoNome;

	private List<RomaneioAgricolaClassificacaoDTO> classificacoes;
	private List<RomaneioAgricolaNotaDTO> notas;
	private List<RomaneioAgricolaParcelaFixacaoDTO> parcelas;

}
