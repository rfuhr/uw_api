package br.com.ultraworks.erp.api.estoque.domain.item;

import java.math.BigDecimal;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDTO {

	private Long id;
	@NotNull
	@FriendlyName("Código")
	private int codigo;
	@NotNull
	@NotEmpty
	@Size(max = 120)
	@FriendlyName("Nome")
	private String nome;

	@Size(max = 50)
	@FriendlyName("SKU")
	private String sku;

	@Size(max = 500)
	@FriendlyName("Descritivo")
	private String descritivo;

	@NotNull
	@FriendlyName("Unidade Medida Comercial")
	private Long unidadeMedidaComercialId;
	private String unidadeMedidaComercialNome;

	private Long marcaId;
	private Long linhaId;
	private Long planoCassificacaoItemId;
	private String gtinEan;
	@FriendlyName("Produto Próprio")
	private boolean produtoProprio;

	private boolean fracionado;
	private boolean controlaEstoque;
	private Long unidadeMedidaEstoqueId;
	private BigDecimal quantidadeMinimaEstoque;
	private BigDecimal quantidadeMaximaEstoque;
	private BigDecimal quantidadeIdealEstoque;
	private BigDecimal quantidadeAlertaEstoque;

	private Long origemId;
	private Long ncmId;
	private String ncmCodigo;
	private Long unidadeMedidaTributavelId;
}
