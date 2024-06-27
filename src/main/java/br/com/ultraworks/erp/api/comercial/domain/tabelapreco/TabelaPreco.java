package br.com.ultraworks.erp.api.comercial.domain.tabelapreco;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilial;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tabela_preco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe Tabela Preço com esse Código"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe Tabela Preço com esse Nome")
})
public class TabelaPreco extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tabelaPrecoSeq", sequenceName = "seq_tabela_preco", allocationSize = 1)
	@GeneratedValue(generator = "tabelaPrecoSeq")
	private Long id;
	
	private int codigo;
	
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;
	
	@OneToOne
	@JoinColumn(name = "tipo_preco_id")
	private TipoPreco tipoPreco;
	
	@OneToOne
	@JoinColumn(name = "grupo_contabil_id")
	private GrupoContabil grupoContabil;
	
	private boolean promocional;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Transient
	private List<TabelaPrecoEmpresaFilial> tabelaPrecoEmpresaFiliais;
	
	@Transient
	private List<TabelaPrecoItem> tabelaPrecoItens;

}
