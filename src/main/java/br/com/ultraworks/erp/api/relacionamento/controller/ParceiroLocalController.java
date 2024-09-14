package br.com.ultraworks.erp.api.relacionamento.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedadeDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEnderecoMapper;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalMapper;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalPropriedadeMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalEnderecoService;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalPropriedadeService;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/relacionamento/parceiro-local")
public class ParceiroLocalController extends GenericController<ParceiroLocal, Long, ParceiroLocalDTO> {

	private ParceiroLocalEnderecoService parceiroLocalEnderecoService;
	private ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper;
	private ParceiroLocalPropriedadeService parceiroLocalPropriedadeService;
	private ParceiroLocalPropriedadeMapper parceiroLocalPropriedadeMapper;
	

	public ParceiroLocalController(ParceiroLocalService service, ParceiroLocalMapper mapper,
			ParceiroLocalEnderecoService parceiroLocalEnderecoService,
			ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper,
			ParceiroLocalPropriedadeService parceiroLocalPropriedadeService,
			ParceiroLocalPropriedadeMapper parceiroLocalPropriedadeMapper) {
		super(service, mapper);
		this.parceiroLocalEnderecoService = parceiroLocalEnderecoService;
		this.parceiroLocalPropriedadeService = parceiroLocalPropriedadeService;
		this.parceiroLocalEnderecoMapper = parceiroLocalEnderecoMapper;
		this.parceiroLocalPropriedadeMapper = parceiroLocalPropriedadeMapper;
	}

	@GetMapping("/{id}/endereco/nfe")
	public ResponseEntity<?> getEnderecoNFe(@PathVariable Long id) {
		ParceiroLocalEndereco enderecoNFe = parceiroLocalEnderecoService.getEnderecoNFe(id);
		if (enderecoNFe != null)
			return ResponseEntity.ok(parceiroLocalEnderecoMapper.toDto(enderecoNFe));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}/propriedade")
	public ResponseEntity<?> getPropriedades(@PathVariable Long id, @RequestBody LazyParams params) {
		Page<ParceiroLocalPropriedade> resultados = this.parceiroLocalPropriedadeService.getPropriedadesPaginada(id, params);
		
		ResultPage<ParceiroLocalPropriedadeDTO> resultado = new ResultPage<ParceiroLocalPropriedadeDTO>(this.parceiroLocalPropriedadeMapper.toDto(resultados.getContent()),
				resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
				resultados.getPageable().getPageSize(), resultados.getTotalPages());
		return ResponseEntity.ok(resultado);
	}
}