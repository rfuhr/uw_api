package br.com.ultraworks.erp.api.tabela.domain.tipoparceiro;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "tipo_parceiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoParceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoParceiroSeq", sequenceName = "seq_tipo_parceiro", allocationSize = 1)
	@GeneratedValue(generator = "tipoParceiroSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
	
	@Column(name = "is_cliente")
	private Boolean isCliente;
	
	@Column(name = "is_empresa")
	private Boolean isEmpresa;
	
	@Column(name = "is_produtor_rural")
	private Boolean isProdutorRural;
	
	@Column(name = "is_fornecedor")
	private Boolean isFornecedor;
}
