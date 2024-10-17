package br.com.ultraworks.erp.api.agricola.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.FinalidadeContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.financeiro.repository.IndiceFinanceiroRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContratoAgricolaMapper extends GenericMapper<ContratoAgricola, ContratoAgricolaDTO> {

	private DepartamentoRepository departamentoRepository;
	private ItemRepository itemRepository;
	private ParceiroLocalRepository parceiroLocalRepository;
	private ParceiroRepository parceiroRepository;
	private IndiceFinanceiroRepository indiceFinanceiroRepository;
	private TipoContratoAgricolaRepository tipoContratoAgricolaRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;
	private OperacaoInternaRepository operacaoInternaRepository;
	private GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository;
	private FinalidadeContratoAgricolaRepository finalidadeContratoAgricolaRepository;
	private PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository;
	private SafraRepository safraRepository;
	private RegraAtividadeRepository regraAtividadeRepository;
	private ContratoAgricolaParcelaMapper contratoAgricolaParcelaMapper;

	public ContratoAgricolaMapper(ContratoAgricolaRepository contratoAgricolaRepository,
			DepartamentoRepository departamentoRepository, ItemRepository itemRepository,
			ParceiroLocalRepository parceiroLocalRepository, ParceiroRepository parceiroRepository,
			IndiceFinanceiroRepository indiceFinanceiroRepository,
			TipoContratoAgricolaRepository tipoContratoAgricolaRepository,
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository,
			OperacaoInternaRepository operacaoInternaRepository,
			GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository,
			FinalidadeContratoAgricolaRepository finalidadeContratoAgricolaRepository,
			PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository, SafraRepository safraRepository,
			RegraAtividadeRepository regraAtividadeRepository,
			ContratoAgricolaParcelaMapper contratoAgricolaParcelaMapper) {
		super(contratoAgricolaRepository, ContratoAgricola::new, ContratoAgricolaDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.itemRepository = itemRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.parceiroRepository = parceiroRepository;
		this.indiceFinanceiroRepository = indiceFinanceiroRepository;
		this.tipoContratoAgricolaRepository = tipoContratoAgricolaRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.grupoOperacaoAgricolaRepository = grupoOperacaoAgricolaRepository;
		this.finalidadeContratoAgricolaRepository = finalidadeContratoAgricolaRepository;
		this.predefinicaoPrecoAgricolaRepository = predefinicaoPrecoAgricolaRepository;
		this.safraRepository = safraRepository;
		this.regraAtividadeRepository = regraAtividadeRepository;
		this.contratoAgricolaParcelaMapper = contratoAgricolaParcelaMapper;
	}

	@Override
	protected void setValuesToEntity(ContratoAgricolaDTO dto, ContratoAgricola entity) {
		entity.setId(dto.getId());
		entity.setNumero(dto.getNumero());
		entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento da filial com id " + dto.getDepartamentoId())));
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		if (dto.getAvalistaId() != null) {
			entity.setAvalista(parceiroRepository.findById(dto.getAvalistaId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado avalista com id " + dto.getAvalistaId())));
		}
		if (dto.getIndiceFinanceiroId() != null) {
			entity.setIndiceFinanceiro(indiceFinanceiroRepository.findById(dto.getIndiceFinanceiroId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado índice financeiro com id " + dto.getIndiceFinanceiroId())));
		}
		if (dto.getTipoContratoAgricolaId() != null) {
			entity.setTipoContratoAgricola(tipoContratoAgricolaRepository.findById(dto.getTipoContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de contrato agrícola com id " + dto.getTipoContratoAgricolaId())));
		}
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getGrupoOperacaoAgricolaId() != null) {
			entity.setGrupoOperacaoAgricola(grupoOperacaoAgricolaRepository.findById(dto.getGrupoOperacaoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo de operação com id " + dto.getGrupoOperacaoAgricolaId())));
		}
		if (dto.getFinalidadeContratoAgricolaId() != null) {
			entity.setFinalidadeContratoAgricola(
					finalidadeContratoAgricolaRepository.findById(dto.getFinalidadeContratoAgricolaId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado finalidade de contrato agrícola com id "
									+ dto.getFinalidadeContratoAgricolaId())));
		}
		if (dto.getPredefinicaoPrecoAgricolaId() != null) {
			entity.setPredefinicaoPrecoAgricola(
					predefinicaoPrecoAgricolaRepository.findById(dto.getPredefinicaoPrecoAgricolaId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado predefinição de preço agrícola com id "
									+ dto.getPredefinicaoPrecoAgricolaId())));
		}
		if (dto.getSafraId() != null) {
			entity.setSafra(safraRepository.findById(dto.getSafraId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado safra com id " + dto.getSafraId())));
		}
		entity.setQuantidadeContratada(dto.getQuantidadeContratada());
		entity.setValorUnitarioLiquido(dto.getValorUnitarioLiquido());
		entity.setValorBruto(dto.getValorBruto());
		entity.setValorDesconto(dto.getValorDesconto());
		entity.setValorLiquido(dto.getValorLiquido());
		entity.setDataDocumento(dto.getDataDocumento());
		entity.setDataMovimento(dto.getDataMovimento());
		entity.setQuantidadeBaixada(dto.getQuantidadeBaixada());
		entity.setValorBaixado(dto.getValorBaixado());
		entity.setDataVencimento(dto.getDataVencimento());
		entity.setDataLimiteEntrega(dto.getDataLimiteEntrega());
		if (dto.getRegraAtividadeId() != null) {
			entity.setRegraAtividade(regraAtividadeRepository.findById(dto.getRegraAtividadeId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado regra de atividade com id " + dto.getRegraAtividadeId())));
		}
		entity.setValorUnitarioBase(dto.getValorUnitarioBase());
		entity.setValorPremio(dto.getValorPremio());
		entity.setQuantidadeAcordoPremio(dto.getQuantidadeAcordoPremio());
		entity.setObservacao(dto.getObservacao());
		entity.setNivelClass1(dto.getNivelClass1());
		entity.setNivelClass2(dto.getNivelClass2());
		entity.setNivelClass3(dto.getNivelClass3());
		entity.setNivelClass4(dto.getNivelClass4());

		if (dto.getParcelas() != null && dto.getParcelas().size() > 0) {
			entity.setParcelas(new ArrayList<>());
			entity.getParcelas().addAll(contratoAgricolaParcelaMapper.toEntity(dto.getParcelas()));
		}
	}

	@Override
	protected void setValuesToDto(ContratoAgricola entity, ContratoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setNumero(entity.getNumero());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setItemId(entity.getItem().getId());
		dto.setItemNome(entity.getItem().getNome());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
		dto.setAvalistaId(entity.getAvalista().getId());
		dto.setAvalistaNome(entity.getAvalista().getNomeRazaoSocial());
		dto.setIndiceFinanceiroId(entity.getIndiceFinanceiro().getId());
		dto.setIndiceFinanceiroNome(entity.getIndiceFinanceiro().getNome());
		dto.setTipoContratoAgricolaId(entity.getTipoContratoAgricola().getId());
		dto.setTipoPrecoAgricolaNome(entity.getTipoContratoAgricola().getNome());
		dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
		dto.setTipoPrecoAgricolaNome(entity.getTipoPrecoAgricola().getNome());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setGrupoOperacaoAgricolaId(entity.getGrupoOperacaoAgricola().getId());
		dto.setGrupoOperacaoAgricolaNome(entity.getGrupoOperacaoAgricola().getNome());
		dto.setFinalidadeContratoAgricolaId(entity.getFinalidadeContratoAgricola().getId());
		dto.setFinalidadeContratoAgricolaNome(entity.getFinalidadeContratoAgricola().getNome());
		dto.setPredefinicaoPrecoAgricolaId(entity.getPredefinicaoPrecoAgricola().getId());
		dto.setPredefinicaoPrecoAgricolaNome(entity.getPredefinicaoPrecoAgricola().getNome());
		dto.setSafraId(entity.getSafra().getId());
		dto.setSafraNome(entity.getSafra().getNome());
		dto.setQuantidadeContratada(entity.getQuantidadeContratada());
		dto.setValorUnitarioLiquido(entity.getValorUnitarioLiquido());
		dto.setValorUnitarioBruto(entity.getValorUnitarioBruto());
		dto.setValorBruto(entity.getValorBruto());
		dto.setValorDesconto(entity.getValorDesconto());
		dto.setValorLiquido(entity.getValorLiquido());
		dto.setDataDocumento(entity.getDataDocumento());
		dto.setDataMovimento(entity.getDataMovimento());
		dto.setQuantidadeBaixada(entity.getQuantidadeBaixada());
		dto.setValorBaixado(entity.getValorBaixado());
		dto.setDataVencimento(entity.getDataVencimento());
		dto.setDataLimiteEntrega(entity.getDataLimiteEntrega());
		dto.setRegraAtividadeId(entity.getRegraAtividade().getId());
		dto.setValorUnitarioBase(entity.getValorUnitarioBase());
		dto.setValorPremio(entity.getValorPremio());
		dto.setQuantidadeAcordoPremio(entity.getQuantidadeAcordoPremio());
		dto.setObservacao(entity.getObservacao());
		dto.setNivelClass1(entity.getNivelClass1());
		dto.setNivelClass2(entity.getNivelClass2());
		dto.setNivelClass3(entity.getNivelClass3());
		dto.setNivelClass4(entity.getNivelClass4());

		dto.setParcelas(new ArrayList<>());
		if (entity.getParcelas() != null) {
			dto.getParcelas().addAll(contratoAgricolaParcelaMapper.toDto(entity.getParcelas()));
		}

	}
}
