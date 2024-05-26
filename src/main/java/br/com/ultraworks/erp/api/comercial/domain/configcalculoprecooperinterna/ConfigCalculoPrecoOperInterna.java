package br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoqueConverter;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@Table(name = "config_calculo_preco_oper_interna")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe uma Operação Interna para a Configuração de Preço com esse Identificador")
})
public class ConfigCalculoPrecoOperInterna extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configCalculoPrecoOperInternaSeq", sequenceName = "seq_config_calculo_preco_oper_interna", allocationSize = 1)
	@GeneratedValue(generator = "configCalculoPrecoOperInternaSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "config_calculo_preco_id")
	private ConfigCalculoPreco configCalculoPreco;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@Convert(converter = OperacaoEstoqueConverter.class)
	@Column(name = "operacao_estoque")
	private OperacaoEstoque operacaoEstoque;
}
