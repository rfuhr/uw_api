package br.com.ultraworks.erp.api.estoque.domain.localestoque;

import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoque;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
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
@Table(name = "local_estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Local do Estoque com este nome")
})
public class LocalEstoque extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "localEstoqueSeq", sequenceName = "seq_local_estoque", allocationSize = 1)
	@GeneratedValue(generator = "localEstoqueSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
	@Column(name = "filial_especifica")
	private boolean filialEspecifica;
	
	@OneToOne
	@JoinColumn(name = "tipo_local_estoque_id")
	private TipoLocalEstoque tipoLocalEstoque;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;
	
}
