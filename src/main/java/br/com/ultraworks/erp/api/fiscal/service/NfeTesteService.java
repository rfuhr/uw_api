package br.com.ultraworks.erp.api.fiscal.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatario;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidor;
import br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe.TipoImpressaoDanfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;
import br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao.TipoProcessoEmissao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoImpressaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoIntegracaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeDestinatario;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeIdentificacao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeItem;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.service.TipoDocumentoService;

@Service
public class NfeTesteService {

	private final ResourceLoader resourceLoader;
	private final ApplicationContext context;
	private TipoDocumentoService tipoDocumentoService;
	private EmpresaFilialService empresaFilialService;
	private ParceiroLocalService parceiroLocalService;

	@Autowired
	public NfeTesteService(ApplicationContext context, ResourceLoader resourceLoader,
			EmpresaFilialService empresaFilialService, TipoDocumentoService tipoDocumentoService,
			ParceiroLocalService parceiroLocalService) {
		this.context = context;
		this.resourceLoader = resourceLoader;
		this.empresaFilialService = empresaFilialService;
		this.tipoDocumentoService = tipoDocumentoService;
		this.parceiroLocalService = parceiroLocalService;
	}

	public byte[] gerarDanfeTeste() {
		IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
		try {
			Resource resource = resourceLoader.getResource("classpath:demos");
			String path = resource.getFile().getAbsolutePath();
			String xml = ImpressaoUtil.leArquivo(path.concat("/nfeteste.xml"));
			return servicoImpressaoNFe.imprimeParaBytes(xml);
		} catch (IOException e) {
			return null;
		}
	}

	public byte[] gerarNFeTeste(Long empresaLocalId) {
		IServicoIntegracaoNFe servicoIntegracaoNFe = context.getBean(IServicoIntegracaoNFe.class);
		RetornoNFeIntegracao retornoNfeIntegracao = servicoIntegracaoNFe
				.emitirNFe(montarContainerIntegracaoNFe(empresaLocalId));
		if (StringUtils.isNotBlank(retornoNfeIntegracao.getXml())) {
			IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
			return servicoImpressaoNFe.imprimeParaBytes(retornoNfeIntegracao.getXml());
		}
		return null;
	}

	private ContainerIntegracaoNFe montarContainerIntegracaoNFe(Long empresaFilialId) {
		EmpresaFilial empresaFilial = empresaFilialService.getById(empresaFilialId).get();
		ParceiroLocal parceiroLocalEmitente = parceiroLocalService.getById(empresaFilial.getParceiroLocal().getId())
				.get();
		return ContainerIntegracaoNFe.builder().empresaFilialEmissorId(empresaFilialId)
				.nfeIdentificacao(montaNfeIdentificacao(parceiroLocalEmitente)).nfeDestinatario(montaNfeDestinatario())
				.nfeItens(montaNfeItens()).build();
	}

	private List<ContainerIntegracaoNFeItem> montaNfeItens() {
		List<ContainerIntegracaoNFeItem> listaItens = new ArrayList<>();

		ContainerIntegracaoNFeItem nfeItem = ContainerIntegracaoNFeItem.builder()

				.build();

		listaItens.add(nfeItem);
		return listaItens;
	}

	private ContainerIntegracaoNFeDestinatario montaNfeDestinatario() {
		ParceiroLocal parceiroLocal = parceiroLocalService.findByCpfCnpj("19560114000114");
		return ContainerIntegracaoNFeDestinatario.builder().parceiroLocalEndereco(parceiroLocal.getEnderecos().get(0))
				.indicadorIEDestinatario(IndicadorIEDestinatario.NAOCONTRIBUINTE).build();
	}

	private ContainerIntegracaoNFeIdentificacao montaNfeIdentificacao(ParceiroLocal parceiroLocalEmitente) {
		return ContainerIntegracaoNFeIdentificacao.builder()
				.tipoDocumento(tipoDocumentoService.findByCodigoReceita("55")).serie(1).numeroNFe(1)
				.naturezaOperacao("Venda").dataHoraSaidaEntrada(LocalDateTime.now())
				.indicadorOperacao(IndicadorOperacao.SAIDA).localDestinoOperacao(DestinoOperacao.ESTADUAL)
				.municipioOcorrenciaFatoGeradorICMS(parceiroLocalEmitente.getEnderecos().get(0).getCidade())
				.tipoImpressaoDanfe(TipoImpressaoDanfe.DANFENORMALRETRATO).finalidadeNfe(FinalidadeNfe.NORMAL)
				.tipoConsumidor(TipoConsumidor.FINAL).tipoPresencaComprador(TipoPresencaComprador.PRESENCIAL)
				.processoEmissao(TipoProcessoEmissao.APLICATIVOPROPRIO).build();
	}

}
