package br.com.ultraworks.erp.api.comercial.domain.configcalculopreco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "config_calculo_preco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"id"}, label = "Já existe Configuração de Cálculo de Preço com esse Identificador")
})
public class ConfigCalculoPreco extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configCalculoPrecoSeq", sequenceName = "seq_config_calculo_preco", allocationSize = 1)
	@GeneratedValue(generator = "configCalculoPrecoSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "tipo_preco_id")
	private TipoPreco tipoPreco;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;
	
	@Column(name = "aplica_indices_markup")
	private boolean aplicaIndicesMarkup;
	
	@Column(name = "aplica_percentual_fixo")
	private boolean aplicaPercentualFixo;
	
	@Column(name = "dias_busca_precos")
	private int diasBuscaPrecos;
	
	private BigDecimal percentual;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@Transient
	private List<ConfigCalculoPrecoOperInterna> configCalculoPrecoOperInternas;

}
