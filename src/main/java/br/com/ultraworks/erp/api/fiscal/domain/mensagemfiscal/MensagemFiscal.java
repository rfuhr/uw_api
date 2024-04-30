package br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal;

import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
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
@Table(name = "mensagem_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe Mensagem Fiscal com esse código")
})
public class MensagemFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "mensagemFiscalSeq", sequenceName = "seq_mensagem_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "mensagemFiscalSeq")
	private Long id;

	private int codigo;
	
	@Column(name = "codigo_ajuste")
	private String codigoAjuste;
	
	private String nome;
	
	private String sigla;
	
	@Column(name = "obs_fiscal")
	private String obsFiscal;
	
	@OneToOne
	@JoinColumn(name = "uf_id")
	private Uf uf;

}
