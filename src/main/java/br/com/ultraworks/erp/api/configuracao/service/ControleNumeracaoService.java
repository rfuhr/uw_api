package br.com.ultraworks.erp.api.configuracao.service;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.repository.ControleNumeracaoRepository;

@Service
public class ControleNumeracaoService {

	private ControleNumeracaoRepository controleNumeracaoRepository;
	
	public ControleNumeracaoService(ControleNumeracaoRepository controleNumeracaoRepository) {
		this.controleNumeracaoRepository = controleNumeracaoRepository;
	}
	
	public int getProximoNumero(Long empresaFilialId, Long tipoDocumentoId, int serie) {
		return controleNumeracaoRepository.getProximoNumero(empresaFilialId, tipoDocumentoId, serie);
	}
}
