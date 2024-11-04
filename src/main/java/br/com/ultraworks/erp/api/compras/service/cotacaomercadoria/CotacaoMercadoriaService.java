package br.com.ultraworks.erp.api.compras.service.cotacaomercadoria;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.compras.service.ConfigAutorizacaoSolicitacaoMercadoriaService;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CotacaoMercadoriaService extends GenericService<CotacaoMercadoria, Long, CotacaoMercadoriaDTO> {

	private CotacaoMercadoriaParceiroService cotacaoMercadoriaParceiroService;
	private ConfigSistemaService configSistemaService;
	private ControleNumeracaoService controleNumeracaoService;

	@Autowired
	public CotacaoMercadoriaService(CotacaoMercadoriaRepository repository, CotacaoMercadoriaMapper mapper,
			CotacaoMercadoriaParceiroService cotacaoMercadoriaParceiroService,
			ConfigSistemaService configSistemaService, ControleNumeracaoService controleNumeracaoService,
			ConfigAutorizacaoSolicitacaoMercadoriaService configAutorizacaoSolicitacaoMercadoriaService) {
		super(repository, mapper);
		this.cotacaoMercadoriaParceiroService = cotacaoMercadoriaParceiroService;
		this.configSistemaService = configSistemaService;
		this.controleNumeracaoService = controleNumeracaoService;
	}

	@Override
	public Optional<CotacaoMercadoria> getById(Long id) {
		Optional<CotacaoMercadoria> cotacao = repository.findById(id);
		if (cotacao.isPresent()) {
			cotacao.get().setParceiros(new ArrayList<>());
			cotacao.get().getParceiros()
					.addAll(cotacaoMercadoriaParceiroService.getAllByCotacaoMercadoria(cotacao.get().getId()));
		}
		return cotacao;
	}

	@Override
	public CotacaoMercadoria save(CotacaoMercadoria cotacao) {
		if ((cotacao.getNumero() == null || cotacao.getNumero().equals(0L))) {

			ConfigSistema configSistema = configSistemaService.getById(1L).get();
			TipoDocumento tipoDocumento = configSistema.getConfiguracoesCompra().get(0).getTipoDocumentoCotacao();
			if (tipoDocumento == null) {
				throw new BusinessException(
						"Tipo de Documento de cotação de mercadoria não configurado para controle de numeração");
			}
			int proximoNumero = controleNumeracaoService.getProximoNumero(
					cotacao.getDepartamentoCotacao().getEmpresaFilial().getEmpresa().getId(), null,
					tipoDocumento.getId(), 0);

			cotacao.setNumero((long) proximoNumero);
		}

		ConfigAutorizacaoSolicitacaoMercadoria configAutorizacao = null;

		CotacaoMercadoria cotacaoSaved = repository.save(cotacao);

		if (cotacao.getParceiros() != null)
			cotacao.getParceiros().forEach(cot -> cot.setCotacaoMercadoria(cotacaoSaved));
		cotacaoMercadoriaParceiroService.criarCotacaoParceiros(cotacaoSaved.getId(), cotacaoSaved.getParceiros());

		return getById(cotacaoSaved.getId()).get();
	}

}