package br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "tipo_incentivo_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe Tipo Incentivo Fiscal com esse código")
})
public class TipoIncentivoFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoIncentivoFiscalSeq", sequenceName = "seq_tipo_incentivo_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "tipoIncentivoFiscalSeq")
	private Long id;

	private int codigo;
	
	private String nome;

}
