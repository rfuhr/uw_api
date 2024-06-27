package br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
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
@Table(name = "config_markup_plano_item_indice")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração do Índice de Mark Up do Plano de Item com esse Identificador")
})
public class ConfigMarkupPlanoItemIndice extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMarkupPlanoItemIndiceSeq", sequenceName = "seq_config_markup_plano_item_indice", allocationSize = 1)
	@GeneratedValue(generator = "configMarkupPlanoItemIndiceSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_markup_plano_item_id")
	private ConfigMarkupPlanoItem configMarkupPlanoItem;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@OneToOne
	@JoinColumn(name = "indice_markup_id")
	private IndiceMarkup indiceMarkup;

	private BigDecimal percentual;
}
