package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import br.com.ultraworks.erp.core.exception.BusinessException;

@Service
@Scope("prototype")
public class ServicoImpressaoNFeImpl implements IServicoImpressaoNFe {

	@Override
	public void imprimeParaArquivo(String xmlNFe, String path) {
		try {
			Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xmlNFe);
			ImpressaoService.impressaoPdfArquivo(impressao, path);
		} catch (Exception e) {
			throw new BusinessException("Erro ao salvar danfe em arquivo. Motivo: " + e.getMessage());
		}
	}

	@Override
	public byte[] imprimeParaBytes(String xmlNFe) {
		try {
			Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xmlNFe);
			
			return ImpressaoService.impressaoPdfByte(impressao);
		} catch (Exception e) {
			throw new BusinessException("Erro ao salvar danfe em arquivo. Motivo: " + e.getMessage());
		}
	}
}
