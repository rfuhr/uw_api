package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro;

import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import lombok.Data;

@Data
public class CotacaoMercadoriaParceiroDTO {

	private Long id;
	private Long cotacaoMercadoriaId;
	private Long parceiroLocalId;
	private List<CotacaoMercadoriaItemDTO> itens;

	private String parceiroLocalNome;
	private Long parceiroId;
	private String parceiroNomeRazaoSocial;
}
