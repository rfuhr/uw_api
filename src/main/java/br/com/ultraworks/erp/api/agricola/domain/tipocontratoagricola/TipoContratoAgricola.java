package br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricolaConverter;
import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneioConverter;
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
@Table(name = "tipo_contrato_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({
		@UniqueValidation(fields = { "codigo" }, label = "Já existe tipo de contrato agrícola com este código"),
		@UniqueValidation(fields = { "nome" }, label = "Já existe tipo de contrato agrícola com este nome"),
		@UniqueValidation(fields = { "sigla" }, label = "Já existe tipo de contrato agrícola com esta sigla") })
public class TipoContratoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "tipoContratoAgricolaSeq", sequenceName = "seq_tipo_contrato_agricola", allocationSize = 1)
	@GeneratedValue(generator = "tipoContratoAgricolaSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;
	
	@Convert(converter = CaracteristicaContratoAgricolaConverter.class)
	@Column(name = "caracteristica_contrato_agricola")
	private CaracteristicaContratoAgricola caracteristicaContratoAgricola;


	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
}
