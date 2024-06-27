package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.repository.ContaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.GrupoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoCaixaBancoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoCaixaBancoRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MovimentoCaixaBancoMapper extends GenericMapper<MovimentoCaixaBanco, MovimentoCaixaBancoDTO> {

	private DepartamentoRepository departamentoRepository;
	private OperacaoCaixaBancoRepository operacaoCaixaBancoRepository;
	private ContaRepository contaRepository;
	private ParceiroLocalRepository parceiroLocalRepository;
	private FatoGeradorRepository fatoGeradorRepository;
	private HistoricoPadraoRepository historicoPadraoRepository;
	private GrupoFinanceiroRepository grupoFinanceiroRepository;

	public MovimentoCaixaBancoMapper(MovimentoCaixaBancoRepository repository,
			DepartamentoRepository departamentoRepository, OperacaoCaixaBancoRepository operacaoCaixaBancoRepository,
			ContaRepository contaRepository, ParceiroLocalRepository parceiroLocalRepository,
			FatoGeradorRepository fatoGeradorRepository, HistoricoPadraoRepository historicoPadraoRepository,
			GrupoFinanceiroRepository grupoFinanceiroRepository) {
		super(repository, MovimentoCaixaBanco::new, MovimentoCaixaBancoDTO::new);
		this.departamentoRepository = departamentoRepository;
		this.operacaoCaixaBancoRepository = operacaoCaixaBancoRepository;
		this.contaRepository = contaRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.historicoPadraoRepository = historicoPadraoRepository;
		this.grupoFinanceiroRepository = grupoFinanceiroRepository;
	}

	@Override
	protected void setValuesToEntity(MovimentoCaixaBancoDTO dto, MovimentoCaixaBanco entity) {
		entity.setId(dto.getId());
		entity.setNossoNumero(dto.getNossoNumero());
		entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado departamento com id " + dto.getDepartamentoId())));
		entity.setOperacaoCaixaBanco(operacaoCaixaBancoRepository.findById(dto.getOperacaoCaixaBancoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado operação com id " + dto.getOperacaoCaixaBancoId())));
		entity.setConta(contaRepository.findById(dto.getContaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado conta com id " + dto.getContaId())));
		if (dto.getContaTransfId() != null) {
			entity.setContaTransf(
					contaRepository.findById(dto.getContaTransfId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado conta transferência com id " + dto.getContaTransfId())));
		}
		entity.setDataMovimento(dto.getDataMovimento());
		entity.setNumeroDocumento(dto.getNumeroDocumento());
		entity.setDataMovimentoBanco(dto.getDataMovimentoBanco());
		entity.setDataVencimento(dto.getDataVencimento());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		if (dto.getFatoGeradorId() != null) {
			entity.setFatoGerador(fatoGeradorRepository.findById(dto.getFatoGeradorId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado fato gerador com id " + dto.getFatoGeradorId())));
		}
		if (dto.getHistoricoPadraoId() != null) {
			entity.setHistoricoPadrao(historicoPadraoRepository.findById(dto.getHistoricoPadraoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado histórico padrão com id " + dto.getHistoricoPadraoId())));
		}
		entity.setComplementoHP(dto.getComplementoHP());
		entity.setValor(dto.getValor());
		entity.setIndicadorDC(IndicadorDC.fromValue(dto.getIndicadorDC()));
		entity.setCompensado(dto.isCompensado());
		entity.setDataCompensado(dto.getDataCompensado());
		if (dto.getGrupoFinanceiroId() != null) {
			entity.setGrupoFinanceiro(grupoFinanceiroRepository.findById(dto.getGrupoFinanceiroId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo financeiro com id " + dto.getGrupoFinanceiroId())));
		}
	}

	@Override
	protected void setValuesToDto(MovimentoCaixaBanco entity, MovimentoCaixaBancoDTO dto) {
		dto.setId(entity.getId());
		dto.setNossoNumero(entity.getNossoNumero());
		dto.setDepartamentoId(entity.getDepartamento().getId());
		dto.setOperacaoCaixaBancoId(entity.getOperacaoCaixaBanco().getId());
		dto.setContaId(entity.getConta().getId());
		if (entity.getContaTransf() != null) {
			dto.setContaTransfId(entity.getContaTransf().getId());
		}
		dto.setDataMovimento(entity.getDataMovimento());
		dto.setNumeroDocumento(entity.getNumeroDocumento());
		dto.setDataMovimentoBanco(entity.getDataMovimentoBanco());
		dto.setDataVencimento(entity.getDataVencimento());
		if (entity.getParceiroLocal() != null) {
			dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		}
		if (entity.getFatoGerador() != null) {
			dto.setFatoGeradorId(entity.getFatoGerador().getId());
		}
		if (entity.getHistoricoPadrao() != null) {
			dto.setHistoricoPadraoId(entity.getHistoricoPadrao().getId());
		}
		dto.setComplementoHP(entity.getComplementoHP());
		dto.setValor(entity.getValor());
		dto.setIndicadorDC(entity.getIndicadorDC().getValue());
		dto.setCompensado(entity.isCompensado());
		dto.setDataCompensado(entity.getDataCompensado());
		if (entity.getGrupoFinanceiro() != null) {
			dto.setGrupoFinanceiroId(entity.getGrupoFinanceiro().getId());
		}
		
		dto.setDepartamentoNome(entity.getDepartamento().getNome());
		dto.setContaNumero(entity.getConta().getNumero());
		dto.setContaNome(entity.getConta().getNome());
		dto.setAgenciaNome(entity.getConta().getAgencia().getNome());
		dto.setBancoNome(entity.getConta().getAgencia().getBanco().getNome());
	}
}
