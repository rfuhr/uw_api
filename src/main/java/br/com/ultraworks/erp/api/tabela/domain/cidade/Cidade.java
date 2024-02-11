package br.com.ultraworks.erp.api.tabela.domain.cidade;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cidade")
@Data
@EqualsAndHashCode(callSuper=false)
public class Cidade extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "cidadeSeq", sequenceName = "seq_cidade", allocationSize = 1)
	@GeneratedValue(generator = "cidadeSeq")
	private Long id;

	@Column(name = "codigo_ibge")
	private Long codigoIBGE;
	
	private String nome;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private Boolean capital;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais_id")
	private Pais pais;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uf_id")
	private Uf uf;
}
