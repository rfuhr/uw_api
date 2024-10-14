package br.com.ultraworks.erp.api.agricola.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import br.com.ultraworks.erp.api.agricola.repository.BalancaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.OrigemProducaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalPropriedadeRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RomaneioAgricolaMapper extends GenericMapper<RomaneioAgricola, RomaneioAgricolaDTO> {

	private DepartamentoRepository departamentoRepository;
	private ParceiroLocalRepository parceiroLocalRepository;
	private ParceiroLocalPropriedadeRepository parceiroLocalPropriedadeRepository;
	private ItemRepository itemRepository;
	private SafraRepository safraRepository;
	private GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository;
	private OperacaoInternaRepository operacaoInternaRepository;
	private OrigemProducaoAgricolaRepository origemProducaoAgricolaRepository;
	private RomaneioAgricolaClassificacaoMapper romaneioAgricolaClassificacaoMapper;
	private RomaneioAgricolaNotaMapper romaneioAgricolaNotaMapper;
	private RomaneioAgricolaParcelaFixacaoMapper romaneioAgricolaParcelaFixacaoMapper;
	private ContratoAgricolaRepository contratoAgricolaRepository;
	private BalancaRepository balancaRepository;
	private RegraAtividadeRepository regraAtividadeRepository;
	private SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;

	public RomaneioAgricolaMapper(RomaneioAgricolaRepository romaneioRepository,
			DepartamentoRepository departamentoRepository, ParceiroLocalRepository parceiroLocalRepository,
			ParceiroLocalPropriedadeRepository parceiroLocalPropriedadeRepository, ItemRepository itemRepository,
			SafraRepository safraRepository, GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository,
			OperacaoInternaRepository operacaoInternaRepository,
			OrigemProducaoAgricolaRepository origemProducaoAgricolaRepository,
			RomaneioAgricolaClassificacaoMapper romaneioAgricolaClassificacaoMapper,
			RomaneioAgricolaNotaMapper romaneioAgricolaNotaMapper,
			ContratoAgricolaRepository contratoAgricolaRepository, BalancaRepository balancaRepository,
			RegraAtividadeRepository regraAtividadeRepository,
			RomaneioAgricolaParcelaFixacaoMapper romaneioAgricolaParcelaFixacaoMapper,
			SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository,
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository) {
		super(romaneioRepository, RomaneioAgricola::new, RomaneioAgricolaDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.parceiroLocalPropriedadeRepository = parceiroLocalPropriedadeRepository;
		this.itemRepository = itemRepository;
		this.safraRepository = safraRepository;
		this.grupoOperacaoAgricolaRepository = grupoOperacaoAgricolaRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.origemProducaoAgricolaRepository = origemProducaoAgricolaRepository;
		this.romaneioAgricolaClassificacaoMapper = romaneioAgricolaClassificacaoMapper;
		this.romaneioAgricolaNotaMapper = romaneioAgricolaNotaMapper;
		this.contratoAgricolaRepository = contratoAgricolaRepository;
		this.balancaRepository = balancaRepository;
		this.regraAtividadeRepository = regraAtividadeRepository;
		this.romaneioAgricolaParcelaFixacaoMapper = romaneioAgricolaParcelaFixacaoMapper;
		this.subItemClassificacaoAgricolaRepository = subItemClassificacaoAgricolaRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(RomaneioAgricolaDTO dto, RomaneioAgricola entity) {
		entity.setId(dto.getId());
		entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento da filial com id " + dto.getDepartamentoId())));
		entity.setOrigem(OrigemRomaneio.fromValue(dto.getOrigem()));
		entity.setNumero(dto.getNumero());
		entity.setDataDocumento(dto.getDataDocumento());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		if (dto.getParceiroLocalPropriedadeId() != null) {
			entity.setParceiroLocalPropriedade(parceiroLocalPropriedadeRepository
					.findById(dto.getParceiroLocalPropriedadeId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado propriedade de parceiro com id " + dto.getParceiroLocalPropriedadeId())));
		}
		if (dto.getRegraAtividadeId() != null) {
			entity.setRegraAtividade(regraAtividadeRepository.findById(dto.getRegraAtividadeId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado regra de atividade com id " + dto.getRegraAtividadeId())));
		}
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getSafraId() != null) {
			entity.setSafra(safraRepository.findById(dto.getSafraId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado safra com id " + dto.getSafraId())));
		}
		if (dto.getGrupoOperacaoAgricolaId() != null) {
			entity.setGrupoOperacaoAgricola(grupoOperacaoAgricolaRepository.findById(dto.getGrupoOperacaoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo de operação com id " + dto.getGrupoOperacaoAgricolaId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getOrigemProducaoAgricolaId() != null) {
			entity.setOrigemProducaoAgricola(origemProducaoAgricolaRepository
					.findById(dto.getOrigemProducaoAgricolaId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado origem de produção com id " + dto.getOrigemProducaoAgricolaId())));
		}
		if (dto.getBalancaId() != null) {
			entity.setBalanca(balancaRepository.findById(dto.getBalancaId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado balança com id " + dto.getBalancaId())));
		}
		entity.setPesoEntrada(dto.getPesoEntrada());
		entity.setPesoSaida(dto.getPesoSaida());
		entity.setPesoLiquido(dto.getPesoLiquido());
		entity.setDescontosAcrescimo(dto.getDescontosAcrescimo());
		entity.setPesoFinal(dto.getPesoFinal());
		entity.setPesoEntradaManual(dto.isPesoEntradaManual());
		entity.setJustificaticaPesoEntrada(dto.getJustificativaPesoEntrada());
		entity.setPesoSaidaManual(dto.isPesoSaidaManual());
		entity.setJustificaticaPesoSaida(dto.getJustificativaPesoSaida());
		entity.setPlaca1(dto.getPlaca1());
		entity.setPlaca2(dto.getPlaca2());
		entity.setPlaca3(dto.getPlaca3());
		entity.setNomeMotorista(dto.getNomeMotorista());
		entity.setContatoMotorista(dto.getContatoMotorista());
		entity.setEntrada(dto.isEntrada());
		entity.setFixarAutomatico(dto.isFixarAutomatico());
		entity.setObservacao(dto.getObservacao());
		entity.setSituacao(SituacaoRomaneio.fromValue(dto.getSituacao()));
		if (dto.getContratoAgricolaId() != null) {
			entity.setContratoAgricola(contratoAgricolaRepository.findById(dto.getContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado contrato agrícola com id " + dto.getContratoAgricolaId())));
		}
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}

		if (dto.getNivel1PredPrecoId() != null) {
			entity.setNivel1PredPreco(subItemClassificacaoAgricolaRepository.findById(dto.getNivel1PredPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 1 de sub. item de classificação com id "
									+ dto.getNivel1PredPrecoId())));
		}
		if (dto.getNivel2PredPrecoId() != null) {
			entity.setNivel2PredPreco(subItemClassificacaoAgricolaRepository.findById(dto.getNivel2PredPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 2 de sub. item de classificação com id "
									+ dto.getNivel2PredPrecoId())));
		}
		if (dto.getNivel3PredPrecoId() != null) {
			entity.setNivel3PredPreco(subItemClassificacaoAgricolaRepository.findById(dto.getNivel3PredPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 3 de sub. item de classificação com id "
									+ dto.getNivel3PredPrecoId())));
		}
		if (dto.getNivel4PredPrecoId() != null) {
			entity.setNivel4PredPreco(subItemClassificacaoAgricolaRepository.findById(dto.getNivel4PredPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 4 de sub. item de classificação com id "
									+ dto.getNivel4PredPrecoId())));
		}
		entity.setPrecoDeposito(dto.getPrecoDeposito());
		entity.setPesoFinalFaturar(dto.getPesoFinalFaturar());
		entity.setSaldoFixar(dto.getSaldoFixar());
		entity.setControle(dto.getControle());
		entity.setControleDv(dto.getControleDv());
		entity.setNumeroNfDeposito(dto.getNumeroNfDeposito());	
		
		if (dto.getClassificacoes() != null && dto.getClassificacoes().size() > 0) {
			entity.setClassificacoes(new ArrayList<>());
			entity.getClassificacoes().addAll(romaneioAgricolaClassificacaoMapper.toEntity(dto.getClassificacoes()));
		}

		if (dto.getNotas() != null && dto.getNotas().size() > 0) {
			entity.setNotas(new ArrayList<>());
			entity.getNotas().addAll(romaneioAgricolaNotaMapper.toEntity(dto.getNotas()));
		}

		if (dto.getParcelas() != null && dto.getParcelas().size() > 0) {
			entity.setParcelas(new ArrayList<>());
			entity.getParcelas().addAll(romaneioAgricolaParcelaFixacaoMapper.toEntity(dto.getParcelas()));
		}

	}

	@Override
	protected void setValuesToDto(RomaneioAgricola entity, RomaneioAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setOrigem(entity.getOrigem().getValue());
		dto.setNumero(entity.getNumero());
		dto.setDataDocumento(entity.getDataDocumento());
		if (entity.getParceiroLocal() != null) {
			dto.setParceiroLocalId(entity.getParceiroLocal().getId());
			dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
			dto.setParceiroNome(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		}
		if (entity.getParceiroLocalPropriedade() != null) {
			dto.setParceiroLocalPropriedadeId(entity.getParceiroLocalPropriedade().getId());
			dto.setParceiroLocalPropriedadeNome(entity.getParceiroLocalPropriedade().getIdentificacao());
		}
		dto.setItemId(entity.getItem().getId());
		if (entity.getSafra() != null) {
			dto.setSafraId(entity.getSafra().getId());
			dto.setSafraNome(entity.getSafra().getNome());
		}
		dto.setGrupoOperacaoAgricolaId(entity.getGrupoOperacaoAgricola().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOrigemProducaoAgricolaId(entity.getOrigemProducaoAgricola().getId());
		if (entity.getBalanca() != null) {
			dto.setBalancaId(entity.getBalanca().getId());
			dto.setBalancaNome(entity.getBalanca().getNome());
		}
		dto.setPesoEntrada(entity.getPesoEntrada());
		dto.setPesoSaida(entity.getPesoSaida());
		dto.setPesoLiquido(entity.getPesoLiquido());
		dto.setDescontosAcrescimo(entity.getDescontosAcrescimo());
		dto.setPesoFinal(entity.getPesoFinal());
		dto.setPesoEntradaManual(entity.isPesoEntradaManual());
		dto.setJustificativaPesoEntrada(entity.getJustificaticaPesoEntrada());
		dto.setPesoSaidaManual(entity.isPesoSaidaManual());
		dto.setJustificativaPesoSaida(entity.getJustificaticaPesoSaida());
		dto.setPlaca1(entity.getPlaca1());
		dto.setPlaca2(entity.getPlaca2());
		dto.setPlaca3(entity.getPlaca3());
		dto.setNomeMotorista(entity.getNomeMotorista());
		dto.setContatoMotorista(entity.getContatoMotorista());
		dto.setObservacao(entity.getObservacao());
		dto.setEntrada(entity.isEntrada());
		dto.setFixarAutomatico(entity.isFixarAutomatico());
		dto.setSituacao(entity.getSituacao().getValue());
		if (entity.getContratoAgricola() != null) {
			dto.setContratoAgricolaId(entity.getContratoAgricola().getId());
		}
		if (entity.getTipoPrecoAgricola() != null) {
			dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
		}
		if (entity.getNivel1PredPreco() != null) {
			dto.setNivel1PredPrecoId(entity.getNivel1PredPreco().getId());
		}
		if (entity.getNivel2PredPreco() != null) {
			dto.setNivel2PredPrecoId(entity.getNivel2PredPreco().getId());
		}
		if (entity.getNivel3PredPreco() != null) {
			dto.setNivel3PredPrecoId(entity.getNivel3PredPreco().getId());
		}
		if (entity.getNivel4PredPreco() != null) {
			dto.setNivel4PredPrecoId(entity.getNivel4PredPreco().getId());
		}
		dto.setPrecoDeposito(entity.getPrecoDeposito());
		dto.setPesoFinalFaturar(entity.getPesoFinalFaturar());
		dto.setSaldoFixar(entity.getSaldoFixar());		
		dto.setControle(entity.getControle());
		dto.setControleDv(entity.getControleDv());
		dto.setNumeroNfDeposito(entity.getNumeroNfDeposito());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setOrigemNome(entity.getOrigem().getName());
		dto.setItemNome(entity.getItem().getNome());
		dto.setGrupoOperacaoAgricolaNome(entity.getGrupoOperacaoAgricola().getNome());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setOrigemProducaoAgricolaNome(entity.getOrigemProducaoAgricola().getNome());
		dto.setSituacaoNome(entity.getSituacao().getName());

		dto.setClassificacoes(new ArrayList<>());
		if (entity.getClassificacoes() != null) {
			dto.getClassificacoes().addAll(romaneioAgricolaClassificacaoMapper.toDto(entity.getClassificacoes()));
		}

		dto.setNotas(new ArrayList<>());
		if (entity.getNotas() != null) {
			dto.getNotas().addAll(romaneioAgricolaNotaMapper.toDto(entity.getNotas()));
		}

		dto.setParcelas(new ArrayList<>());
		if (entity.getParcelas() != null) {
			dto.getParcelas().addAll(romaneioAgricolaParcelaFixacaoMapper.toDto(entity.getParcelas()));
		}
	}
}
