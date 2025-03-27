package br.com.ultraworks.erp.api.fiscal.domain.nfe.entity;

import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.tiponfe.TipoNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tiponfe.TipoNfeConverter;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumentoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFe extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeSeq", sequenceName = "seq_nfe", allocationSize = 1)
	@GeneratedValue(generator = "nfeSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	@Column(name = "chave_nfe")
	private String chaveNfe;

	private String cstat;
	private String crejeicao;
	private String cdenegado;
	private String nprotnfe;
	private String nrecibo;
	private String xmotivo;
	private byte[] xml;

	@Convert(converter = SituacaoDocumentoConverter.class)
	@Column(name = "situacao")
	private SituacaoDocumento situacao;
	
	@Convert(converter = TipoNfeConverter.class)
	@Column(name = "tipo_nfe")
	private TipoNfe tipoNfe;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeIde nfeIde;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeEmit nfeEmit;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeDest nfeDest;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeRetirada nfeRetirada;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeEntrega nfeEntrega;

	@OneToMany(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<NFeAut> autorizacoes;

	@OneToMany(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NFeDetItem> itens;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeTotais nfeTotais;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeTransporte nfeTransporte;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFePagamento nfePagamento;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
	private NFeInfoAdic nfeInfoAdic;

}
