package br.com.ultraworks.erp.api.fiscal.domain.nfe.response;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListaNFeResponse {

	private Long nfeId;
	private Instant dataHoraEmissao;
	private String chaveNFe;
	private int serie;
	private int numero;
	private String situacao;
	private String cpfCnpj;
	private String nome;
	private String siglaOperacaoInterna;
	private BigDecimal vnf;
	private String situacaoIntegracao;
	private String tipoNfe;
}
