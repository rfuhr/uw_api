package br.com.ultraworks.erp.api.agricola.integrator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.api.agricola.integrator.dto.ContainerConfiguracaoIntegracao;
import br.com.ultraworks.erp.api.agricola.integrator.dto.ContainerConfiguracaoIntegracaoFixacaoParcelas;
import br.com.ultraworks.erp.api.agricola.integrator.dto.RecordRomaneioFixar;
import br.com.ultraworks.erp.api.agricola.integrator.dto.RomaneioProblemaFixacaoDTO;
import br.com.ultraworks.erp.api.agricola.integrator.dto.RomaneioSelecaoFixacaoDTO;
import br.com.ultraworks.erp.api.agricola.service.ValidaOperacaoInternaAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaPrecoAgricolaItemService;
import br.com.ultraworks.erp.api.agricola.service.romaneioagricola.RomaneioAgricolaService;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.util.DateUtils;

@Service
@Scope("prototype")
public class ServicoIntegracaoFixacao {

	private RomaneioAgricolaService romaneioAgricolaService;
	private ValidaOperacaoInternaAgricolaService validaOperacaoInternaAgricolaService;
	private ValidaPrecoAgricolaItemService validaPrecoAgricolaItemService;

	private TipoOperacaoIntegracaoFixacao tipoOperacaoIntegracaoFixacao;
	private ContainerConfiguracaoIntegracao container;
	private List<RomaneioSelecaoFixacaoDTO> listaRomaneioSelecaoFixacaoDTO;
	private List<RomaneioProblemaFixacaoDTO> listaRomaneioProblemaFixacaoDTO;

	private List<RecordRomaneioFixar> listaRecordRomaneioFixar;

	public ServicoIntegracaoFixacao(RomaneioAgricolaService romaneioAgricolaService,
			ValidaOperacaoInternaAgricolaService validaOperacaoInternaAgricolaService,
			ValidaPrecoAgricolaItemService validaPrecoAgricolaItemService) {
		this.romaneioAgricolaService = romaneioAgricolaService;
		this.validaOperacaoInternaAgricolaService = validaOperacaoInternaAgricolaService;
		this.validaPrecoAgricolaItemService = validaPrecoAgricolaItemService;
	}

	public void iniciarIntegracao(TipoOperacaoIntegracaoFixacao tipoOperacaoIntegracaoFixacao) {
		this.tipoOperacaoIntegracaoFixacao = tipoOperacaoIntegracaoFixacao;
		this.container = new ContainerConfiguracaoIntegracao();
		this.listaRomaneioSelecaoFixacaoDTO = new ArrayList<RomaneioSelecaoFixacaoDTO>();
		this.listaRomaneioProblemaFixacaoDTO = new ArrayList<RomaneioProblemaFixacaoDTO>();
	}

	public void definirOperacao(OperacaoInterna operacaoInternaFixacao) {
		container.setOperacaoInternaFixacao(operacaoInternaFixacao);
	}

	public void atribuirConfiguracaoParaFixacao(RomaneioAgricola romaneioAgricola, EmpresaFilial empresaFilial,
			Departamento departamento, Item item, GrupoOperacaoAgricola grupoOperacaoAgricola,
			ContratoAgricola contratoAgricola, BigDecimal quantidadeFixar, boolean descontarLiquido,
			boolean baixaAutomaticaContrato, LocalDate dataVencimento, BigDecimal valorUnitario,
			BigDecimal valorTotal) {
		container.setRomaneioAgricola(romaneioAgricola);
		container.setEmpresaFilial(empresaFilial);
		container.setDepartamento(departamento);
		container.setItem(item);
		container.setGrupoOperacaoAgricola(grupoOperacaoAgricola);
		container.setContratoAgricola(contratoAgricola);
		container.setQuantidadeFixar(quantidadeFixar);
		container.setDescontarLiquido(descontarLiquido);
		container.setBaixaAutomaticaContrato(baixaAutomaticaContrato);
		container.setDataVencimento(dataVencimento);
		container.setValorUnitario(valorUnitario);
		container.setValorTotal(valorTotal);
	}

	public void atribuirConfiguracaoParaSelecaoRomaneio(EmpresaFilial empresaFilial, Departamento departamento,
			ParceiroLocal parceiroLocal, Item item, GrupoOperacaoAgricola grupoOperacaoAgricola, LocalDate dataFixacao,
			ContratoAgricola contratoAgricola) {
		container.setEmpresaFilial(empresaFilial);
		container.setDepartamento(departamento);
		container.setParceiroLocal(parceiroLocal);
		container.setItem(item);
		container.setGrupoOperacaoAgricola(grupoOperacaoAgricola);
		container.setDataBaixa(dataFixacao);
		container.setContratoAgricola(contratoAgricola);
	}

	public void atribuirParcelaFixacao(LocalDate dataVencimento, BigDecimal valorParcela) {
		container.getParcelasFixacao().add(ContainerConfiguracaoIntegracaoFixacaoParcelas.builder()
				.dataVencimento(dataVencimento).valorParcela(valorParcela).build());
	}

	public void executarIntegracao() {
//		gerarContainerAuxiliarTemporarioFixacoes();
//		if (!listaContainerAuxiliarParaFixacao.isEmpty()) {
//			validaOperacaoInternaParaFixacao();
//			validaItemOperacaoEDefineTipoPrecoAgricola();
//			validaPredefinicaoPreco();
//			buscaUnidadePreco();
//			gravarFixacoes();
//		}
	}

	public void executarSelecaoRomaneio() {
		if (tipoOperacaoIntegracaoFixacao.isSelecaoRomaneios()) {
			Long empresaFilialId = container.getEmpresaFilial().getId();
			Long departamentoId = container.getDepartamento() != null ? container.getDepartamento().getId() : null;
			Long itemId = container.getItem().getId();
			List<Long> idsRomaneiosPossiveis = romaneioAgricolaService.buscarIdsRomaneiosParaFixacao(empresaFilialId,
					departamentoId, itemId);
			if (idsRomaneiosPossiveis != null && !idsRomaneiosPossiveis.isEmpty()) {
				idsRomaneiosPossiveis.forEach(romaneioAgricolaId -> {
					gerarRomaneioSelecaoFixacao(romaneioAgricolaId);
				});
			} else {
				throw new BusinessException("Não existe romaneios a serem baixados");
			}
		} else {
			throw new BusinessException("Tipo de integração inválida para seleção de romaneios");
		}
	}

	private void gravarFixacoes() {

	}

	private RomaneioSelecaoFixacaoDTO gerarRomaneioSelecaoFixacao(Long romaneioAgricolaId) {
		RomaneioAgricola romaneio = romaneioAgricolaService.getById(romaneioAgricolaId).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado romaneio agrícola com id " + romaneioAgricolaId));

		try {
			validaDataRomaneioXDataBaixa(romaneio);

			RomaneioSelecaoFixacaoDTO romaneioSelecaoFixacaoDTO = RomaneioSelecaoFixacaoDTO.builder()
					.romaneioAgricolaId(romaneio.getId()).parceiroId(romaneio.getParceiroLocal().getParceiro().getId())
					.parceiroLocalId(romaneio.getParceiroLocal().getId())
					.parceiroLocalPropriedadeId(romaneio.getParceiroLocalPropriedade().getId())
					.itemId(romaneio.getItem().getId()).dataMovimento(LocalDate.now())
					.numeroRomaneio(romaneio.getNumero()).departamentoId(romaneio.getDepartamento().getId())
					.tipoPrecoAgricolaId(container.getTipoPrecoAgricola().getId())
					.quantidadeParcial(container.getQuantidadeFixar())
					.saldoFixar(container.getOperacaoInternaFixacao().getOperacaoInternaAgricola()
							.isComplementoPrecoFixacao() ? BigDecimal.valueOf(1) : romaneio.getSaldoFixar())
					.operacaoInternaRomaneioId(romaneio.getOperacaoInterna().getId())
					.grupoOperacaoAgricolaRomaneioId(romaneio.getGrupoOperacaoAgricola().getId())
					.safraId(romaneio.getSafra() != null ? romaneio.getSafra().getId() : null)
					.numeroNfDeposito(romaneio.getNumeroNfDeposito()).build();

			validaContratoAgricola(romaneio);
			validaNotaFiscalDeposito(romaneio);
			validaNotaFiscalEntrada(romaneio);
			validaOperacaoInternaParaFixacao();
			buscarValorUnitario(romaneio);
			calcularDescontoAcrescimoInicial(romaneio);
			validaValorDesconto(romaneio);
			verificaNotaFiscalDepositoRemessaInformado(romaneio);

			listaRomaneioSelecaoFixacaoDTO.add(romaneioSelecaoFixacaoDTO);
		} catch (BusinessException e) {
			listaRomaneioProblemaFixacaoDTO.add(RomaneioProblemaFixacaoDTO.builder()
					.romaneioAgricolaId(romaneio.getId()).romaneioAgricolaNumero(romaneio.getNumero())
					.romaneioAgricolaData(DateUtils.formatDate(romaneio.getDataDocumento()))
					.romaneioAgricolaSaldoFixar(romaneio.getSaldoFixar()).problema(e.getMessage()).build());
		} catch (Exception e) {
			listaRomaneioProblemaFixacaoDTO.add(RomaneioProblemaFixacaoDTO.builder()
					.romaneioAgricolaId(romaneio.getId()).romaneioAgricolaNumero(romaneio.getNumero())
					.romaneioAgricolaData(DateUtils.formatDate(romaneio.getDataDocumento()))
					.romaneioAgricolaSaldoFixar(romaneio.getSaldoFixar()).problema("Problema não identificado")
					.build());
		}
		return null;
	}

	private void verificaNotaFiscalDepositoRemessaInformado(RomaneioAgricola romaneio) {
		// TODO Romaneio digitado com N.F. de Dep./Remessa, não é possivel fixar, faça a
		// 204

	}

	private void validaValorDesconto(RomaneioAgricola romaneio) {
		// TODO Auto-generated method stub

	}

	private void calcularDescontoAcrescimoInicial(RomaneioAgricola romaneio) {
		validaOrdemCalculo(romaneio);
		validaTaxaCalculo(romaneio);
		verificaValidacaoCalculo(romaneio);

	}

	private void verificaValidacaoCalculo(RomaneioAgricola romaneio) {
		// TODO VERIFICA VALIDAÇÃO CALCULO

	}

	private void validaTaxaCalculo(RomaneioAgricola romaneio) {
		// TODO VALIDA TAXA DE CALCULO

	}

	private void validaOrdemCalculo(RomaneioAgricola romaneio) {
		// TODO VALIDA SE TEM ORDEM DE CALCULO

	}

	private void buscarValorUnitario(RomaneioAgricola romaneio) {
		validaPredefinicaoPreco(romaneio);
		validaSeExisteLancamentoParaPredefinicao(romaneio);
		buscaPrecoDefinido(romaneio);
	}

	private void buscaPrecoDefinido(RomaneioAgricola romaneio) {
		// TODO BUSCAR PRECO DEFINIDO

	}

	private void validaSeExisteLancamentoParaPredefinicao(RomaneioAgricola romaneio) {
		// TODO VALIDA SE EXISTE LANÇAMENTOS PARA PREDEFINICAO

	}

	private void validaPredefinicaoPreco(RomaneioAgricola romaneio) {
		// TODO VALIDA SE EXISTE PREDEFINICAO DE PRECO

	}

	private void validaNotaFiscalEntrada(RomaneioAgricola romaneio) {
		if (romaneio.getNumeroNfDeposito() == 0 && (romaneio.getNotas() == null || romaneio.getNotas().isEmpty())) {
			throw new BusinessException("Romaneio sem Nota Fiscal de Entrada");
		}

	}

	private void validaNotaFiscalDeposito(RomaneioAgricola romaneio) {
		if (romaneio.getNumeroNfDeposito() < 0) {
			throw new BusinessException("Romaneio sem Nota Fiscal Depósito gerada");
		}
	}

	private void validaContratoAgricola(RomaneioAgricola romaneio) {
		if (container.getContratoAgricola() != null && !container.getContratoAgricola().getTipoContratoAgricola()
				.getCaracteristicaContratoAgricola().equals(CaracteristicaContratoAgricola.DEPOSITO_FIXAR)) {

		}
		// TODO VALIDAR CONTRATO AGRICOLA
		// TODO VALIDAR SE ROMANEIO REFERENTE AO CONTRATO AGRICOLA INFORMADO
		// TODO VALIDAR SE ROMANEIO DEVE SER INFORMADO CONTRATO

	}

	private void validaDataRomaneioXDataBaixa(RomaneioAgricola romaneio) {
		if (romaneio.getDataDocumento().isAfter(container.getDataBaixa())) {
			String msg = String.format("Data do Romaneio %s maior que a data de baixa infromada",
					DateUtils.formatDate(romaneio.getDataDocumento()));
			throw new BusinessException(msg);
		}
	}

	private void validaOperacaoInternaParaFixacao() {
		if (container.getOperacaoInternaFixacao().getOperacaoInternaAgricola() == null
				|| !container.getOperacaoInternaFixacao().getOperacaoInternaAgricola()
						.getIdentificacaoDocumentoAgricola().equals(IdentificacaoDocumentoAgricola.FIXACAO)) {
			throw new BusinessException("Operação interna informada para fixação não é permitida para esta finalidade");
		}
		ValidaOperacaoInternaAgricola validacaoOperacaoInternaAgricola = validaOperacaoInternaAgricolaService
				.getValidacaoVigente(container.getItem().getId(), container.getOperacaoInternaFixacao().getId(),
						container.getGrupoOperacaoAgricola().getId(), container.getDataBaixa());
		if (validacaoOperacaoInternaAgricola == null) {
			throw new BusinessException("Não existe parametrização de operação para este item");
		}
	}

}
