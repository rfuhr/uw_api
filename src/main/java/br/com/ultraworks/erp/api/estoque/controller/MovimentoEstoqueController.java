package br.com.ultraworks.erp.api.estoque.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueResponse;
import br.com.ultraworks.erp.api.estoque.mapper.MovimentoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.MovimentoEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.service.ReportsService;


@RestController
@RequestMapping("/estoque/movimento-estoque")
public class MovimentoEstoqueController extends GenericController<MovimentoEstoque, Long, MovimentoEstoqueDTO> {
	
	MovimentoEstoqueService service;
	ReportsService reportsService;
	
	public MovimentoEstoqueController(MovimentoEstoqueService service, MovimentoEstoqueMapper mapper,
			ReportsService reportsService) {
		super(service, mapper);
		this.service = service;
		this.reportsService = reportsService;
	}
	
	@PostMapping("/consultar")
	public List<MovimentoEstoqueResponse> buscaMovimentoEstoque(@RequestBody MovimentoEstoqueRequest movimentoEstoqueRequest) {
		return service.buscaMovimentoEstoque(movimentoEstoqueRequest);
	}
	
	@PostMapping("/imprimir")
    public ResponseEntity<byte[]> generateReport(@RequestBody MovimentoEstoqueRequest movimentoEstoqueRequest) {
        try {
        	Date dataInicio = Date.valueOf(movimentoEstoqueRequest.getDataInicio());
        	Date dataFinal = Date.valueOf(movimentoEstoqueRequest.getDataFinal());
        	
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataInicio", dataInicio);
            parameters.put("dataFinal", dataFinal);
            parameters.put("usuarioNome", "Admin");
            parameters.put("empresaNome", "EMPRESA MODELO S/A");
            parameters.put("filtroFilial", "<Todas>");
            parameters.put("filtroLocalEstoque", "<Todos>");
            parameters.put("filtroGrupoContabil", "<Todos>");
            parameters.put("filtroOperacaoInterna", "<Todas>");
            parameters.put("filtroDocumento", "<Todos>");
            parameters.put("filtroItem", "<Todos>");

            byte[] report = reportsService.generateReport("RazaoEstoque", parameters);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=RazaoEstoque.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}