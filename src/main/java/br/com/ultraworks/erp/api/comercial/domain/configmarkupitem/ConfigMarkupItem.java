package br.com.ultraworks.erp.api.comercial.domain.configmarkupitem;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_markup_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Mark Up do Item com esse Identificador")
})
public class ConfigMarkupItem extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configMarkupItemSeq", sequenceName = "seq_config_markup_item", allocationSize = 1)
	@GeneratedValue(generator = "configMarkupItemSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Transient
	private List<ConfigMarkupItemIndice> configMarkupItemIndices;

}
