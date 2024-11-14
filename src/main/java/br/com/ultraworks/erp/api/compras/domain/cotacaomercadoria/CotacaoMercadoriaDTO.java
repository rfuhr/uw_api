package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiroDTO;
import lombok.Data;

@Data
public class CotacaoMercadoriaDTO {

	private Long id;
	private Long numero;
	private Long departamentoCotacaoId;
	private LocalDate dataCotacao;
	private String situacaoCotacaoMercadoria;

	private String departamentoCotacaoNome;
	private String situacaoCotacaoMercadoriaNome;

	private List<CotacaoMercadoriaParceiroDTO> parceiros;
}
