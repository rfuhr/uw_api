package br.com.ultraworks.erp.api.estoque.domain.saldoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saldo_estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SaldoEstoque extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "saldoEstoqueSeq", sequenceName = "seq_saldo_estoque", allocationSize = 1)
	@GeneratedValue(generator = "saldoEstoqueSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "empresa_filial_id")
	@JsonBackReference
	private EmpresaFilial empresaFilial;
	
	@ManyToOne
	@JoinColumn(name = "local_estoque_id")
	@JsonBackReference
	private LocalEstoque localEstoque;
	
	@ManyToOne
	@JoinColumn(name = "grupo_contabil_id")
	@JsonBackReference
	private GrupoContabil grupoContabil;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;
	
	private LocalDate data;
	
	@Column(name = "saldo_quantidade")
	private BigDecimal saldoQuantidade;

	@Column(name = "saldo_valor")
	private BigDecimal saldoValor;
	
	@Column(name = "custo_medio")
	private BigDecimal custoMedio;
	
}
