package br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricolaConverter;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "ordem_calculo_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdemCalculoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "ordemCalculoAgricolaSeq", sequenceName = "seq_ordem_calculo_agricola", allocationSize = 1)
	@GeneratedValue(generator = "ordemCalculoAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@Convert(converter = IdentificacaoDocumentoAgricolaConverter.class)
	@Column(name = "identificacao_documento_agricola")
	private IdentificacaoDocumentoAgricola identificacaoDocumentoAgricola;

	@OneToOne
	@JoinColumn(name = "tipo_calculo_agricola_id")
	private TipoCalculoAgricola tipoCalculoAgricola;

	@Column(name = "ordem")
	private int ordem;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
