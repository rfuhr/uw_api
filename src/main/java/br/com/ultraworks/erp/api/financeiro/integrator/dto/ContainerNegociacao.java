package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContainerNegociacao {

	private Departamento departamento;
	private ParceiroLocal parceiroLocal;
	private BigDecimal valorTotalAtraso;
	private BigDecimal valorJurosNegociado;
	private BigDecimal valorDescontoNegociado;
	private BigDecimal valorNegociadoPagar;
	private LocalDate dataNegociacao;
	private String observacao;
	private int quantidadeParcelas;
}
