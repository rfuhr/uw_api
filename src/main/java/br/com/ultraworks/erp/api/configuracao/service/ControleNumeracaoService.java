package br.com.ultraworks.erp.api.configuracao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultraworks.erp.api.configuracao.repository.ControleNumeracaoRepository;

@Service
public class ControleNumeracaoService {

	private ControleNumeracaoRepository controleNumeracaoRepository;

	public ControleNumeracaoService(ControleNumeracaoRepository controleNumeracaoRepository) {
		this.controleNumeracaoRepository = controleNumeracaoRepository;
	}

	@Transactional
	public int getProximoNumero(Long empresaId, Long empresaFilialId, Long tipoDocumentoId, int serie) {
		return controleNumeracaoRepository.getProximoNumero(empresaId, empresaFilialId, tipoDocumentoId, serie);
	}
}
