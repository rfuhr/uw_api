package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.SelecaoRomaneioParaFixacaoRequest;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.integrator.ServicoIntegracaoFixacao;
import br.com.ultraworks.erp.api.agricola.integrator.TipoOperacaoIntegracaoFixacao;
import br.com.ultraworks.erp.api.agricola.mapper.FixacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.FixacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.service.contratoagricola.ContratoAgricolaService;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class FixacaoAgricolaService extends GenericService<FixacaoAgricola, Long, FixacaoAgricolaDTO> {

	private final ApplicationContext context;
	private OperacaoInternaService operacaoInternaService;
	private EmpresaFilialService empresaFilialService;
	private DepartamentoService departamentoService;
	private ParceiroLocalService parceiroLocalService;
	private ItemService itemService;
	private GrupoOperacaoAgricolaService grupoOperacaoAgricolaService;
	private TipoPrecoAgricolaService tipoPrecoAgricolaService;
	private ContratoAgricolaService contratoAgricolaService;

	@Autowired
	public FixacaoAgricolaService(ApplicationContext context, FixacaoAgricolaRepository repository,
			FixacaoAgricolaMapper mapper, OperacaoInternaService operacaoInternaService,
			EmpresaFilialService empresaFilialService, DepartamentoService departamentoService,
			ParceiroLocalService parceiroLocalService, ItemService itemService,
			GrupoOperacaoAgricolaService grupoOperacaoAgricolaService,
			TipoPrecoAgricolaService tipoPrecoAgricolaService, ContratoAgricolaService contratoAgricolaService) {
		super(repository, mapper);
		this.context = context;
		this.operacaoInternaService = operacaoInternaService;
		this.empresaFilialService = empresaFilialService;
		this.departamentoService = departamentoService;
		this.parceiroLocalService = parceiroLocalService;
		this.itemService = itemService;
		this.grupoOperacaoAgricolaService = grupoOperacaoAgricolaService;
		this.tipoPrecoAgricolaService = tipoPrecoAgricolaService;
		this.contratoAgricolaService = contratoAgricolaService;

	}

	public void pesquisaRomaneios(SelecaoRomaneioParaFixacaoRequest request) {
		EmpresaFilial empresaFilial = this.empresaFilialService.getById(request.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado filial da empresa com id " + request.getEmpresaFilialId()));
		Departamento departamento = null;
		if (request.getDepartamentoId() != null) {
			departamento = this.departamentoService.getById(request.getDepartamentoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado departamento com id " + request.getDepartamentoId()));
		}
		ParceiroLocal parceiroLocal = null;
		if (request.getParceiroLocalId() != null) {
			parceiroLocal = this.parceiroLocalService.getById(request.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + request.getParceiroLocalId()));
		}
		Item item = this.itemService.getById(request.getItemId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado produto com id " + request.getItemId()));
		OperacaoInterna operacaoInternaFixacao = this.operacaoInternaService
				.getById(request.getOperacaoInternaFixacaoId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado operação interna de fixação com id " + request.getOperacaoInternaFixacaoId()));
		GrupoOperacaoAgricola grupoOperacaoAgricola = this.grupoOperacaoAgricolaService
				.getById(request.getGrupoOperacaoAgricolaId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo de operação com id " + request.getGrupoOperacaoAgricolaId()));
		TipoPrecoAgricola tipoPrecoAgricola = this.tipoPrecoAgricolaService.getById(request.getTipoPrecoAgricolaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de preço agrícola com id " + request.getTipoPrecoAgricolaId()));
		ContratoAgricola contratoAgricola = null;
		if (request.getContratoAgricolaId() != null) {
			contratoAgricola = this.contratoAgricolaService.getById(request.getContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado contrato agrícola com id " + request.getContratoAgricolaId()));
		}

		ServicoIntegracaoFixacao servicoIntegracaoFixacao = context.getBean(ServicoIntegracaoFixacao.class);
		servicoIntegracaoFixacao.iniciarIntegracao(TipoOperacaoIntegracaoFixacao.SELECAOROMANEIOS);
		servicoIntegracaoFixacao.definirOperacao(operacaoInternaFixacao);
		servicoIntegracaoFixacao.atribuirConfiguracaoParaSelecaoRomaneio(empresaFilial, departamento, parceiroLocal,
				item, grupoOperacaoAgricola, request.getDataDocumento(), contratoAgricola);
		servicoIntegracaoFixacao.executarSelecaoRomaneio();
	}
}