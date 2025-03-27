package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.InutilizacaoUtil;
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
public class ServicoInutilizacaoNFeImpl implements IServicoInutilizacaoNFe {
	
	private ConfigEmpresaNFeService configEmpresaNFeService;
	private EmpresaCertificadoService empresaCertificadoService;
	private ResourceLoader resourceLoader;
	
	@Autowired
	public ServicoInutilizacaoNFeImpl(ConfigEmpresaNFeService configEmpresaNFeService,
			EmpresaCertificadoService empresaCertificadoService,
			ResourceLoader resourceLoader) {
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.empresaCertificadoService = empresaCertificadoService;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public RetornoNFeIntegracao inutilizarNFe(NFe nfe) {
		RetornoNFeIntegracao retornoNFeIntegracao = new RetornoNFeIntegracao();
		
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService
				.getByEmpresaFilialId(nfe.getEmpresaFilial().getId());
		ConfiguracoesNfe configuracoesNfe = criarConfiguracoesNfe(configEmpresaNFe);
		
        String cnpj = nfe.getNfeEmit().getCnpj();
        int serie = nfe.getNfeIde().getSerie();
        int numeroInicial = nfe.getNfeIde().getNnf();
        int numeroFinal = nfe.getNfeIde().getNnf();
        String justificativa = "Numeração da Nota não foi utilizada no sistema.";
        LocalDateTime dataCancelamento = LocalDateTime.now();
        
        TInutNFe inutNFe = null;
        TRetInutNFe retorno = null;
        
        try {
        
	        inutNFe = InutilizacaoUtil.montaInutilizacao(DocumentoEnum.NFE, cnpj, serie, numeroInicial, numeroFinal, justificativa, dataCancelamento, configuracoesNfe);
			
	        retorno = Nfe.inutilizacao(configuracoesNfe, inutNFe, DocumentoEnum.NFE, true);
	        
	        RetornoUtil.validaInutilizacao(retorno);
	        
	        InutilizacaoUtil.criaProcInutilizacao(configuracoesNfe, inutNFe, retorno);
	        
	        retornoNFeIntegracao.setProtocolo(retorno.getInfInut().getNProt());
	        retornoNFeIntegracao.setStatus(retorno.getInfInut().getCStat());
	        retornoNFeIntegracao.setXml(retorno.toString());
	        retornoNFeIntegracao.setErroValidarRetorno(retorno.getInfInut().getXMotivo());
		
        } catch (Exception e) {
        	throw new BusinessException(e.getMessage());
        }
        
		try {
			String xmlEnvio = XmlNfeUtil.objectToXml(inutNFe, configuracoesNfe.getEncode());
			retornoNFeIntegracao.setXmlEnvio(xmlEnvio);
		} catch (JAXBException | NfeException e) {
			retornoNFeIntegracao.setXmlEnvio("Erro ao recuperar o XML de Envio da Inutilização da NFe.");
		}
		
		try {
			String xmlRetorno = XmlNfeUtil.objectToXml(retorno, configuracoesNfe.getEncode());
			retornoNFeIntegracao.setXmlRetorno(xmlRetorno);
		} catch (JAXBException | NfeException e) {
			retornoNFeIntegracao.setXmlRetorno("Erro ao recuperar o XML de Retorno da Inutilização da NFe.");
		}
        
        return retornoNFeIntegracao;
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
