package br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricolaConverter;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDCConverter;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_calculo_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({ @UniqueValidation(fields = { "nome" }, label = "Já existe um Tipo de Cálculo com este nome"),
		@UniqueValidation(fields = { "codigo" }, label = "Já existe um Tipo de Cálculo com este código") })
public class TipoCalculoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "tipoCalculoAgricolaSeq", sequenceName = "seq_tipo_calculo_agricola", allocationSize = 1)
	@GeneratedValue(generator = "tipoCalculoAgricolaSeq")
	private Long id;

	@Convert(converter = BaseCalculoAgricolaConverter.class)
	@Column(name = "base_calculo_agricola")
	private BaseCalculoAgricola baseCalculoAgricola;

	private Long codigo;
	private String nome;

	@Convert(converter = IndicadorDCConverter.class)
	@Column(name = "indicador_dc")
	private IndicadorDC indicadorDC;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
