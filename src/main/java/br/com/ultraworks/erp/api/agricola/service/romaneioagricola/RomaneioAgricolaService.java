package br.com.ultraworks.erp.api.agricola.service.romaneioagricola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import br.com.ultraworks.erp.api.agricola.mapper.RomaneioAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaRepository;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RomaneioAgricolaService extends GenericService<RomaneioAgricola, Long, RomaneioAgricolaDTO> {

	private ConfigSistemaService configSistemaService;
	private ControleNumeracaoService controleNumeracaoService;
	private RomaneioAgricolaClassificacaoService romaneioAgricolaClassificacaoService;
	private RomaneioAgricolaNotaService romaneioAgricolaNotaService;
	private RomaneioAgricolaParcelaFixacaoService romaneioAgricolaParcelaFixacaoService;

	@Autowired
	public RomaneioAgricolaService(RomaneioAgricolaRepository repository, RomaneioAgricolaMapper mapper,
			ConfigSistemaService configSistemaService, ControleNumeracaoService controleNumeracaoService,
			RomaneioAgricolaClassificacaoService romaneioAgricolaClassificacaoService,
			RomaneioAgricolaNotaService romaneioAgricolaNotaService,
			RomaneioAgricolaParcelaFixacaoService romaneioAgricolaParcelaFixacaoService) {
		super(repository, mapper);
		this.configSistemaService = configSistemaService;
		this.controleNumeracaoService = controleNumeracaoService;
		this.romaneioAgricolaClassificacaoService = romaneioAgricolaClassificacaoService;
		this.romaneioAgricolaNotaService = romaneioAgricolaNotaService;
		this.romaneioAgricolaParcelaFixacaoService = romaneioAgricolaParcelaFixacaoService;

	}

	@Override
	public Optional<RomaneioAgricola> getById(Long id) {
		Optional<RomaneioAgricola> romaneio = repository.findById(id);
		if (romaneio.isPresent()) {
			romaneio.get().setClassificacoes(new ArrayList<>());
			romaneio.get().getClassificacoes()
					.addAll(romaneioAgricolaClassificacaoService.getAllByRomaneioAgricola(romaneio.get().getId()));

			romaneio.get().setNotas(new ArrayList<>());
			romaneio.get().getNotas()
					.addAll(romaneioAgricolaNotaService.getAllByRomaneioAgricola(romaneio.get().getId()));

			romaneio.get().setParcelas(new ArrayList<>());
			romaneio.get().getParcelas()
					.addAll(romaneioAgricolaParcelaFixacaoService.getAllByRomaneioAgricola(romaneio.get().getId()));
		}
		return romaneio;
	}

	@Override
	public RomaneioAgricola save(RomaneioAgricola romaneio) {
		if (romaneio.getId() == null && romaneio.getSituacao().equals(SituacaoRomaneio.FINALIZADO)) {
			throw new BusinessException("Não é possível criar romaneio com situação Finalizado");
		}
		RomaneioAgricola romaneioAgricolaSalvo = getById(romaneio.getId()).get();
		if (!romaneioAgricolaSalvo.getSituacao().equals(SituacaoRomaneio.FINALIZADO)
				&& romaneio.getSituacao().equals(SituacaoRomaneio.FINALIZADO)) {
			throw new BusinessException("Para mudar romaneio para situação Finalizado, utilize rotina específica");
		}

		if ((romaneio.getNumero() == null || romaneio.getNumero().equals(0))
				&& romaneio.getSituacao().equals(SituacaoRomaneio.PENDENTE)) {

			ConfigSistema configSistema = configSistemaService.getById(1L).get();
			TipoDocumento tipoDocumento = configSistema.getConfiguracoesAgricola().get(0).getTipoDocumentoRomaneio();
			if (tipoDocumento == null) {
				throw new BusinessException("Tipo de Documento de romaneio não configurado para controle de numeração");
			}
			int proximoNumero = controleNumeracaoService.getProximoNumero(
					romaneio.getDepartamento().getEmpresaFilial().getEmpresa().getId(), null, tipoDocumento.getId(), 0);

			romaneio.setNumero((long) proximoNumero);
		}
		if (romaneio.getSituacao().equals(SituacaoRomaneio.PENDENTE)) {
			romaneio.setSituacao(SituacaoRomaneio.ABERTO);
		}

		validarDadosRomaneioAgricola(romaneio);

		RomaneioAgricola romaneioSaved = repository.save(romaneio);

		if (romaneio.getClassificacoes() != null)
			romaneio.getClassificacoes().forEach(clas -> clas.setRomaneioAgricola(romaneioSaved));
		romaneioAgricolaClassificacaoService.persistList(romaneioSaved.getId(), romaneioSaved.getClassificacoes());

		if (romaneio.getNotas() != null)
			romaneio.getNotas().forEach(nota -> nota.setRomaneioAgricola(romaneioSaved));
		romaneioAgricolaNotaService.persistList(romaneioSaved.getId(), romaneioSaved.getNotas());

		if (romaneio.getParcelas() != null)
			romaneio.getParcelas().forEach(nota -> nota.setRomaneioAgricola(romaneioSaved));
		romaneioAgricolaParcelaFixacaoService.persistList(romaneioSaved.getId(), romaneioSaved.getParcelas());

		return getById(romaneioSaved.getId()).get();
	}

	public RomaneioAgricola salvarFinalizacao(RomaneioAgricola romaneio) {
		RomaneioAgricola romaneioSaved = repository.save(romaneio);
		return getById(romaneioSaved.getId()).get();
	}

	public void validarDadosRomaneioAgricola(RomaneioAgricola romaneioAgricola) {
		validacaoGeralRomaneio(romaneioAgricola);
		validacaoRomaneioFechado(romaneioAgricola);
	}

	private void validacaoGeralRomaneio(RomaneioAgricola romaneio) {
		if (romaneio.getNumero() == null) {
			throw new BusinessException("Número do romaneio deve ser informada");
		}
		if (romaneio.getDataDocumento() == null) {
			throw new BusinessException("Data do romaneio deve ser informada");
		}
		if (!romaneio.getDataDocumento().isEqual(LocalDate.now())) {
			throw new BusinessException("Sem autonomia para informar data de romaneio diferente da data atual");
		}
		if (romaneio.getDataDocumento().isAfter(LocalDate.now())) {
			throw new BusinessException("Data do romaneio não pode ser maior que a data atual");
		}
		if (romaneio.getDepartamento() == null) {
			throw new BusinessException("Departamento da Filial deve ser informada");
		}
		if (romaneio.getOrigem() == null) {
			throw new BusinessException("Origem do romaneio deve ser informada");
		}
		if (romaneio.getOrigem().equals(OrigemRomaneio.LANCAMENTO) && romaneio.getBalanca() == null) {
			throw new BusinessException("Deve ser informado balança para romaneio com origem de lançamento");
		}
		if (romaneio.getSituacao() == null) {
			throw new BusinessException("Situação do romaneio deve ser informada");
		}
		if (romaneio.getParceiroLocal() == null) {
			throw new BusinessException("Parceiro deve ser informada");
		}
		if (romaneio.getParceiroLocalPropriedade() == null) {
			throw new BusinessException("Propriedade deve ser informada");
		}
		if (romaneio.getRegraAtividade() == null) {
			throw new BusinessException("Regra de atividade deve ser informada");
		}
		if (romaneio.getGrupoOperacaoAgricola() == null) {
			throw new BusinessException("Grupo de Operação Agrícola deve ser informada");
		}

		if (romaneio.getOperacaoInterna() == null) {
			throw new BusinessException("Operação interna deve ser informada");
		}
		if (romaneio.getOrigemProducaoAgricola() == null) {
			throw new BusinessException("Origem de produção deve ser informada");
		}

		if (romaneio.getItem() == null) {
			throw new BusinessException("Produto deve ser informado");
		}
		if (romaneio.getItem().isInformaSafraAgricola() && romaneio.getSafra() == null) {
			throw new BusinessException("Safra agrícola deve ser informada");
		}
		if (!romaneio.getItem().isInformaSafraAgricola() && romaneio.getSafra() != null) {
			throw new BusinessException("Safra agrícola não deve ser informada");
		}

	}

	private void validacaoRomaneioFechado(RomaneioAgricola romaneio) {
		if (romaneio.getSituacao().equals(SituacaoRomaneio.FINALIZADO)) {
			validacaoPesosRomaneio(romaneio);
			validacaoDadosTransporte(romaneio);
		}
	}

	private void validacaoDadosTransporte(RomaneioAgricola romaneio) {
		if (StringUtils.isBlank(romaneio.getPlaca1())) {
			throw new BusinessException("Placa deve ser informada");
		}
		if (StringUtils.isBlank(romaneio.getNomeMotorista())) {
			throw new BusinessException("Nome do motorista deve ser informado");
		}
		if (StringUtils.isBlank(romaneio.getContatoMotorista())) {
			throw new BusinessException("Contato do motorista deve ser informado");
		}
	}

	private void validacaoPesosRomaneio(RomaneioAgricola romaneio) {
		IndicadorOperacao indicadorOperacao = romaneio.getOperacaoInterna().getNaturezaOperacao()
				.getIndicadorOperacao();
		if (romaneio.getPesoEntrada() == null) {
			throw new BusinessException("Peso de entrada deve ser informado");
		}
		if (romaneio.getPesoSaida() == null) {
			throw new BusinessException("Peso de saída deve ser informado");
		}
		if (romaneio.getPesoLiquido() == null) {
			throw new BusinessException("Peso líquido deve ser informado");
		}
		BigDecimal pesoLiquidoCalculado = BigDecimal.ZERO;
		if (indicadorOperacao.equals(IndicadorOperacao.ENTRADA)) {
			if (romaneio.getPesoSaida().compareTo(romaneio.getPesoEntrada()) >= 0) {
				throw new BusinessException("Peso de entrada deve ser maior que o peso de saída");
			}
			pesoLiquidoCalculado = romaneio.getPesoEntrada().subtract(romaneio.getPesoSaida());
		} else {
			if (romaneio.getPesoEntrada().compareTo(romaneio.getPesoSaida()) >= 0) {
				throw new BusinessException("Peso de saída deve ser maior que o peso de entrada");
			}
			pesoLiquidoCalculado = romaneio.getPesoSaida().subtract(romaneio.getPesoEntrada());
		}
		if (!pesoLiquidoCalculado.equals(romaneio.getPesoLiquido())) {
			throw new BusinessException("Peso líquido informado difere do peso calculado");
		}
		if (romaneio.getDescontosAcrescimo() == null) {
			throw new BusinessException("Quantidade de desconto ou acréscimo deve ser informado");
		}
		if (romaneio.getPesoFinal() == null) {
			throw new BusinessException("Peso final deve ser informado");
		}
		BigDecimal pesoFinalCalculado = romaneio.getPesoLiquido().add(romaneio.getDescontosAcrescimo());
		if (!pesoFinalCalculado.equals(romaneio.getPesoFinal())) {
			throw new BusinessException("Peso final informado difere do peso calculado");
		}

		if (romaneio.isPesoEntradaManual()) {
			if (StringUtils.isBlank(romaneio.getJustificaticaPesoEntrada())) {
				throw new BusinessException("Para pesagem de entrada manual, a justificativa deve ser informada");
			}
		}
		if (romaneio.isPesoSaidaManual()) {
			if (StringUtils.isBlank(romaneio.getJustificaticaPesoSaida())) {
				throw new BusinessException("Para pesagem de saída manual, a justificativa deve ser informada");
			}
		}
	}

	public void cancelarRomaneio(Long id) {
		RomaneioAgricola romaneio = getById(id)
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado romaneio agrícola com id " + id));
		if (romaneio.getSituacao().equals(SituacaoRomaneio.CANCELADO)) {
			throw new BusinessException("Romaneio agrícola já está cancelado.");
		}
		romaneio.setSituacao(SituacaoRomaneio.CANCELADO);
		repository.save(romaneio);
	}

	public List<Long> buscarIdsRomaneiosParaFixacao(Long empresaFilialId, Long departamentoId, Long itemId) {
		return ((RomaneioAgricolaRepository) repository).buscarIdsRomaneiosParaFixacao(empresaFilialId, departamentoId, itemId);
	}
}