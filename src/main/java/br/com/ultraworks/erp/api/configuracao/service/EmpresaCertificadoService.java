package br.com.ultraworks.erp.api.configuracao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificadoDTO;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.configuracao.mapper.EmpresaCertificadoMapper;
import br.com.ultraworks.erp.api.configuracao.repository.EmpresaCertificadoRepository;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EmpresaCertificadoService extends GenericService<EmpresaCertificado, Long, EmpresaCertificadoDTO> {

	@Autowired
	public EmpresaCertificadoService(EmpresaCertificadoRepository repository, EmpresaCertificadoMapper mapper) {
		super(repository, mapper);
	}

	public Optional<EmpresaCertificado> encontrarCertificadoValidoMaisProximoVencimento(Empresa empresa, TipoCertificado tipoCertificado) {
		return ((EmpresaCertificadoRepository) repository).encontrarCertificadoValidoMaisProximoVencimento(empresa, tipoCertificado);
		
	}
}