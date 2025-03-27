package br.com.ultraworks.erp.api.fiscal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeComunicacaoSEFAZ;
import br.com.ultraworks.erp.api.fiscal.repository.NFeComunicacaoSEFAZRepository;

@Service
public class NFeComunicacaoSEFAZService {
	
	private NFeComunicacaoSEFAZRepository nFeComunicacaoSEFAZRepository;
	
	@Autowired
	public NFeComunicacaoSEFAZService(NFeComunicacaoSEFAZRepository nFeComunicacaoSEFAZRepository) {
		this.nFeComunicacaoSEFAZRepository = nFeComunicacaoSEFAZRepository;
	}
	
	public List<NFeComunicacaoSEFAZ> findByNFeId(Long nfeId) {
		return nFeComunicacaoSEFAZRepository.findByNfeId(nfeId, Sort.by(Sort.Direction.DESC, "criadoEm"));
	}

}
