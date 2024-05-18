package br.com.ultraworks.erp.api.estoque.service;

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

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueRequest;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.ReportsService;

@Service
public class RelatoriosEstoqueService {
	
	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public ResponseEntity<byte[]> imprimirRazaoEstoque(@RequestBody MovimentoEstoqueRequest movimentoEstoqueRequest) {
        try {
        	Date dataInicio = Date.valueOf(movimentoEstoqueRequest.getDataInicio());
        	Date dataFinal = Date.valueOf(movimentoEstoqueRequest.getDataFinal());
        	Date dataSaldo = Date.valueOf(movimentoEstoqueRequest.getDataInicio().minusDays(1));
    		int validaEmpresaFilial = movimentoEstoqueRequest.getEmpresaFilialId() == null ? 1 : 0;
    		int validaLocalEstoque = movimentoEstoqueRequest.getLocalEstoqueId() == null ? 1 : 0;
    		int validaGrupoContabil = movimentoEstoqueRequest.getGrupoContabilId() == null ? 1 : 0;
    		int validaItem = movimentoEstoqueRequest.getItemId() == null ? 1 : 0;
    		int validaOperacaoInterna = movimentoEstoqueRequest.getOperacaoInternaId() == null ? 1 : 0;
    		int validaDocumento = movimentoEstoqueRequest.getDocumento() == null || movimentoEstoqueRequest.getDocumento() == "" ? 1 : 0;
    		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		Optional<Empresa> empresa = empresaService.getById(user.getEmpresaId());
        	
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataInicio", dataInicio);
            parameters.put("dataFinal", dataFinal);
            parameters.put("dataSaldo", dataSaldo);
            parameters.put("validaEmpresaFilialId", validaEmpresaFilial);
            parameters.put("validaLocalEstoque", validaLocalEstoque);
            parameters.put("validaGrupoContabil", validaGrupoContabil);
            parameters.put("validaItem", validaItem);
            parameters.put("validaOperacaoInterna", validaOperacaoInterna);
            parameters.put("validaDocumento", validaDocumento);
            parameters.put("usuarioNome", user.getUsername());
            parameters.put("empresaNome", empresa.get().getNome());
            if (validaEmpresaFilial == 1) {
            	parameters.put("filtroFilial", "<Todas>");
            } else {
            	parameters.put("empresaFilialId", movimentoEstoqueRequest.getEmpresaFilialId());
            	parameters.put("filtroFilial", movimentoEstoqueRequest.getEmpresaFilialId().toString());
            }
            if (validaLocalEstoque == 1) {
            	parameters.put("filtroLocalEstoque", "<Todos>");
            } else {
            	parameters.put("localEstoqueId", movimentoEstoqueRequest.getLocalEstoqueId());
            	parameters.put("filtroLocalEstoque", movimentoEstoqueRequest.getLocalEstoqueId().toString());
            }
            if (validaGrupoContabil == 1) {
            	parameters.put("filtroGrupoContabil", "<Todos>");
            } else {
            	parameters.put("grupoContabilId", movimentoEstoqueRequest.getGrupoContabilId());
            	parameters.put("filtroGrupoContabil", movimentoEstoqueRequest.getGrupoContabilId().toString());
            }
            if (validaItem == 1) {
            	parameters.put("filtroItem", "<Todos>");
            } else {
            	parameters.put("itemId", movimentoEstoqueRequest.getItemId());
            	parameters.put("filtroItem", movimentoEstoqueRequest.getItemId().toString());
            }
            if (validaOperacaoInterna == 1) {
            	parameters.put("filtroOperacaoInterna", "<Todas>");
            } else {
            	parameters.put("operacaoInternaId", movimentoEstoqueRequest.getOperacaoInternaId());
            	parameters.put("filtroOperacaoInterna", movimentoEstoqueRequest.getOperacaoInternaId().toString());
            }
            if (validaDocumento == 1) {
            	parameters.put("filtroDocumento", "<Todos>");
            } else {
            	parameters.put("documento", movimentoEstoqueRequest.getDocumento());
            	parameters.put("filtroDocumento", movimentoEstoqueRequest.getDocumento().toString());
            }

            byte[] report = reportsService.generateReport("RazaoEstoque", parameters);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=RazaoEstoque.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	public ResponseEntity<byte[]> imprimirPosicaoFisicoFinanceiro(@RequestBody SaldoEstoqueRequest saldoEstoqueRequest) {
        try {
        	Date dataInicio = Date.valueOf(saldoEstoqueRequest.getDataInicio());
        	Date dataFinal = Date.valueOf(saldoEstoqueRequest.getDataFinal());
    		int validaEmpresaFilial = saldoEstoqueRequest.getEmpresaFilialId() == null ? 1 : 0;
    		int validaLocalEstoque = saldoEstoqueRequest.getLocalEstoqueId() == null ? 1 : 0;
    		int validaGrupoContabil = saldoEstoqueRequest.getGrupoContabilId() == null ? 1 : 0;
    		int validaItem = saldoEstoqueRequest.getItemId() == null ? 1 : 0;

    		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		Optional<Empresa> empresa = empresaService.getById(user.getEmpresaId());
        	
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataInicio", dataInicio);
            parameters.put("dataFinal", dataFinal);
            parameters.put("validaEmpresaFilialId", validaEmpresaFilial);
            parameters.put("validaLocalEstoque", validaLocalEstoque);
            parameters.put("validaGrupoContabil", validaGrupoContabil);
            parameters.put("validaItem", validaItem);

            parameters.put("usuarioNome", user.getUsername());
            parameters.put("empresaNome", empresa.get().getNome());
            if (validaEmpresaFilial == 1) {
            	parameters.put("filtroFilial", "<Todas>");
            } else {
            	parameters.put("empresaFilialId", saldoEstoqueRequest.getEmpresaFilialId());
            	parameters.put("filtroFilial", saldoEstoqueRequest.getEmpresaFilialId().toString());
            }
            if (validaLocalEstoque == 1) {
            	parameters.put("filtroLocalEstoque", "<Todos>");
            } else {
            	parameters.put("localEstoqueId", saldoEstoqueRequest.getLocalEstoqueId());
            	parameters.put("filtroLocalEstoque", saldoEstoqueRequest.getLocalEstoqueId().toString());
            }
            if (validaGrupoContabil == 1) {
            	parameters.put("filtroGrupoContabil", "<Todos>");
            } else {
            	parameters.put("grupoContabilId", saldoEstoqueRequest.getGrupoContabilId());
            	parameters.put("filtroGrupoContabil", saldoEstoqueRequest.getGrupoContabilId().toString());
            }
            if (validaItem == 1) {
            	parameters.put("filtroItem", "<Todos>");
            } else {
            	parameters.put("itemId", saldoEstoqueRequest.getItemId());
            	parameters.put("filtroItem", saldoEstoqueRequest.getItemId().toString());
            }

            byte[] report = reportsService.generateReport("PosicaoFisicoFinanceiro", parameters);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=PosicaoFisicoFinanceiro.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}
