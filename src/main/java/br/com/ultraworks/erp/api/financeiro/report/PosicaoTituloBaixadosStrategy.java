package br.com.ultraworks.erp.api.financeiro.report;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.relatorio.request.SelecaoRelatorioFinanceiroRequest;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.service.CaracteristicaMovimentoFinanceiroService;
import br.com.ultraworks.erp.api.financeiro.service.CarteiraFinanceiraService;
import br.com.ultraworks.erp.api.financeiro.service.TipoOperacaoFinanceiraService;
import br.com.ultraworks.erp.api.financeiro.service.TipoTituloService;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.relatorios.AbstractRelatorioStrategy;
import br.com.ultraworks.erp.core.relatorios.ISelecaoRelatorioRequest;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.ReportsService;
import br.com.ultraworks.erp.core.util.DateUtils;
import br.com.ultraworks.erp.core.util.SQLUtils;

@Service
public class PosicaoTituloBaixadosStrategy extends AbstractRelatorioStrategy {

	private final TipoTituloService tipoTituloService;
	private final DepartamentoService departamentoService;
	private final CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService;
	private final ParceiroLocalService parceiroLocalService;
	private final CarteiraFinanceiraService carteiraFinanceiraService;
	private final TipoOperacaoFinanceiraService tipoOperacaoFinanceiraService;

	@Autowired
	public PosicaoTituloBaixadosStrategy(ReportsService reportsService, EmpresaService empresaService,
			TipoTituloService tipoTituloService, DepartamentoService departamentoService,
			CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService,
			ParceiroLocalService parceiroLocalService, CarteiraFinanceiraService carteiraFinanceiraService,
			TipoOperacaoFinanceiraService tipoOperacaoFinanceiraService) {
		super(reportsService, empresaService);
		this.tipoTituloService = tipoTituloService;
		this.departamentoService = departamentoService;
		this.caracteristicaMovimentoFinanceiroService = caracteristicaMovimentoFinanceiroService;
		this.parceiroLocalService = parceiroLocalService;
		this.carteiraFinanceiraService = carteiraFinanceiraService;
		this.tipoOperacaoFinanceiraService = tipoOperacaoFinanceiraService;
	}

	@Override
	protected void addSpecificParameters(Map<String, Object> parameters, ISelecaoRelatorioRequest request) {
		SelecaoRelatorioFinanceiroRequest financeiroRequest = (SelecaoRelatorioFinanceiroRequest) request;
		Date dataMovimentoInicio = Date.valueOf(financeiroRequest.getDataMovimentoInicial());
		Date dataMovimentoFinal = Date.valueOf(financeiroRequest.getDataMovimentoFinal());
		Date competencia = Date.valueOf(financeiroRequest.getCompetencia());
		String filtroTipoTitulo = getFiltroTipoTitulo(financeiroRequest.getTipoTituloId());
		String filtroDepartamento = getFiltroDepartamento(financeiroRequest.getDepartamentoId());
		String filtroCaractMovto = getFiltroCaractMovto(financeiroRequest.getCaracteristicaMovimentoFinanceiroId());
		String filtroParceiro = getFiltroParceiro(financeiroRequest.getParceiroLocalId());
		String filtroCarteira = getFiltroCarteira(financeiroRequest.getCarteiraFinanceiraId());
		String filtroTipoOperacao = getFiltroTipoOperacao(financeiroRequest.getTipoOperacaoFinanceiraId());

		parameters.put("dataMovtoInicio", dataMovimentoInicio);
		parameters.put("dataMovtoFinal", dataMovimentoFinal);
		parameters.put("competencia", competencia);
		parameters.put("filtroTipoTitulo", filtroTipoTitulo);
		parameters.put("filtroDepartamento", filtroDepartamento);
		parameters.put("filtroCaractMovto", filtroCaractMovto);
		parameters.put("filtroParceiro", filtroParceiro);
		parameters.put("filtroCarteira", filtroCarteira);
		parameters.put("filtroTipoOperacao", filtroTipoOperacao);
	}

	private String getFiltroTipoTitulo(Long tipoTituloId) {
		if (tipoTituloId != null) {
			TipoTitulo tipoTitulo = tipoTituloService.getById(tipoTituloId).get();
			return String.valueOf(tipoTitulo.getCodigo());
		}
		return "<TODOS>";
	}

	private String getFiltroDepartamento(Long departamentoId) {
		if (departamentoId != null) {
			Departamento departamento = departamentoService.getById(departamentoId).get();
			return departamento.getSigla();
		}
		return "<TODOS>";
	}

	private String getFiltroCaractMovto(Long caracteristicaMovimentoFinanceiroId) {
		if (caracteristicaMovimentoFinanceiroId != null) {
			CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = caracteristicaMovimentoFinanceiroService
					.getById(caracteristicaMovimentoFinanceiroId).get();
			return String.valueOf(caracteristicaMovimentoFinanceiro.getCodigo());
		}
		return "<TODAS>";
	}

	private String getFiltroParceiro(Long parceiroLocalId) {
		if (parceiroLocalId != null) {
			ParceiroLocal parceiroLocal = parceiroLocalService.getById(parceiroLocalId).get();
			return parceiroLocal.getParceiro().getRaizCnpjCpf();
		}
		return "<TODOS>";
	}

	private String getFiltroCarteira(Long carteiraFinanceiraId) {
		if (carteiraFinanceiraId != null) {
			CarteiraFinanceira carteiraFinanceira = carteiraFinanceiraService.getById(carteiraFinanceiraId).get();
			return String.valueOf(carteiraFinanceira.getCodigo());
		}
		return "<TODAS>";
	}

	private String getFiltroTipoOperacao(Long tipoOperacaoFinanceiraId) {
		if (tipoOperacaoFinanceiraId != null) {
			TipoOperacaoFinanceira tipoOperacaoFinanceira = tipoOperacaoFinanceiraService
					.getById(tipoOperacaoFinanceiraId).get();
			return tipoOperacaoFinanceira.getSigla();
		}
		return "<TODAS>";
	}

	@Override
	protected String getReportName() {
		return "financeiro/PosicaoTituloBaixados";
	}

	@Override
	protected String getSqlWhere(ISelecaoRelatorioRequest request) {
		SelecaoRelatorioFinanceiroRequest financeiroRequest = (SelecaoRelatorioFinanceiroRequest) request;
		CustomUser user = getAuthenticatedUser();
		Optional<Empresa> empresa = getEmpresa(user.getEmpresaId());

		Map<String, String> where = new HashMap<>();
		where.put("empresaId", String.valueOf(empresa.get().getId()));
		if (financeiroRequest.getTipoTituloId() != null)
			where.put("tipotituloid", String.valueOf(financeiroRequest.getTipoTituloId()));
		if (financeiroRequest.getDepartamentoId() != null)
			where.put("departamentoid", String.valueOf(financeiroRequest.getDepartamentoId()));
		if (financeiroRequest.getParceiroLocalId() != null) {
			ParceiroLocal parceiroLocal = parceiroLocalService.getById(financeiroRequest.getParceiroLocalId()).get();
			where.put("parceiroId", String.valueOf(parceiroLocal.getParceiro().getId()));
		}
		if (financeiroRequest.getCaracteristicaMovimentoFinanceiroId() != null) {
			where.put("caracteristicamovimentofinanceiroid",
					String.valueOf(financeiroRequest.getCaracteristicaMovimentoFinanceiroId()));
		}
		if (financeiroRequest.getCarteiraFinanceiraId() != null) {
			where.put("carteiraid", String.valueOf(financeiroRequest.getCarteiraFinanceiraId()));
		}
		if (financeiroRequest.getTipoOperacaoFinanceiraId() != null) {
			where.put("tipoOperacaoFinanceiraId", String.valueOf(financeiroRequest.getTipoOperacaoFinanceiraId()));
		}
    	String sql = SQLUtils.generateWhereClause(where);
    	sql = sql.concat(" AND movimentofinanceirodatamovimento between '").concat(DateUtils.formatDate_yyyyMMdd(financeiroRequest.getDataMovimentoInicial()))
    			.concat("' AND '").concat(DateUtils.formatDate_yyyyMMdd(financeiroRequest.getDataMovimentoFinal())).concat("'");
        return sql;

	}

	@Override
	protected String getSql(ISelecaoRelatorioRequest request) {
		return "select * from vw_fin_posicaotitulobaixados ";
	}
}
