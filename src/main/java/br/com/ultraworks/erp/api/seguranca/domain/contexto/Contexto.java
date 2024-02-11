package br.com.ultraworks.erp.api.seguranca.domain.contexto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contexto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contexto {

	@Id
	@SequenceGenerator(name = "contextoSeq", sequenceName = "seq_contexto", allocationSize = 1)
	@GeneratedValue(generator = "contextoSeq")
	private Long id;

	@Column(name = "usuario_id")
	private Long usuarioId;
	@Column(name = "empresa_id")
	private Long empresaId;
	@Column(name = "empresa_filial_id")
	private Long empresaFilialId;
	@Column(name = "modulo_id")
	private Long moduloId;	
}
