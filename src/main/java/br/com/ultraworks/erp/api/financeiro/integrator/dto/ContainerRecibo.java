package br.com.ultraworks.erp.api.financeiro.integrator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerRecibo {

	private int tipoDocumentoId;
	private int movimentoFinanceiroId;
}
