package br.com.ultraworks.erp.api.comercial.domain.calculoprecos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Precos {
	
	private Long tipoPrecoId;
	private Long itemId;
	private Long configCalculoPrecoId;
	private BigDecimal valorPrecoBase;
	private BigDecimal valorMarkup;
	private BigDecimal valorCalculado;
	private BigDecimal valorAtual;

}
