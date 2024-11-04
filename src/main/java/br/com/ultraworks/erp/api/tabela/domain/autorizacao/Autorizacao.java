package br.com.ultraworks.erp.api.tabela.domain.autorizacao;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacaoConverter;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacaoConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autorizacao")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class Autorizacao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "autorizaaoSeq", sequenceName = "seq_autorizacao", allocationSize = 1)
	@GeneratedValue(generator = "autorizaaoSeq")
	private Long id;

	@Column(name = "documento_origem_id")
	private Long documentoOrigemId;

	@Column(name = "documento_identificacao")
	private String documentoIdentificacao;
	
	@Convert(converter = TipoAutorizacaoConverter.class)
	@Column(name = "tipo_autorizacao")
	private TipoAutorizacao tipoAutorizacao;

	@Convert(converter = StatusAutorizacaoConverter.class)
	@Column(name = "status_autorizacao")
	private StatusAutorizacao statusAutorizacao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_solicitante_id")
	private Usuario usuarioSolicitante;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_autorizador_id")
	private Usuario usuarioAutorizador;

	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;

	@Column(name = "data_autorizacao")
	private LocalDate dataAutorizacao;

}
