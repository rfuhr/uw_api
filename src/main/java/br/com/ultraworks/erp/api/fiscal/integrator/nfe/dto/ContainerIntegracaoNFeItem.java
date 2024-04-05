package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFeItem {

	private Item item;
	private String cfop;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private BigDecimal quantidadeTributavel;
	private BigDecimal valorUnitarioTributavel;
	private BigDecimal valorTotalFrete;
	private BigDecimal valorTotalSeguro;
	private BigDecimal valorDesconto;
	private BigDecimal valorOutrasDespesas;
	private boolean valorItemCompoeValorTotalNFe;
}
