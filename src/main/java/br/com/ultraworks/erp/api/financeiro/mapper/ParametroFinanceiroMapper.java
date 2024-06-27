package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoAcessoriaFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParametroFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.PlanoClassificacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParametroFinanceiroMapper extends GenericMapper<ParametroFinanceiro, ParametroFinanceiroDTO> {

	private TipoTituloRepository tipoTituloRepository;
	private CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository;
	private OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository;
	private OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository;
	private CarteiraFinanceiraRepository carteiraFinanceiraRepository;
	private FatoGeradorRepository fatoGeradorRepository;
	private PlanoClassificacaoFinanceiraRepository planoClassificacaoFinanceiraRepository;

	public ParametroFinanceiroMapper(ParametroFinanceiroRepository repository, 
			TipoTituloRepository tipoTituloRepository,
			CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository,
			OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository,
			OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository,
			CarteiraFinanceiraRepository carteiraFinanceiraRepository,
			FatoGeradorRepository fatoGeradorRepository,
			PlanoClassificacaoFinanceiraRepository planoClassificacaoFinanceiraRepository) {
		super(repository, ParametroFinanceiro::new, ParametroFinanceiroDTO::new);
		this.carteiraFinanceiraRepository = carteiraFinanceiraRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.planoClassificacaoFinanceiraRepository = planoClassificacaoFinanceiraRepository;
		this.tipoTituloRepository = tipoTituloRepository;
		this.caracteristicaMovimentoFinanceiroRepository = caracteristicaMovimentoFinanceiroRepository;
		this.operacaoMovimentoFinanceiroRepository = operacaoMovimentoFinanceiroRepository;
		this.operacaoAcessoriaFinanceiraRepository = operacaoAcessoriaFinanceiraRepository;
	}

	@Override
	protected void setValuesToEntity(ParametroFinanceiroDTO dto, ParametroFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setTipoTitulo(tipoTituloRepository.findById(dto.getTipoTituloId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado tipo título com id " + dto.getTipoTituloId())));
		entity.setCaracteristicaMovimentoFinanceiro(caracteristicaMovimentoFinanceiroRepository
				.findById(dto.getCaracteristicaMovimentoFinanceiroId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado caracteristica movimento financeiro com id "
								+ dto.getCaracteristicaMovimentoFinanceiroId())));
		entity.setCarteiraFinanceira(carteiraFinanceiraRepository.findById(dto.getCarteiraFinanceiraId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado carteira financeira com id " + dto.getCarteiraFinanceiraId())));		
		entity.setFatoGerador(fatoGeradorRepository.findById(dto.getFatoGeradorId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado fato gerador com id " + dto.getFatoGeradorId())));		
		
		entity.setPlanoClassificacaoFinanceira(planoClassificacaoFinanceiraRepository.findById(dto.getPlanoClassificacaoFinanceiraId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado classificação financeira com id " + dto.getPlanoClassificacaoFinanceiraId())));		
		
		entity.setOperacaoMovimentoFinanceiro(
				operacaoMovimentoFinanceiroRepository.findById(dto.getOperacaoMovimentoFinanceiroId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado operação de movimento financeiro com id "
								+ dto.getOperacaoMovimentoFinanceiroId())));
		entity.setOperacaoAcessoriaFinanceira(
				operacaoAcessoriaFinanceiraRepository.findById(dto.getOperacaoAcessoriaFinanceiraId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado operação acessória financeira com id "
								+ dto.getOperacaoAcessoriaFinanceiraId())));
		entity.setIndicadorDC(IndicadorDC.fromValue(dto.getIndicadorDC()));

	}

	@Override
	protected void setValuesToDto(ParametroFinanceiro entity, ParametroFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setTipoTituloId(entity.getTipoTitulo().getId());
		dto.setCaracteristicaMovimentoFinanceiroId(entity.getCaracteristicaMovimentoFinanceiro().getId());
		dto.setCarteiraFinanceiraId(entity.getCarteiraFinanceira().getId());
		dto.setFatoGeradorId(entity.getFatoGerador().getId());
		dto.setIndicadorDC(entity.getIndicadorDC().getValue());
		dto.setOperacaoMovimentoFinanceiroId(entity.getOperacaoMovimentoFinanceiro().getId());
		dto.setOperacaoAcessoriaFinanceiraId(entity.getOperacaoAcessoriaFinanceira().getId());
		dto.setPlanoClassificacaoFinanceiraId(entity.getPlanoClassificacaoFinanceira().getId());

		dto.setTipoTituloSigla(entity.getTipoTitulo().getSigla());
		dto.setCaracteristicaMovimentoFinanceiroSigla(entity.getCaracteristicaMovimentoFinanceiro().getSigla());
		dto.setCarteiraFinanceiraSigla(entity.getCarteiraFinanceira().getSigla());
		dto.setFatoGeradorSigla(entity.getFatoGerador().getSigla());
		dto.setOperacaoMovimentoFinanceiroNome(entity.getOperacaoMovimentoFinanceiro().getNome());
		dto.setOperacaoAcessoriaFinanceiraNome(entity.getOperacaoAcessoriaFinanceira().getNome());
		dto.setPlanoClassificacaoFinanceiraCodigo(entity.getPlanoClassificacaoFinanceira().getCodigo());
	}

}
