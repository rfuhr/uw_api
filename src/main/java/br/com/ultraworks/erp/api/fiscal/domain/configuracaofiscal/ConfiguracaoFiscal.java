package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.entradasaida.EntradaSaida;
import br.com.ultraworks.erp.api.fiscal.domain.entradasaida.EntradaSaidaConverter;
import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuracao_fiscal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
//@UniqueValidationGroup({
//    @UniqueValidation(fields = {"uf_origem_id","uf_destino_id","entrada_saida","data_inicio_vigencia", "data_final_vigencia", "grupo_tributacao_id",
//    		                    "cfop_id","ncm_id","regime_tributario_id","origem_id","operacao_interna_id","classificacao_operacao_id", "item_id"},
//    					label = "Já existe Configuração Fiscal com esta chave")
//})
public class ConfiguracaoFiscal extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "configuracaoFiscalSeq", sequenceName = "seq_configuracao_fiscal", allocationSize = 1)
	@GeneratedValue(generator = "configuracaoFiscalSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "uf_origem_id")
	private Uf ufOrigem;
	
	@OneToOne
	@JoinColumn(name = "pais_destino_id")
	private Pais paisDestino;
	
	@OneToOne
	@JoinColumn(name = "uf_destino_id")
	private Uf ufDestino;
	
	@Convert(converter = EntradaSaidaConverter.class)
	@Column(name = "entrada_saida")
	private EntradaSaida entradaSaida;
	
	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "data_final_vigencia")
	private LocalDate dataFinalVigencia;
	
	@OneToOne
	@JoinColumn(name = "grupo_tributacao_id")
	private GrupoTributacao grupoTributacao;
	
	@OneToOne
	@JoinColumn(name = "cfop_id")
	private Cfop cfop;
	
	@OneToOne
	@JoinColumn(name = "ncm_id")
	private Ncm ncm;
	
	@OneToOne
	@JoinColumn(name = "regime_tributario_id")
	private RegimeTributario regimeTributario;
	
	@OneToOne
	@JoinColumn(name = "origem_id")
	private Origem origem;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;
	
	@OneToOne
	@JoinColumn(name = "classificacao_operacao_id")
	private ClassificacaoOperacao classificacaoOperacao;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Transient
	private ConfiguracaoFiscalIcms configuracaoFiscalIcms;
	
	@Transient
	private ConfiguracaoFiscalIpi configuracaoFiscalIpi;
	
	@Transient
	private ConfiguracaoFiscalPis configuracaoFiscalPis;
	
	@Transient
	private ConfiguracaoFiscalCofins configuracaoFiscalCofins;
}
