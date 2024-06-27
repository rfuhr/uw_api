package br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "tabela_preco_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já  Item da Tabela de Preço com esse Identificador")
})
public class TabelaPrecoItem extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tabelaPrecoItemSeq", sequenceName = "seq_tabela_preco_item", allocationSize = 1)
	@GeneratedValue(generator = "tabelaPrecoItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "tabela_preco_id")
	private TabelaPreco tabelaPreco;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Column(name = "valor_custo")
	private BigDecimal valorCusto;
	
	@Column(name = "valor_markup")
	private BigDecimal valorMarkup;
	
	@Column(name = "valor_calculado")
	private BigDecimal valorCalculado;
	
	@Column(name = "valor_atual")
	private BigDecimal valorAtual;
	
	private BigDecimal valor;
	
	@Column(name = "percentual_maximo_desconto")
	private BigDecimal percentualMaximoDesconto;
		
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;
	
	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
}
