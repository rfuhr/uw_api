package br.com.ultraworks.erp.api.fiscal.domain.documentoItem;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documento_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class DocumentoItem extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "documentoItemSeq", sequenceName = "seq_documento_item", allocationSize = 1)
	@GeneratedValue(generator = "documentoItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "documento_id")
	@JsonBackReference
	private Documento documento;
	
	@Column(name = "sequencia")
	private int sequencia;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Column(name = "quantidade")
	private BigDecimal quantidade;
	
	@Column(name = "valor_unitario")
	private BigDecimal ValorUnitario;
	
	@Column(name = "valor_bruto")
	private BigDecimal valorBruto;

	@Column(name = "perc_desconto")
	private BigDecimal percentualDesconto;
	
	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;
	
	@Column(name = "valor_frete")
	private BigDecimal valorFrete;
	
	@Column(name = "valor_seguro")
	private BigDecimal valorSeguro;
	
	@Column(name = "valor_outrasdesp")
	private BigDecimal valorOutrasDespesas;
	
	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;

}
