package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

public interface IServicoImpressaoNFe {

	void imprimeParaArquivo(String xmlNFe, String path);
	byte[] imprimeParaBytes(String xmlNFe);
	
}
