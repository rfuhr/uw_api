package br.com.ultraworks.erp.api.agricola.domain.precoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
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
@Table(name = "preco_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PrecoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "precoAgricolaSeq", sequenceName = "seq_preco_agricola", allocationSize = 1)
	@GeneratedValue(generator = "precoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "predefinicao_preco_agricola_id")
	private PredefinicaoPrecoAgricola predefinicaoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "cod_nivel_class1")
	private SubItemClassificacaoAgricola nivelClass1;

	@OneToOne
	@JoinColumn(name = "cod_nivel_class2")
	private SubItemClassificacaoAgricola nivelClass2;

	@OneToOne
	@JoinColumn(name = "cod_nivel_class3")
	private SubItemClassificacaoAgricola nivelClass3;

	@OneToOne
	@JoinColumn(name = "cod_nivel_class4")
	private SubItemClassificacaoAgricola nivelClass4;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;

}
