package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EmpresaCertificadoRepository extends UWRepository<EmpresaCertificado, Long> {

	@Query("SELECT ec FROM EmpresaCertificado ec WHERE ec.empresa = :empresa "
			+ "AND ec.tipoCertificado = :tipoCertificado AND ec.dataValidade >= CURRENT_DATE "
			+ "ORDER BY ec.dataValidade ASC")
	Optional<EmpresaCertificado> encontrarCertificadoValidoMaisProximoVencimento(@Param("empresa") Empresa empresa,
			@Param("tipoCertificado") TipoCertificado tipoCertificado);
}
