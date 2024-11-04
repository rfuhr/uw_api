package br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.mapper.SolicitacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.compras.repository.query.BuscarSolicitacoesPendentesParaCotacaoQuery;
import br.com.ultraworks.erp.api.compras.service.ConfigAutorizacaoSolicitacaoMercadoriaService;
import br.com.ultraworks.erp.api.compras.vo.SolicitacaoMercadoriaParaCotacaoVO;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.Autorizacao;
import br.com.ultraworks.erp.api.tabela.domain.statusautorizacao.StatusAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.service.AutorizacaoService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SolicitacaoMercadoriaService
		extends GenericService<SolicitacaoMercadoria, Long, SolicitacaoMercadoriaDTO> {

	private SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService;
	private ConfigSistemaService configSistemaService;
	private ControleNumeracaoService controleNumeracaoService;
	private ConfigAutorizacaoSolicitacaoMercadoriaService configAutorizacaoSolicitacaoMercadoriaService;
	private UsuarioService usuarioService;
	private AutorizacaoService autorizacaoService;
	private BuscarSolicitacoesPendentesParaCotacaoQuery buscarSolicitacoesPendentesParaCotacaoQuery;

	@Autowired
	public SolicitacaoMercadoriaService(SolicitacaoMercadoriaRepository repository, SolicitacaoMercadoriaMapper mapper,
			SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService,
			ConfigSistemaService configSistemaService, ControleNumeracaoService controleNumeracaoService,
			ConfigAutorizacaoSolicitacaoMercadoriaService configAutorizacaoSolicitacaoMercadoriaService,
			UsuarioService usuarioService, @Lazy AutorizacaoService autorizacaoService,
			BuscarSolicitacoesPendentesParaCotacaoQuery buscarSolicitacoesPendentesParaCotacaoQuery) {
		super(repository, mapper);
		this.solicitacaoMercadoriaItemService = solicitacaoMercadoriaItemService;
		this.configSistemaService = configSistemaService;
		this.controleNumeracaoService = controleNumeracaoService;
		this.configAutorizacaoSolicitacaoMercadoriaService = configAutorizacaoSolicitacaoMercadoriaService;
		this.usuarioService = usuarioService;
		this.autorizacaoService = autorizacaoService;
		this.buscarSolicitacoesPendentesParaCotacaoQuery = buscarSolicitacoesPendentesParaCotacaoQuery;
	}

	@Override
	public Optional<SolicitacaoMercadoria> getById(Long id) {
		Optional<SolicitacaoMercadoria> solicitacao = repository.findById(id);
		if (solicitacao.isPresent()) {
			solicitacao.get().setItens(new ArrayList<>());
			solicitacao.get().getItens()
					.addAll(solicitacaoMercadoriaItemService.getAllBySolicitacaoMercadoria(solicitacao.get().getId()));
		}
		return solicitacao;
	}

	@Override
	public SolicitacaoMercadoria save(SolicitacaoMercadoria solicitacao) {
		if (solicitacao.getId() == null && (!solicitacao.getSituacaoSolicitacaoMercadoria()
				.equals(SituacaoSolicitacaoMercadoria.EM_DIGITACAO)
				&& !solicitacao.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.DIGITADA))) {
			throw new BusinessException("Não é possível criar ou alterar solicitação com situação informada");
		}
		if (solicitacao.getId() != null) {
			SolicitacaoMercadoria solicitacaoMercadoriaSalvo = getById(solicitacao.getId()).get();
			if (!solicitacaoMercadoriaSalvo.getSituacaoSolicitacaoMercadoria()
					.equals(SituacaoSolicitacaoMercadoria.EM_DIGITACAO)
					&& !solicitacaoMercadoriaSalvo.getSituacaoSolicitacaoMercadoria()
							.equals(SituacaoSolicitacaoMercadoria.DIGITADA)) {
				throw new BusinessException("Para mudar status da solicitação, utilize rotina específica");
			}
		}

		if ((solicitacao.getNumero() == null || solicitacao.getNumero().equals(0L))) {

			ConfigSistema configSistema = configSistemaService.getById(1L).get();
			TipoDocumento tipoDocumento = configSistema.getConfiguracoesCompra().get(0).getTipoDocumentoSolicitacao();
			if (tipoDocumento == null) {
				throw new BusinessException(
						"Tipo de Documento de solicitação de mercadoria não configurado para controle de numeração");
			}
			int proximoNumero = controleNumeracaoService.getProximoNumero(
					solicitacao.getDepartamentoSolicitante().getEmpresaFilial().getEmpresa().getId(), null,
					tipoDocumento.getId(), 0);

			solicitacao.setNumero((long) proximoNumero);
		}

		ConfigAutorizacaoSolicitacaoMercadoria configAutorizacao = null;

		if (solicitacao.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.DIGITADA)) {

			configAutorizacao = configAutorizacaoSolicitacaoMercadoriaService.buscaConfiguracao(
					solicitacao.getDepartamentoSolicitante().getEmpresaFilial().getEmpresa().getId(),
					solicitacao.getDepartamentoSolicitante().getEmpresaFilial().getId(),
					solicitacao.getDepartamentoSolicitante().getId(), solicitacao.getGrupoContabilDestino().getId());

			if (configAutorizacao != null) {
				solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.AGUARDANDO_AUTORIZACAO);
			} else {
				solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.AUTORIZADA);
			}
		}
		SolicitacaoMercadoria solicitacaoSaved = repository.save(solicitacao);

		if (solicitacao.getItens() != null)
			solicitacao.getItens().forEach(sol -> {
				sol.setSolicitacaoMercadoria(solicitacaoSaved);
				if (solicitacaoSaved.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.EM_DIGITACAO)) {
					sol.setStatus(StatusSolicitacaoMercadoriaItem.EM_DIGITACAO);
				} else if (solicitacaoSaved.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.DIGITADA)) {
					sol.setStatus(StatusSolicitacaoMercadoriaItem.DIGITADA);
				} else if (solicitacaoSaved.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.AGUARDANDO_AUTORIZACAO)) {
					sol.setStatus(StatusSolicitacaoMercadoriaItem.AGUARDANDO_AUTORIZACAO);
				} else if (solicitacaoSaved.getSituacaoSolicitacaoMercadoria().equals(SituacaoSolicitacaoMercadoria.AUTORIZADA)) {
					sol.setStatus(StatusSolicitacaoMercadoriaItem.AUTORIZADA);
				} 
			});

		solicitacaoMercadoriaItemService.criarItens(solicitacaoSaved.getId(), solicitacaoSaved.getItens());

		if (configAutorizacao != null) {
			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario usuarioSolicitante = usuarioService.findByUserId(user.getId()).get();

			Arrays.stream(configAutorizacao.getAutorizadoresId().split(",")).map(Long::parseLong)
					.collect(Collectors.toList()).forEach(aut -> {
						Usuario usuarioAutorizador = usuarioService.getById(aut).get();

						Autorizacao autorizacao = Autorizacao.builder().documentoOrigemId(solicitacaoSaved.getId())
								.documentoIdentificacao(String.valueOf(solicitacaoSaved.getNumero()))
								.tipoAutorizacao(TipoAutorizacao.SOLICITACAO_MERCADORIA)
								.statusAutorizacao(StatusAutorizacao.AGUARDANDO_AUTORIZACAO)
								.usuarioSolicitante(usuarioSolicitante).usuarioAutorizador(usuarioAutorizador)
								.dataSolicitacao(solicitacaoSaved.getDataSolicitacao()).build();

						autorizacaoService.save(autorizacao);

					});

		}

		return getById(solicitacaoSaved.getId()).get();
	}

	@Override
	public void delete(Long id) {
		SolicitacaoMercadoria solicitacao = this.getById(id).orElseThrow(RegisterNotFoundException::new);
		solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.CANCELADA);
		repository.save(solicitacao);
	}

	public void autorizar(Long id, Usuario usuarioAutorizador) {
		SolicitacaoMercadoria solicitacao = this.getById(id).orElseThrow(RegisterNotFoundException::new);
		solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.AUTORIZADA);
		repository.save(solicitacao);
		solicitacaoMercadoriaItemService.autorizar(solicitacao.getItens());
	}

	public void negar(Long id, Usuario usuarioAutorizador) {
		SolicitacaoMercadoria solicitacao = this.getById(id).orElseThrow(RegisterNotFoundException::new);
		solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.NAO_AUTORIZADA);
		repository.save(solicitacao);
		solicitacaoMercadoriaItemService.negar(solicitacao.getItens());
	}

	public List<SolicitacaoMercadoriaParaCotacaoVO> buscarSolicitacoesPendentesParaCotacao(
			Long departamentoSolicitadoId) {
		return buscarSolicitacoesPendentesParaCotacaoQuery.executeSQL(departamentoSolicitadoId);
	}

	@Transactional
	public void atualizarStatusSolicitacao(Long solicitacaoMercadoriaId) {
		SolicitacaoMercadoria solicitacao = this.getById(solicitacaoMercadoriaId)
				.orElseThrow(RegisterNotFoundException::new);
		solicitacao.setSituacaoSolicitacaoMercadoria(SituacaoSolicitacaoMercadoria.ABERTA);
		repository.save(solicitacao);
	}

	@Transactional
	public void atualizarStatusSolicitacoes(List<Long> solicitacaoMercadoriaIds) {
		solicitacaoMercadoriaIds.forEach(id -> atualizarStatusSolicitacao(id));

	}

}