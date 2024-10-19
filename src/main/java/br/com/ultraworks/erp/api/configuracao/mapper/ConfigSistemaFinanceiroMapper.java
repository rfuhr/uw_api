package br.com.ultraworks.erp.api.configuracao.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiro;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiroDTO;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaFinanceiroRepository;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.GrupoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoAcessoriaFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigSistemaFinanceiroMapper extends GenericMapper<ConfigSistemaFinanceiro, ConfigSistemaFinanceiroDTO> {

	ConfigSistemaRepository configSistemaRepository;
	OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository;
	OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository;
	TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository;
	GrupoFinanceiroRepository grupoFinanceiroRepository;
	FatoGeradorRepository fatoGeradorRepository;
	CarteiraFinanceiraRepository carteiraFinanceiraRepository;
	CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository;
	HistoricoPadraoRepository historicoPadraoRepository;
	TipoTituloRepository tipoTituloRepository;

	public ConfigSistemaFinanceiroMapper(ConfigSistemaFinanceiroRepository configSistemaFinanceiroRepository,
			ConfigSistemaRepository configSistemaRepository,
			OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository,
			OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository,
			TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository,
			GrupoFinanceiroRepository grupoFinanceiroRepository, FatoGeradorRepository fatoGeradorRepository,
			CarteiraFinanceiraRepository carteiraFinanceiraRepository,
			CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository,
			HistoricoPadraoRepository historicoPadraoRepository, TipoTituloRepository tipoTituloRepository) {
		super(configSistemaFinanceiroRepository, ConfigSistemaFinanceiro::new, ConfigSistemaFinanceiroDTO::new);
		this.configSistemaRepository = configSistemaRepository;
		this.operacaoMovimentoFinanceiroRepository = operacaoMovimentoFinanceiroRepository;
		this.operacaoAcessoriaFinanceiraRepository = operacaoAcessoriaFinanceiraRepository;
		this.tipoOperacaoFinanceiraRepository = tipoOperacaoFinanceiraRepository;
		this.grupoFinanceiroRepository = grupoFinanceiroRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.carteiraFinanceiraRepository = carteiraFinanceiraRepository;
		this.caracteristicaMovimentoFinanceiroRepository = caracteristicaMovimentoFinanceiroRepository;
		this.historicoPadraoRepository = historicoPadraoRepository;
		this.tipoTituloRepository = tipoTituloRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigSistemaFinanceiroDTO dto, ConfigSistemaFinanceiro entity) {
		entity.setId(dto.getId());
		if (dto.getConfigSistemaId() != null) {
			entity.setConfigSistema(configSistemaRepository.findById(dto.getConfigSistemaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração de sistema com id " + dto.getConfigSistemaId())));
		}
		entity.setTipoTituloReceber(tipoTituloRepository
				.findById(dto.getTipoTituloReceberId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado tipo de título a receber com id "
								+ dto.getTipoTituloReceberId())));
		entity.setTipoTituloPagar(tipoTituloRepository
				.findById(dto.getTipoTituloPagarId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado tipo de título a pagar com id "
								+ dto.getTipoTituloPagarId())));
		
		entity.setOperacaoMovimentoFinanceiroInclusao(operacaoMovimentoFinanceiroRepository
				.findById(dto.getOperacaoMovimentoFinanceiroInclusaoId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado operação de movimento financeiro com id "
								+ dto.getOperacaoMovimentoFinanceiroInclusaoId())));

		entity.setOperacaoMovimentoFinanceiroBaixa(
				operacaoMovimentoFinanceiroRepository.findById(dto.getOperacaoMovimentoFinanceiroBaixaId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado operação de movimento financeiro com id "
								+ dto.getOperacaoMovimentoFinanceiroBaixaId())));

		entity.setOperacaoAcessoriaFinanceiraPrincipal(
				operacaoAcessoriaFinanceiraRepository.findById(dto.getOperacaoAcessoriaFinanceiraPrincipalId())
						.orElseThrow(() -> new RegisterNotFoundException("Não encontrado operação acessória com id "
								+ dto.getOperacaoAcessoriaFinanceiraPrincipalId())));

		entity.setOperacaoAcessoriaFinanceiraJurosPactuado(
				operacaoAcessoriaFinanceiraRepository.findById(dto.getOperacaoAcessoriaFinanceiraJurosPactuadoId())
						.orElseThrow(() -> new RegisterNotFoundException("Não encontrado operação acessória com id "
								+ dto.getOperacaoAcessoriaFinanceiraJurosPactuadoId())));

		entity.setTipoOperacaoFinanceiraLancamento(tipoOperacaoFinanceiraRepository
				.findById(dto.getTipoOperacaoFinanceiraLancamentoId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de operação com id " + dto.getTipoOperacaoFinanceiraLancamentoId())));

		entity.setTipoOperacaoFinanceiraEstorno(tipoOperacaoFinanceiraRepository
				.findById(dto.getTipoOperacaoFinanceiraEstornoId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de operação com id " + dto.getTipoOperacaoFinanceiraEstornoId())));

		entity.setGrupoFinanceiroNegociacao(grupoFinanceiroRepository.findById(dto.getGrupoFinanceiroNegociacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo financeiro com id " + dto.getGrupoFinanceiroNegociacaoId())));

		entity.setFatoGeradorNegociacao(fatoGeradorRepository.findById(dto.getFatoGeradorNegociacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado fato gerador com id " + dto.getFatoGeradorNegociacaoId())));

		entity.setCarteiraFinanceiraNegociacao(carteiraFinanceiraRepository
				.findById(dto.getCarteiraFinanceiraNegociacaoId()).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado carteira financeira com id " + dto.getCarteiraFinanceiraNegociacaoId())));

		entity.setCaracteristicaMovimentoFinanceiroNegociacao(caracteristicaMovimentoFinanceiroRepository
				.findById(dto.getCaracteristicaMovimentoFinanceiroNegociacaoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado caracteristica movimento com id "
						+ dto.getCaracteristicaMovimentoFinanceiroNegociacaoId())));

		entity.setHistoricoPadraoNegociacao(historicoPadraoRepository.findById(dto.getHistoricoPadraoNegociacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado histórico padrão com id " + dto.getHistoricoPadraoNegociacaoId())));

	}

	@Override
	protected void setValuesToDto(ConfigSistemaFinanceiro entity, ConfigSistemaFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigSistemaId(entity.getConfigSistema().getId());
		dto.setTipoTituloReceberId(entity.getTipoTituloReceber().getId());
		dto.setTipoTituloPagarId(entity.getTipoTituloPagar().getId());
		dto.setOperacaoMovimentoFinanceiroInclusaoId(entity.getOperacaoMovimentoFinanceiroInclusao().getId());
		dto.setOperacaoMovimentoFinanceiroBaixaId(entity.getOperacaoMovimentoFinanceiroBaixa().getId());
		dto.setOperacaoAcessoriaFinanceiraPrincipalId(entity.getOperacaoAcessoriaFinanceiraPrincipal().getId());
		dto.setOperacaoAcessoriaFinanceiraJurosPactuadoId(entity.getOperacaoAcessoriaFinanceiraJurosPactuado().getId());
		dto.setTipoOperacaoFinanceiraLancamentoId(entity.getTipoOperacaoFinanceiraLancamento().getId());
		dto.setTipoOperacaoFinanceiraEstornoId(entity.getTipoOperacaoFinanceiraEstorno().getId());
		dto.setGrupoFinanceiroNegociacaoId(entity.getGrupoFinanceiroNegociacao().getId());
		dto.setFatoGeradorNegociacaoId(entity.getFatoGeradorNegociacao().getId());
		dto.setCarteiraFinanceiraNegociacaoId(entity.getCarteiraFinanceiraNegociacao().getId());
		dto.setCaracteristicaMovimentoFinanceiroNegociacaoId(
				entity.getCaracteristicaMovimentoFinanceiroNegociacao().getId());
		dto.setHistoricoPadraoNegociacaoId(entity.getHistoricoPadraoNegociacao().getId());
	}
}
