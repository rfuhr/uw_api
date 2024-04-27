package br.com.ultraworks.erp.api.fiscal.domain.nfe;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cache_nfe")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CacheNFe extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "cacheNfeSeq", sequenceName = "seq_cache_nfe", allocationSize = 1)
	@GeneratedValue(generator = "cacheNfeSeq")
	private Long id;

	@Column(name = "nfe_id")
	private Long nfeId;

	@Column(name = "cache")
	private byte[] cache;

}
