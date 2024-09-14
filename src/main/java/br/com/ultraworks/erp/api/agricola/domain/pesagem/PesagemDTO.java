package br.com.ultraworks.erp.api.agricola.domain.pesagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacaoDTO;
import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.validator.ValidaSituacaoPesagem;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PesagemDTO {

	private Long id;

	@NotNull
	private Long empresaFilialId;
	@NotNull
	private LocalDate dataPesagem;
	@NotNull
	private Long operacaoInternaId;
	@NotNull
	private boolean temCadastro;
	private Long parceiroLocalId;
	private Long parceiroLocalPropriedadeId;
	private String nomeParceiro;
	private String nomePropriedade;
	@NotNull
	private Long itemId;
	@NotNull
	private Long safraId;
	@NotNull
	private BigDecimal pesoEntrada;
	@NotNull
	private BigDecimal pesoSaida;
	@NotNull
	private BigDecimal pesoBruto;
	@NotNull
	private BigDecimal descontos;
	@NotNull
	private BigDecimal pesoLiquido;
	@NotNull
	private String placa1;
	private String placa2;
	private String placa3;
	@NotNull
	private String nomeMotorista;
	@NotNull
	private String contatoMotorista;
	private String observacao;
	private boolean pesoEntradaManual;
	private String justificativaPesoEntrada;
	private boolean pesoSaidaManual;
	private String justificativaPesoSaida;
	@ValidaSituacaoPesagem
	private String situacao;

	private String empresaFilialNome;
	private String operacaoInternaNome;
	private String parceiroLocalNome;
	private String parceiroNome;
	private String parceiroLocalPropriedadeNome;
	private String itemNome;
	private String safraNome;
	private String situacaoNome;

	private List<PesagemClassificacaoDTO> classificacoes;
}
