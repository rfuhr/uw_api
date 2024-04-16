package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica;

import java.sql.Date;

import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatario;
import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatarioConverter;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoaConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_local_juridica")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParceiroLocalJuridica extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroJuridicaSeq", sequenceName = "seq_parceiro_juridica", allocationSize = 1)
	@GeneratedValue(generator = "parceiroJuridicaSeq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;
	
	@Column(name = "data_fundacao")
	private Date dataFundacao;
	
	@Convert(converter = IndicadorIEDestinatarioConverter.class)
	@Column(name = "indicador_ie")
	private IndicadorIEDestinatario indicadorIE;
	
	@Column(name = "suframa")
	private String suframa;
}
