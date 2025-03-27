package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import java.io.IOException;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.EmpresaCertificadoService;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.core.exception.BusinessException;

@Service
@Scope("prototype")
public class ServicoConsultaStatusNFeImpl implements IServicoConsultaStatusNFe {
	
	private ConfigEmpresaNFeService configEmpresaNFeService;
	private EmpresaCertificadoService empresaCertificadoService;
	private ResourceLoader resourceLoader;

	public ServicoConsultaStatusNFeImpl(ConfigEmpresaNFeService configEmpresaNFeService,
			EmpresaCertificadoService empresaCertificadoService,
			ResourceLoader resourceLoader) {
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.empresaCertificadoService = empresaCertificadoService;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public TRetConsSitNFe consultarStatusNFe(NFe nfe) {
		
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService
				.getByEmpresaFilialId(nfe.getEmpresaFilial().getId());
		ConfiguracoesNfe configuracoesNfe = criarConfiguracoesNfe(configEmpresaNFe);
		
		String chaveNFe = nfe.getChaveNfe();
		if (chaveNFe.startsWith("NFe")) {
			chaveNFe = chaveNFe.replaceFirst("^NFe", "");
        }

		TRetConsSitNFe consultaXml = null;
		
		try {
			consultaXml = Nfe.consultaXml(configuracoesNfe, chaveNFe, DocumentoEnum.NFE);
		} catch (Exception e) {
        	throw new BusinessException(e.getMessage());
		}

		return consultaXml;
	}
	
	private ConfiguracoesNfe criarConfiguracoesNfe(ConfigEmpresaNFe configEmpresaNFe) {
		try {
			Resource resource = resourceLoader.getResource("classpath:schemas");
			String path = resource.getFile().getAbsolutePath().concat("/nfe");

			Certificado certificado = carregarCertificado(configEmpresaNFe);
			AmbienteEnum ambiente = AmbienteEnum.getByCodigo(configEmpresaNFe.getTipoAmbiente().getValue());
			return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.PR, ambiente, certificado, path);
		} catch (CertificadoException | IOException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private Certificado carregarCertificado(ConfigEmpresaNFe configEmpresaNFe) {
		Optional<EmpresaCertificado> optionalEmpresaCertificado = empresaCertificadoService
				.encontrarCertificadoValidoMaisProximoVencimento(configEmpresaNFe.getConfigEmpresa().getEmpresa(),
						configEmpresaNFe.getTipoCertificado());
		if (optionalEmpresaCertificado.isPresent()) {
			EmpresaCertificado empresaCertificado = optionalEmpresaCertificado.get();
			try {
				return CertificadoService.certificadoPfxBytes(empresaCertificado.getArquivoPfx(),
						empresaCertificado.getSenha());
			} catch (CertificadoException e) {
				throw new BusinessException(e.getMessage());
			}
		} else {
			throw new BusinessException("Não encontrado certificado válido para empresa "
					+ configEmpresaNFe.getConfigEmpresa().getEmpresa().getNome());
		}
	}

}
