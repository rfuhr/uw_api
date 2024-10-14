package br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
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
@Table(name = "valida_preco_agricola_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidaPrecoAgricolaItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "validaPrecoAgricolaItemSeq", sequenceName = "seq_valida_preco_agricola_item", allocationSize = 1)
	@GeneratedValue(generator = "validaPrecoAgricolaItemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;
	
	@OneToOne
	@JoinColumn(name = "predefinicao_preco_agricola_id")
	private PredefinicaoPrecoAgricola predefinicaoPrecoAgricola;
	

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
