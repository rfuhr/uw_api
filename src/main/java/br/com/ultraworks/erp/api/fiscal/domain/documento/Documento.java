package br.com.ultraworks.erp.api.fiscal.domain.documento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.fiscal.domain.documentoItem.DocumentoItem;
import br.com.ultraworks.erp.api.fiscal.domain.documentointegracao.DocumentoIntegracao;
import br.com.ultraworks.erp.api.fiscal.domain.documentoparcela.DocumentoParcela;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.origemdocumento.OrigemDocumento;
import br.com.ultraworks.erp.api.tabela.domain.origemdocumento.OrigemDocumentoConverter;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumentoConverter;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Documento extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "documentoSeq", sequenceName = "seq_documento", allocationSize = 1)
	@GeneratedValue(generator = "documentoSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	@Column(name = "numero")
	private Long numero;

	@Column(name = "data_documento")
	private LocalDate dataDocumento;
	
	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;
	
	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@OneToOne
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;	
	
	@Convert(converter = OrigemDocumentoConverter.class)
	@Column(name = "origem_documento")
	private OrigemDocumento origemDocumento;	
	
	@OneToOne
	@JoinColumn(name = "documento_origem_id")
	@JsonBackReference
	private Documento documentoOrigemId;
	
	@Column(name = "cancelado")
	private boolean cancelado;
	
	@Column(name = "valor")
	private BigDecimal valor;

	@OneToOne
	@JoinColumn(name = "nfe_id")
	@JsonBackReference
	private NFe nfe;
	
	@Convert(converter = SituacaoDocumentoConverter.class)
	@Column(name = "situacao")
	private SituacaoDocumento situacaoDocumento;
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoItem> itens;
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoParcela> parcelas;
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoIntegracao> integracoes;

}
