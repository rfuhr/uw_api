package br.com.ultraworks.erp.api.fiscal.domain.nfe;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumentoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.CascadeType;
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
@Table(name = "nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NFe extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "nfeSeq", sequenceName = "nfe_seq", allocationSize = 1)
	@GeneratedValue(generator = "nfeSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	@Column(name = "chave_nfe")
	private String chaveNfe;

	@Convert(converter = SituacaoDocumentoConverter.class)
	@Column(name = "situacao")
	private SituacaoDocumento situacao;

	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
    private NFeIde nfeIde;
	
	@OneToOne(mappedBy = "nfe", cascade = CascadeType.ALL, orphanRemoval = true)
    private NFeEmit nfeEmit;
	
}
