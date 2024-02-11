package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro;

import java.sql.Date;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiro;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_local_tipo_parceiro")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroLocalTipoParceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroTipoParceiroSeq", sequenceName = "seq_parceiro_tipo_parceiro", allocationSize = 1)
	@GeneratedValue(generator = "parceiroTipoParceiroSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@OneToOne
	@JoinColumn(name = "tipo_parceiro_id")
	private TipoParceiro tipoParceiro;	
}
