package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.GrupoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.IndiceFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.MotivoEstornoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiro;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiroDTO;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFinanceiroRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaFinanceiroMapper
		extends GenericMapper<OperacaoInternaFinanceiro, OperacaoInternaFinanceiroDTO> {

	private OperacaoInternaRepository operacaoInternaRepository;
	private IndiceFinanceiroRepository indiceFinanceiroRepository;
	private TipoTituloRepository tipoTituloRepository;
	private GrupoFinanceiroRepository grupoFinanceiroRepository;
	private CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository;
	private HistoricoPadraoRepository historicoPadraoRepository;
	private CarteiraFinanceiraRepository carteiraFinanceiraRepository;
	private FatoGeradorRepository fatoGeradorRepository;
	private MotivoEstornoFinanceiroRepository motivoEstornoFinanceiroRepository;

	public OperacaoInternaFinanceiroMapper(OperacaoInternaFinanceiroRepository repository,
			OperacaoInternaRepository operacaoInternaRepository,
			IndiceFinanceiroRepository indiceFinanceiroRepository,
			TipoTituloRepository tipoTituloRepository,
			GrupoFinanceiroRepository grupoFinanceiroRepository,
			CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository,
			HistoricoPadraoRepository historicoPadraoRepository,
			CarteiraFinanceiraRepository carteiraFinanceiraRepository,
			FatoGeradorRepository fatoGeradorRepository,
			MotivoEstornoFinanceiroRepository motivoEstornoFinanceiroRepository) {
		super(repository, OperacaoInternaFinanceiro::new, OperacaoInternaFinanceiroDTO::new);
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.indiceFinanceiroRepository = indiceFinanceiroRepository;
		this.tipoTituloRepository = tipoTituloRepository;
		this.grupoFinanceiroRepository = grupoFinanceiroRepository;
		this.caracteristicaMovimentoFinanceiroRepository = caracteristicaMovimentoFinanceiroRepository;
		this.historicoPadraoRepository = historicoPadraoRepository;
		this.carteiraFinanceiraRepository = carteiraFinanceiraRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.motivoEstornoFinanceiroRepository = motivoEstornoFinanceiroRepository;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaFinanceiroDTO dto, OperacaoInternaFinanceiro entity) {
		entity.setId(dto.getId());
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getIndiceFinanceiroPadraoId() != null) {
			entity.setIndiceFinanceiroPadrao(indiceFinanceiroRepository.findById(dto.getIndiceFinanceiroPadraoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado índice financeiro com id " + dto.getIndiceFinanceiroPadraoId())));
		}
		if (dto.getTipoTituloId() != null) {
			entity.setTipoTitulo(tipoTituloRepository.findById(dto.getTipoTituloId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo título com id " + dto.getTipoTituloId())));
		}
		if (dto.getGrupoFinanceiroId() != null) {
			entity.setGrupoFinanceiro(grupoFinanceiroRepository.findById(dto.getGrupoFinanceiroId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo financeiro com id " + dto.getGrupoFinanceiroId())));
		}
		if (dto.getCaracteristicaMovimentoFinanceiroId() != null) {
			entity.setCaracteristicaMovimentoFinanceiro(caracteristicaMovimentoFinanceiroRepository.findById(dto.getCaracteristicaMovimentoFinanceiroId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrada característica movimento financeiro com id " + dto.getCaracteristicaMovimentoFinanceiroId())));
		}
		if (dto.getHistoricoPadraoId() != null) {
			entity.setHistoricoPadrao(historicoPadraoRepository.findById(dto.getHistoricoPadraoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado histórico padrão com id " + dto.getHistoricoPadraoId())));
		}
		if (dto.getCarteiraFinanceiraId() != null) {
			entity.setCarteiraFinanceira(carteiraFinanceiraRepository.findById(dto.getCarteiraFinanceiraId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrada carteira financeira com id " + dto.getCarteiraFinanceiraId())));
		}
		if (dto.getFatoGeradorId() != null) {
			entity.setFatoGerador(fatoGeradorRepository.findById(dto.getFatoGeradorId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado fato gerador com id " + dto.getFatoGeradorId())));
		}
		if (dto.getMotivoEstornoFinanceiroId() != null) {
			entity.setMotivoEstornoFinanceiro(motivoEstornoFinanceiroRepository.findById(dto.getMotivoEstornoFinanceiroId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado motivo estorno financeiro com id " + dto.getMotivoEstornoFinanceiroId())));
		}
	}

	@Override
	protected void setValuesToDto(OperacaoInternaFinanceiro entity, OperacaoInternaFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		if (entity.getIndiceFinanceiroPadrao() != null) {
			dto.setIndiceFinanceiroPadraoId(entity.getIndiceFinanceiroPadrao().getId());
			dto.setIndiceFinanceiroPadraoNome(entity.getIndiceFinanceiroPadrao().getNome());
		}
		if (entity.getTipoTitulo() != null) {
			dto.setTipoTituloId(entity.getTipoTitulo().getId());
			dto.setTipoTituloNome(entity.getTipoTitulo().getNome());
		}
		if (entity.getGrupoFinanceiro() != null) {
			dto.setGrupoFinanceiroId(entity.getGrupoFinanceiro().getId());
			dto.setGrupoFinanceiroNome(entity.getGrupoFinanceiro().getNome());
		}
		if (entity.getCaracteristicaMovimentoFinanceiro() != null) {
			dto.setCaracteristicaMovimentoFinanceiroId(entity.getCaracteristicaMovimentoFinanceiro().getId());
			dto.setCaracteristicaMovimentoFinanceiroNome(entity.getCaracteristicaMovimentoFinanceiro().getNome());
		}
		if (entity.getHistoricoPadrao() != null) {
			dto.setHistoricoPadraoId(entity.getHistoricoPadrao().getId());
			dto.setHistoricoPadraoNome(entity.getHistoricoPadrao().getNome());
		}
		if (entity.getCarteiraFinanceira() != null) {
			dto.setCarteiraFinanceiraId(entity.getCarteiraFinanceira().getId());
			dto.setCarteiraFinanceiraNome(entity.getCarteiraFinanceira().getNome());
		}
		if (entity.getFatoGerador() != null) {
			dto.setFatoGeradorId(entity.getFatoGerador().getId());
			dto.setFatoGeradorNome(entity.getFatoGerador().getNome());
		}
		if (entity.getMotivoEstornoFinanceiro() != null) {
			dto.setMotivoEstornoFinanceiroId(entity.getMotivoEstornoFinanceiro().getId());
			dto.setMotivoEstornoFinanceiroNome(entity.getMotivoEstornoFinanceiro().getNome());
		}
	}
}
