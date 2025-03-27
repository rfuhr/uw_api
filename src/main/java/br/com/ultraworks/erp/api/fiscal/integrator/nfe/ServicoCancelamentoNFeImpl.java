package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.EmpresaCertificadoService;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.core.exception.BusinessException;

@Service
@Scope("prototype")
public class ServicoCancelamentoNFeImpl implements IServicoCancelamentoNFe {
	
	private ApplicationContext context;
	private ConfigEmpresaNFeService configEmpresaNFeService;
	private EmpresaCertificadoService empresaCertificadoService;
	private ResourceLoader resourceLoader;

	public ServicoCancelamentoNFeImpl(ApplicationContext context, ConfigEmpresaNFeService configEmpresaNFeService,
			EmpresaCertificadoService empresaCertificadoService,
			ResourceLoader resourceLoader) {
		this.context = context;
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.empresaCertificadoService = empresaCertificadoService;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public RetornoNFeIntegracao cancelarNFe(NFe nfe) {
		RetornoNFeIntegracao retornoNFeIntegracao = new RetornoNFeIntegracao();
		
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService
				.getByEmpresaFilialId(nfe.getEmpresaFilial().getId());
		ConfiguracoesNfe configuracoesNfe = criarConfiguracoesNfe(configEmpresaNFe);
		
		String chaveNFe = nfe.getChaveNfe();
		if (chaveNFe.startsWith("NFe")) {
			chaveNFe = chaveNFe.replaceFirst("^NFe", "");
        }
		
        Evento cancela = new Evento();
        cancela.setChave(chaveNFe);
        cancela.setProtocolo(nfe.getNprotnfe());
        cancela.setCnpj(nfe.getNfeEmit().getCnpj());
        cancela.setMotivo("Cancelamento solicitado pelo usuário");
        cancela.setDataEvento(LocalDateTime.now());
        
        TRetEnvEvento retorno = null;
        TEnvEvento enviEvento = null;
		
        try {
        
        	enviEvento = CancelamentoUtil.montaCancelamento(cancela, configuracoesNfe);
        	
			
        	retorno = Nfe.cancelarNfe(configuracoesNfe, enviEvento, true, DocumentoEnum.NFE);
        	
        	if (retorno.getRetEvento() != null && retorno.getRetEvento().get(0).getInfEvento().getCStat().equals("573")) {
	        	consultaStatusNFe(retornoNFeIntegracao, configuracoesNfe, nfe);
	        	return retornoNFeIntegracao;
        	}
        	
	        RetornoUtil.validaCancelamento(retorno);
	        
	        CancelamentoUtil.criaProcEventoCancelamento(configuracoesNfe, enviEvento, retorno.getRetEvento().get(0));
	        
	        if (retorno.getRetEvento().get(0).getInfEvento().getCStat().equals("135")) {
	        	consultaStatusNFe(retornoNFeIntegracao, configuracoesNfe, nfe);
	        } else {
	        	retornoNFeIntegracao.setProtocolo(retorno.getRetEvento().get(0).getInfEvento().getNProt());
	        	retornoNFeIntegracao.setStatus(retorno.getRetEvento().get(0).getInfEvento().getCStat());
	        	retornoNFeIntegracao.setXml(retorno.toString());
	        	retornoNFeIntegracao.setErroValidarRetorno(retorno.getRetEvento().get(0).getInfEvento().getXMotivo());	        	
	        }

		} catch (Exception e) {
        	throw new BusinessException(e.getMessage());
        }
		
		try {
			String xmlEnvio = XmlNfeUtil.objectToXml(enviEvento, configuracoesNfe.getEncode());
			retornoNFeIntegracao.setXmlEnvio(xmlEnvio);
		} catch (JAXBException | NfeException e) {
			retornoNFeIntegracao.setXmlEnvio("Erro ao recuperar o XML de Cancelamento da NFe.");
		}
		
		try {
			String xmlRetorno = XmlNfeUtil.objectToXml(retorno, configuracoesNfe.getEncode());
			retornoNFeIntegracao.setXmlRetorno(xmlRetorno);
		} catch (JAXBException | NfeException e) {
			retornoNFeIntegracao.setXmlRetorno("Erro ao recuperar o XML do Retorno do Cancelamento da NFe.");
		}
        
        return retornoNFeIntegracao;
	}

	private void consultaStatusNFe(RetornoNFeIntegracao retornoNFeIntegracao, ConfiguracoesNfe configuracoesNfe,
			NFe nfe) throws NfeException {
		IServicoConsultaStatusNFe servicoConsultaStatusNFe = context.getBean(IServicoConsultaStatusNFe.class);
		
		TRetConsSitNFe retornoConsulta = servicoConsultaStatusNFe.consultarStatusNFe(nfe);
		
		retornoNFeIntegracao.setProtocolo(retornoConsulta.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getNProt());
		retornoNFeIntegracao.setStatus(retornoConsulta.getCStat());
		retornoNFeIntegracao.setErroValidarRetorno(retornoConsulta.getXMotivo());	
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
