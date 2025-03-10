package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro;

import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamentoConverter;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CotacaoMercadoriaParceiroDTO {

	private Long id;
	private Long cotacaoMercadoriaId;
	private Long parceiroLocalId;
	private String situacao;
	private Integer previsaoDiasEntrega;
	private Long condicaoPagamentoId;
	private String meioPagamento;
	
	private List<CotacaoMercadoriaItemDTO> itens;

	private ParceiroLocalDTO parceiroLocal;
	private String situacaoNome;
	private String condicaoPagamentoNome;
	private String meioPagamentoNome;
}
