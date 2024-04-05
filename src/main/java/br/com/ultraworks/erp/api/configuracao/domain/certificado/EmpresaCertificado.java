package br.com.ultraworks.erp.api.configuracao.domain.certificado;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificadoConverter;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa_certificado")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class EmpresaCertificado extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "empresaCertificadoSeq", sequenceName = "seq_empresa_certificado", allocationSize = 1)
	@GeneratedValue(generator = "empresaCertificadoSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@Column(name = "certificado")
	private byte[] arquivoPfx;
	
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	
	private String senha;
	
	@Convert(converter = TipoCertificadoConverter.class)
	@Column(name = "tipo_certificado")
	private TipoCertificado tipoCertificado;
	
	
}
