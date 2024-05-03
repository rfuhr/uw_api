package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VolumeNFeRequest {

	private BigDecimal quantidade;
	private String especie;
	private String marca;
	private String numeracao;
	private BigDecimal pesoLiquido;
	private BigDecimal pesoBruto;

	private boolean informarLacres;

	private List<String> lacres;
}
