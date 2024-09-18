package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoCaixaBancoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoContaCxBcoRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoCaixaBancoMapper extends GenericMapper<OperacaoCaixaBanco, OperacaoCaixaBancoDTO> {

	private HistoricoPadraoRepository historicoPadraoRepository;
	private FatoGeradorRepository fatoGeradorRepository;
	private TipoContaCxBcoRepository tipoContaCxBcoRepository;
	private ParceiroLocalRepository parceiroLocalRepository;

	public OperacaoCaixaBancoMapper(OperacaoCaixaBancoRepository repository,
			HistoricoPadraoRepository historicoPadraoRepository, FatoGeradorRepository fatoGeradorRepository,
			TipoContaCxBcoRepository tipoContaCxBcoRepository, ParceiroLocalRepository parceiroLocalRepository) {
		super(repository, OperacaoCaixaBanco::new, OperacaoCaixaBancoDTO::new);
		this.historicoPadraoRepository = historicoPadraoRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
		this.tipoContaCxBcoRepository = tipoContaCxBcoRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(OperacaoCaixaBancoDTO dto, OperacaoCaixaBanco entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setDigitaVencimento(dto.isDigitaVencimento());
		entity.setDigitaDataMovimento(dto.isDigitaDataMovimento());
		entity.setDigitaHistoricoPadrao(dto.isDigitaHistoricoPadrao());
		if (dto.getHistoricoPadraoIdDefault() != null)
			entity.setHistoricoPadraoDefault(historicoPadraoRepository.findById(dto.getHistoricoPadraoIdDefault())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado histórico padrão com id " + dto.getHistoricoPadraoIdDefault())));
		entity.setDigitaFatoGerador(dto.isDigitaFatoGerador());
		if (dto.getFatoGeradorIdDefault() != null)
			entity.setFatoGeradorDefault(fatoGeradorRepository.findById(dto.getFatoGeradorIdDefault())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado fato gerador padrão com id " + dto.getFatoGeradorIdDefault())));
		entity.setDigitaComplementoHp(dto.isDigitaComplementoHp());
		if (dto.getTipoContaCxBcoId() != null)
			entity.setTipoContaCxBco(tipoContaCxBcoRepository.findById(dto.getTipoContaCxBcoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de conta com id " + dto.getTipoContaCxBcoId())));
		entity.setIndicadorDC(IndicadorDC.fromValue(dto.getIndicadorDC()));
		entity.setDigitaDocumento(dto.isDigitaDocumento());
		entity.setDigitaParceiro(dto.isDigitaParceiro());
		if (dto.getParceiroLocalIdDefault() != null)
			entity.setParceiroLocalDefault(parceiroLocalRepository.findById(dto.getParceiroLocalIdDefault())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro padrão com id " + dto.getParceiroLocalIdDefault())));
		entity.setTransferencia(dto.isTransferencia());
		if (dto.getOperacaoCaixaBancoIdTransferencia() != null)
			entity.setOperacaoCaixaBancoIdTransferencia(repository.findById(dto.getOperacaoCaixaBancoIdTransferencia())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado operação de transferência com id "
							+ dto.getOperacaoCaixaBancoIdTransferencia())));
		entity.setEmiteRecibo(dto.isEmiteRecibo());

	}

	@Override
	protected void setValuesToDto(OperacaoCaixaBanco entity, OperacaoCaixaBancoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setDigitaVencimento(entity.isDigitaVencimento());
		dto.setDigitaDataMovimento(entity.isDigitaDataMovimento());
		dto.setDigitaHistoricoPadrao(entity.isDigitaHistoricoPadrao());
		if (entity.getHistoricoPadraoDefault() != null)
			dto.setHistoricoPadraoIdDefault(entity.getHistoricoPadraoDefault().getId());
		dto.setDigitaFatoGerador(entity.isDigitaFatoGerador());
		if (entity.getFatoGeradorDefault() != null)
			dto.setFatoGeradorIdDefault(entity.getFatoGeradorDefault().getId());
		dto.setDigitaComplementoHp(entity.isDigitaComplementoHp());
		dto.setTipoContaCxBcoId(entity.getTipoContaCxBco().getId());
		dto.setIndicadorDC(entity.getIndicadorDC().getValue());
		dto.setDigitaDocumento(entity.isDigitaDocumento());
		dto.setDigitaParceiro(entity.isDigitaParceiro());
		if (entity.getParceiroLocalDefault() != null)
			dto.setParceiroLocalIdDefault(entity.getParceiroLocalDefault().getId());
		dto.setTransferencia(entity.isTransferencia());
		if (entity.getOperacaoCaixaBancoIdTransferencia() != null)
			dto.setOperacaoCaixaBancoIdTransferencia(entity.getOperacaoCaixaBancoIdTransferencia().getId());
		dto.setEmiteRecibo(entity.isEmiteRecibo());
	}
}
