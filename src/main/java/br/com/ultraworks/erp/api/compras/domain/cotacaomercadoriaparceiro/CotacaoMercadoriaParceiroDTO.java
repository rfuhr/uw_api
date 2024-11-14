package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro;

import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import lombok.Data;

@Data
public class CotacaoMercadoriaParceiroDTO {

	private Long id;
	private Long cotacaoMercadoriaId;
	private Long parceiroLocalId;
	private String situacao;
	private List<CotacaoMercadoriaItemDTO> itens;

	private ParceiroLocalDTO parceiroLocal;
	private String situacaoNome;
}
