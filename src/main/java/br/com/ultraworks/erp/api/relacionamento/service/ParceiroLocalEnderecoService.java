package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEnderecoDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEnderecoMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalEnderecoRepository;
import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.TipoEndereco;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalEnderecoService extends GenericService<ParceiroLocalEndereco, Long, ParceiroLocalEnderecoDTO> {
	
	ParceiroLocalEnderecoRepository repository;
	ParceiroLocalEnderecoMapper mapper;
	
	@Autowired
	public ParceiroLocalEnderecoService(ParceiroLocalEnderecoRepository repository, ParceiroLocalEnderecoMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalEndereco> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}
	
	public ParceiroLocalEndereco getEnderecoNFe(Long parceiroLocalId) {
		List<ParceiroLocalEndereco> enderecos = repository.findByParceiroLocalId(parceiroLocalId);
		Optional<ParceiroLocalEndereco> endereco = enderecos.stream().filter(end -> end.getTipoEndereco().equals(TipoEndereco.FISCAL)).findFirst();
		if (endereco.isPresent()) return endereco.get();
		
		endereco = enderecos.stream().filter(end -> end.getTipoEndereco().equals(TipoEndereco.COMERCIAL)).findFirst();
		if (endereco.isPresent()) return endereco.get();
		
		if (!enderecos.isEmpty()) return enderecos.get(0);
		
		return null;
	}

}
