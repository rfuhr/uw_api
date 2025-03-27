package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe.TipoComunicacaoNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe.TipoComunicacaoNfeConverter;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumentoConverter;
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
@Table(name = "nfe_comunicacao_sefaz")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFeComunicacaoSEFAZ extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeComunicacaoSefazSeq", sequenceName = "seq_nfe_comunicacao_sefaz", allocationSize = 1)
	@GeneratedValue(generator = "nfeComunicacaoSefazSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;

	private String cstat;
	private String nprotnfe;
	private String nrecibo;
	private String xmotivo;
	private String status;
	@Column(name = "xml_envio")
	private byte[] xmlEnvio;
	@Column(name = "xml_retorno")
	private byte[] xmlRetorno;
	
	@Convert(converter = TipoComunicacaoNfeConverter.class)
	@Column(name = "tipo_comunicacao")
	private TipoComunicacaoNfe tipoComunicacaoNfe;
	
}
