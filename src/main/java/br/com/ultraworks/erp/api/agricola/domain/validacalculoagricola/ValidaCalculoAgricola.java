package br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
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
@Table(name = "valida_calculo_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidaCalculoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "validaCalculoAgricolaSeq", sequenceName = "seq_valida_calculo_agricola", allocationSize = 1)
	@GeneratedValue(generator = "validaCalculoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "tipo_calculo_agricola_id")
	private TipoCalculoAgricola tipoCalculoAgricola;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "grupo_operacao_agricola_id")
	private GrupoOperacaoAgricola grupoOperacaoAgricola;

	@OneToOne
	@JoinColumn(name = "regra_atividade_id")
	private RegraAtividade regraAtividade;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
