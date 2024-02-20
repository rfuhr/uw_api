package br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributoConverter;
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
@Table(name = "situacao_tributaria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class SituacaoTributaria extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "situacaoTributariaSeq", sequenceName = "seq_situacao_tributaria", allocationSize = 1)
	@GeneratedValue(generator = "situacaoTributariaSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
	@Convert(converter = TipoTributoConverter.class)
	@Column(name = "tipo_tributo")
	private TipoTributo tipoTributo;
	
	@Column(name = "simples_nacional")
	private boolean simplesNacional;
	
	@Column(name = "aliquota_zero")
	private boolean aliquotaZero;
	
	@Column(name = "requer_mensagem_fiscal")
	private boolean requerMensagemFiscal;
	
	@Column(name = "destaca_st_saida")
	private boolean destacaStSaida;
	
	@Column(name = "exige_aliquota_desonerada")
	private boolean exigeAliquotaDesonerada;
	
	@Column(name = "controla_imposto_retido")
	private boolean controlaImpostoRetido;
	
	@Column(name = "excluir_icms_base_calculo")
	private boolean excluirIcmsBaseCalculo;
	
	@Column(name = "excluir_icms_bc_piscofins")
	private boolean excluirIcmsBcPiscofins;
	
	@Column(name = "nao_excluir_icms_entrada")
	private boolean naoExcluirIcmsEntrada;
	
}
