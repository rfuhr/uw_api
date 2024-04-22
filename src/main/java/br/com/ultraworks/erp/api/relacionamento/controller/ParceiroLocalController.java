package br.com.ultraworks.erp.api.relacionamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEnderecoMapper;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalEnderecoService;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/relacionamento/parceiro-local")
public class ParceiroLocalController extends GenericController<ParceiroLocal, Long, ParceiroLocalDTO> {

	private ParceiroLocalEnderecoService parceiroLocalEnderecoService;
	private ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper;

	public ParceiroLocalController(ParceiroLocalService service, ParceiroLocalMapper mapper,
			ParceiroLocalEnderecoService parceiroLocalEnderecoService,
			ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper) {
		super(service, mapper);
		this.parceiroLocalEnderecoService = parceiroLocalEnderecoService;
		this.parceiroLocalEnderecoMapper = parceiroLocalEnderecoMapper;
	}

	@GetMapping("/{id}/endereco/nfe")
	public ResponseEntity<?> getEnderecoNFe(@PathVariable Long id) {
		ParceiroLocalEndereco enderecoNFe = parceiroLocalEnderecoService.getEnderecoNFe(id);
		if (enderecoNFe != null)
			return ResponseEntity.ok(parceiroLocalEnderecoMapper.toDto(enderecoNFe));
		return ResponseEntity.notFound().build();
	}
}