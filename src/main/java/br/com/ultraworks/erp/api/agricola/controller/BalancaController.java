package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.api.agricola.domain.balanca.BalancaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.BalancaMapper;
import br.com.ultraworks.erp.api.agricola.service.BalancaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/balanca")
public class BalancaController extends GenericController<Balanca, Long, BalancaDTO> {

	private BalancaService balancaService;
	private BalancaMapper balancaMapper;

	public BalancaController(BalancaService service, BalancaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("service/get-balanca-by-filial/{empresaFilialId}")
	public ResponseEntity<BalancaDTO> getBalancaByFilial(@PathVariable Long empresaFilialId) {
		Balanca entity = balancaService.getByEmpresaFilialId(empresaFilialId);
		if (entity != null) {
			return ResponseEntity.ok(balancaMapper.toDto(entity));
		} else {
			throw new RegisterNotFoundException("Não encontrado registro");
		}
	};

}