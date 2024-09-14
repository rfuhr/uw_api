package br.com.ultraworks.erp.api.financeiro.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.relatorio.TipoRelatorioFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.relatorio.request.SelecaoRelatorioFinanceiroRequest;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.ReportsService;

public class RelatoriosFinanceiroService {

	private ReportsService reportsService;
	private EmpresaService empresaService;
	private TipoTituloService tipoTituloService;
	private DepartamentoService departamentoService;
	private CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService;
	private ParceiroLocalService parceiroLocalService;
	private CarteiraFinanceiraService carteiraFinanceiraService;

	@Autowired
	public RelatoriosFinanceiroService(ReportsService reportsService, EmpresaService empresaService,
			TipoTituloService tipoTituloService, DepartamentoService departamentoService,
			CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService,
			ParceiroLocalService parceiroLocalService, CarteiraFinanceiraService carteiraFinanceiraService) {
		this.reportsService = reportsService;
		this.empresaService = empresaService;
		this.tipoTituloService = tipoTituloService;
		this.departamentoService = departamentoService;
		this.caracteristicaMovimentoFinanceiroService = caracteristicaMovimentoFinanceiroService;
		this.parceiroLocalService = parceiroLocalService;
		this.carteiraFinanceiraService = carteiraFinanceiraService;
	}

	public ResponseEntity<byte[]> imprimirRelatorio(
			SelecaoRelatorioFinanceiroRequest selecaoRelatorioFinanceiroRequest) {
		TipoRelatorioFinanceiro tipoRelatorioFinanceiro = TipoRelatorioFinanceiro
				.fromValue(selecaoRelatorioFinanceiroRequest.getTipoRelatorioFinanceiro());
		switch (tipoRelatorioFinanceiro) {
		case POSICAOTITULOABERTO:
			return imprimirPosicaoTituloAberto(selecaoRelatorioFinanceiroRequest);
		default:
			break;
		}
		return null;
	}

	public ResponseEntity<byte[]> imprimirPosicaoTituloAberto(
			@RequestBody SelecaoRelatorioFinanceiroRequest selecaoRelatorioFinanceiroRequest) {
		try {

			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<Empresa> empresa = empresaService.getById(user.getEmpresaId());

			Date dataVencimentoInicio = Date.valueOf(selecaoRelatorioFinanceiroRequest.getVencimentoInicial());
			Date dataVencimentoFinal = Date.valueOf(selecaoRelatorioFinanceiroRequest.getVencimentoFinal());
			Date competencia = Date.valueOf(selecaoRelatorioFinanceiroRequest.getCompetencia());
			String filtroTipoTitulo = "<TODOS>";
			if (selecaoRelatorioFinanceiroRequest.getTipoTituloId() != null) {
				TipoTitulo tipoTitulo = tipoTituloService.getById(selecaoRelatorioFinanceiroRequest.getTipoTituloId())
						.get();
				filtroTipoTitulo = String.valueOf(tipoTitulo.getCodigo());
			}
			String filtroDepartamento = "<TODOS>";
			if (selecaoRelatorioFinanceiroRequest.getDepartamentoId() != null) {
				Departamento departamento = departamentoService.getById(selecaoRelatorioFinanceiroRequest.getDepartamentoId()).get();
				filtroDepartamento = departamento.getSigla();
			}

			String filtroCaractMovto = "<TODAS>";
			if (selecaoRelatorioFinanceiroRequest.getCaracteristicaMovimentoFinanceiroId() != null) {
				CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = caracteristicaMovimentoFinanceiroService.getById(selecaoRelatorioFinanceiroRequest.getCaracteristicaMovimentoFinanceiroId()).get();
				filtroCaractMovto = String.valueOf(caracteristicaMovimentoFinanceiro.getCodigo());
			}
			
			String filtroParceiro = "<TODOS>";
			if (selecaoRelatorioFinanceiroRequest.getParceiroLocalId() != null) {
				ParceiroLocal parceiroLocal = parceiroLocalService.getById(selecaoRelatorioFinanceiroRequest.getParceiroLocalId()).get();
				filtroParceiro = parceiroLocal.getParceiro().getRaizCnpjCpf();
			}
			
			String filtroCarteira = "<TODAS>";
			if (selecaoRelatorioFinanceiroRequest.getCarteiraFinanceiraId() != null) {
				CarteiraFinanceira carteiraFinanceira = carteiraFinanceiraService.getById(selecaoRelatorioFinanceiroRequest.getCarteiraFinanceiraId()).get();
				filtroCarteira = String.valueOf(carteiraFinanceira.getCodigo());
			}
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("usuarioNome", user.getUsername());
			parameters.put("empresaNome", empresa.get().getNome());
			parameters.put("dataVectoInicio", dataVencimentoInicio);
			parameters.put("dataVectoFinal", dataVencimentoFinal);
			parameters.put("competencia", competencia);
			parameters.put("filtroTipoTitulo", filtroTipoTitulo);
			parameters.put("filtroDepartamento", filtroDepartamento);
			parameters.put("filtroCaractMovto", filtroCaractMovto);
			parameters.put("filtroParceiro", filtroParceiro);
			parameters.put("filtroCarteira", filtroCarteira);

			byte[] report = reportsService.generateReport("financeiro/PosicaoTituloAberto", parameters);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=PosicaoTituloAberto.pdf");
			return new ResponseEntity<>(report, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
