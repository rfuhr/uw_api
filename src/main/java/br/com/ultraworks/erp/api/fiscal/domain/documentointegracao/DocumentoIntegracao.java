package br.com.ultraworks.erp.api.fiscal.domain.documentointegracao;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.TipoIntegracao;
import br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.TipoIntegracaoConverter;
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
@Table(name = "documento_integracao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class DocumentoIntegracao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "documentoIntegracaoSeq", sequenceName = "seq_documento_integracao", allocationSize = 1)
	@GeneratedValue(generator = "documentoIntegracaoSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "documento_id")
	@JsonBackReference
	private Documento documento;
	
	@Convert(converter = TipoIntegracaoConverter.class)
	@Column(name = "tipo_integracao")
	private TipoIntegracao tipoIntegracao;
	
	private boolean integrado = false;
	
	private boolean cancelamento = false;
	
	@Column(name = "data_integracao")
	@UpdateTimestamp
	private LocalDateTime dataIntegracao;
	
	@Column(name = "protocolo_destino")
	private Long protocoloDestino;
	
	@Column(name = "pilha_erro")
	private byte[] pilhaErro;

}
