package br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoriaConverter;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
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
@Table(name = "solicitacao_mercadoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SolicitacaoMercadoria extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "solicitacaoMercadoriaSeq", sequenceName = "seq_solicitacao_mercadoria", allocationSize = 1)
	@GeneratedValue(generator = "solicitacaoMercadoriaSeq")
	private Long id;

	@Column(name = "numero")
	private Long numero;

	@OneToOne
	@JoinColumn(name = "depto_solicitante_id")
	private Departamento departamentoSolicitante;

	@OneToOne
	@JoinColumn(name = "grupo_contabil_dest_id")
	private GrupoContabil grupoContabilDestino;

	@OneToOne
	@JoinColumn(name = "depto_solicitado_id")
	private Departamento departamentoSolicitado;

	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;

	@Column(name = "observacao")
	private String observacao;

	@Convert(converter = SituacaoSolicitacaoMercadoriaConverter.class)
	@Column(name = "situacao_solicitacao_mercadoria")
	private SituacaoSolicitacaoMercadoria situacaoSolicitacaoMercadoria;

	@Transient
	private List<SolicitacaoMercadoriaItem> itens;
}
