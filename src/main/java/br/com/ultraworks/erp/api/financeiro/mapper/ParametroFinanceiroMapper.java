package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.CaracteristicaMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParametroFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParametroFinanceiroMapper extends GenericMapper<ParametroFinanceiro, ParametroFinanceiroDTO> {

	private TipoTituloRepository tipoTituloRepository;
	private CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository;
	private CarteiraFinanceiraRepository carteiraRepository;
	private FatoGeradorRepository fatoGeradorRepository;
	private OperacaoFinanceiraRepository operacaoFinanceiraRepository;
	private EmpresaRepository empresaRepository;

	public ParametroFinanceiroMapper(ParametroFinanceiroRepository repository, EmpresaRepository empresaRepository,
			TipoTituloRepository tipoTituloRepository,
			CaracteristicaMovimentoFinanceiroRepository caracteristicaMovimentoFinanceiroRepository,
			CarteiraFinanceiraRepository carteiraRepository, FatoGeradorRepository fatoGeradorRepository,
			OperacaoFinanceiraRepository operacaoFinanceiraRepository) {
		super(repository, ParametroFinanceiro::new, ParametroFinanceiroDTO::new);
		this.empresaRepository = empresaRepository;
		this.tipoTituloRepository = tipoTituloRepository;
		this.caracteristicaMovimentoFinanceiroRepository = caracteristicaMovimentoFinanceiroRepository;
		this.carteiraRepository = carteiraRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.operacaoFinanceiraRepository = operacaoFinanceiraRepository;
	}

	@Override
	protected void setValuesToEntity(ParametroFinanceiroDTO dto, ParametroFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado empresa com id " + dto.getEmpresaId())));
		entity.setTipoTitulo(tipoTituloRepository.findById(dto.getTipoTituloId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado tipo título com id " + dto.getTipoTituloId())));
		entity.setCaracteristicaMovimentoFinanceiro(caracteristicaMovimentoFinanceiroRepository
				.findById(dto.getCaracteristicaMovimentoFinanceiroId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado caracteristica movimento financeiro com id "
								+ dto.getCaracteristicaMovimentoFinanceiroId())));
		entity.setCarteiraFinanceira(carteiraRepository.findById(dto.getCarteiraFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado carteira financeira com id " + dto.getCarteiraFinanceiraId())));
		entity.setFatoGerador(fatoGeradorRepository.findById(dto.getFatoGeradorId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado fato gerador com id " + dto.getFatoGeradorId())));
		entity.setIndicadorDC(IndicadorDC.valueOf(dto.getIndicadorDC()));
		entity.setOperacaoFinanceira(operacaoFinanceiraRepository.findById(dto.getOperacaoFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado operação financeira com id " + dto.getOperacaoFinanceiraId())));

	}

	@Override
	protected void setValuesToDto(ParametroFinanceiro entity, ParametroFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaId(entity.getEmpresa().getId());
		dto.setTipoTituloId(entity.getTipoTitulo().getId());
		dto.setCaracteristicaMovimentoFinanceiroId(entity.getCaracteristicaMovimentoFinanceiro().getId());
		dto.setCarteiraFinanceiraId(entity.getCarteiraFinanceira().getId());
		dto.setFatoGeradorId(entity.getFatoGerador().getId());
		dto.setIndicadorDC(entity.getIndicadorDC().getValue());
		dto.setOperacaoFinanceiraId(entity.getOperacaoFinanceira().getId());
	}
}
