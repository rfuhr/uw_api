package br.com.ultraworks.erp.api.compras.service.cotacaomercadoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoria.SituacaoCotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.statuscotacaomercadoriaitem.StatusCotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.compras.repository.query.BuscarCotacoesParaRetornoQuery;
import br.com.ultraworks.erp.api.compras.service.ConfigAutorizacaoSolicitacaoMercadoriaService;
import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaItemService;
import br.com.ultraworks.erp.api.compras.vo.CotacaoMercadoriaParaRetornoVO;
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
	private SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService;
	private ConfigSistemaService configSistemaService;
	private ControleNumeracaoService controleNumeracaoService;
	private BuscarCotacoesParaRetornoQuery buscarCotacoesParaRetornoQuery;

	@Autowired
	public CotacaoMercadoriaService(CotacaoMercadoriaRepository repository, CotacaoMercadoriaMapper mapper,
			CotacaoMercadoriaParceiroService cotacaoMercadoriaParceiroService,
			SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService,
			ConfigSistemaService configSistemaService, ControleNumeracaoService controleNumeracaoService,
			ConfigAutorizacaoSolicitacaoMercadoriaService configAutorizacaoSolicitacaoMercadoriaService,
			BuscarCotacoesParaRetornoQuery buscarCotacoesParaRetornoQuery) {
		super(repository, mapper);
		this.cotacaoMercadoriaParceiroService = cotacaoMercadoriaParceiroService;
		this.solicitacaoMercadoriaItemService = solicitacaoMercadoriaItemService;
		this.configSistemaService = configSistemaService;
		this.controleNumeracaoService = controleNumeracaoService;
		this.buscarCotacoesParaRetornoQuery = buscarCotacoesParaRetornoQuery;
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
		List<CotacaoMercadoriaParceiro> parceirosDaCotacao = cotacao.getParceiros();
		if (cotacao.getId() == null
				&& (!cotacao.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.EM_DIGITACAO)
						&& !cotacao.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.DIGITADA))) {
			throw new BusinessException("Não é possível criar ou alterar cotação com situação informada");
		}
		if (cotacao.getId() != null) {
			CotacaoMercadoria cotacaoMercadoriaSalvo = repository.findById(cotacao.getId()).get();
			if (!cotacaoMercadoriaSalvo.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.EM_DIGITACAO)
					&& !cotacaoMercadoriaSalvo.getSituacaoCotacaoMercadoria()
							.equals(SituacaoCotacaoMercadoria.DIGITADA)) {
				throw new BusinessException("Para mudar status da cotação, utilize rotina específica");
			}
		}
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
		if (cotacao.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.DIGITADA))
			cotacao.setSituacaoCotacaoMercadoria(SituacaoCotacaoMercadoria.AGUARDANDO_RETORNO);

		CotacaoMercadoria cotacaoSaved = repository.save(cotacao);

		if (parceirosDaCotacao != null)
			parceirosDaCotacao.forEach(parc -> {
				parc.setCotacaoMercadoria(cotacaoSaved);
				if (cotacaoSaved.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.EM_DIGITACAO)) {
					parc.setSituacao(SituacaoCotacaoMercadoriaParceiro.EM_DIGITACAO);
				} else if (cotacaoSaved.getSituacaoCotacaoMercadoria().equals(SituacaoCotacaoMercadoria.DIGITADA)) {
					parc.setSituacao(SituacaoCotacaoMercadoriaParceiro.DIGITADA);
				} else if (cotacaoSaved.getSituacaoCotacaoMercadoria()
						.equals(SituacaoCotacaoMercadoria.AGUARDANDO_RETORNO)) {
					parc.setSituacao(SituacaoCotacaoMercadoriaParceiro.AGUARDANDO_RETORNO);
				}

				if (parc.getItens() != null)
					parc.getItens().forEach(it -> {
						if (cotacaoSaved.getSituacaoCotacaoMercadoria()
								.equals(SituacaoCotacaoMercadoria.EM_DIGITACAO)) {
							it.setStatus(StatusCotacaoMercadoriaItem.EM_DIGITACAO);
						} else if (cotacaoSaved.getSituacaoCotacaoMercadoria()
								.equals(SituacaoCotacaoMercadoria.DIGITADA)) {
							it.setStatus(StatusCotacaoMercadoriaItem.DIGITADA);
						} else if (cotacaoSaved.getSituacaoCotacaoMercadoria()
								.equals(SituacaoCotacaoMercadoria.AGUARDANDO_RETORNO)) {
							it.setStatus(StatusCotacaoMercadoriaItem.AGUARDANDO_RETORNO);
						}
					});
			});
		cotacaoMercadoriaParceiroService.persistList(cotacaoSaved.getId(), parceirosDaCotacao);

		CotacaoMercadoria cotacaoMercadoriaFinal = getById(cotacaoSaved.getId()).get();

		ajustaStatusDosItensDasSolicitacoesCotadas(cotacaoMercadoriaFinal);

		return cotacaoMercadoriaFinal;
	}

	private void ajustaStatusDosItensDasSolicitacoesCotadas(CotacaoMercadoria cotacaoMercadoriaFinal) {
		Set<SolicitacaoMercadoriaItem> itensDeSolicitacoes = cotacaoMercadoriaFinal.getParceiros().stream()
				.flatMap(parceiro -> parceiro.getItens().stream())
				.map(CotacaoMercadoriaItem::getSolicitacaoMercadoriaItem).collect(Collectors.toSet());

		solicitacaoMercadoriaItemService.cotar(new ArrayList<>(itensDeSolicitacoes));
	}

	public List<CotacaoMercadoriaParaRetornoVO> buscarCotacoesPendenteParaRetorno() {
		return buscarCotacoesParaRetornoQuery.executeSQL();
	}

	public Optional<CotacaoMercadoria> buscarCotacaoParaRetorno(Long cotacaoMercadoriaId,
			Long cotacaoMercadoriaParceiroId) {
		Optional<CotacaoMercadoria> optCotacao = this.getById(cotacaoMercadoriaId);
		if (optCotacao.isPresent()) {
			CotacaoMercadoria cotacao = optCotacao.get();
			cotacao.setParceiros(cotacao.getParceiros().stream()
					.filter(c -> c.getId().equals(cotacaoMercadoriaParceiroId)).collect(Collectors.toList()));
			if (cotacao.getParceiros().isEmpty()) {
				return Optional.empty();
			}
			CotacaoMercadoriaParceiro cotacaoMercadoriaParceiro = cotacao.getParceiros().get(0);
			if (cotacaoMercadoriaParceiro.getSituacao().equals(SituacaoCotacaoMercadoriaParceiro.AGUARDANDO_RETORNO)
					|| cotacaoMercadoriaParceiro.getSituacao()
							.equals(SituacaoCotacaoMercadoriaParceiro.RETORNO_PARCIAL)) {
				return optCotacao;
			}
			throw new BusinessException(
					"Cotação de nº " + cotacao.getNumero().toString() + " não está aguardando retorno");
		}
		return Optional.empty();
	}

}