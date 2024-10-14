package br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "valida_operacao_interna_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidaOperacaoInternaAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "validaOperacaoInternaAgricolaSeq", sequenceName = "seq_valida_operacao_interna_agricola", allocationSize = 1)
	@GeneratedValue(generator = "validaOperacaoInternaAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "grupo_operacao_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private GrupoOperacaoAgricola grupoOperacaoAgricola;

	@OneToOne
	@JoinColumn(name = "fato_gerador_contrato_id")
	private FatoGerador fatoGeradorContrato;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
