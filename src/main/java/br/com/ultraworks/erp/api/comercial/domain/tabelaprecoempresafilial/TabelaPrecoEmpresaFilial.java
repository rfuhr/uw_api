package br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "tabela_preco_empresa_filial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Tabela de Preço da Filial com esse Identificador")
})
public class TabelaPrecoEmpresaFilial extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tabelaPrecoEmpresaFilialSeq", sequenceName = "seq_tabela_preco_empresa_filial", allocationSize = 1)
	@GeneratedValue(generator = "tabelaPrecoEmpresaFilialSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "tabela_preco_id")
	private TabelaPreco tabelaPreco;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

}
