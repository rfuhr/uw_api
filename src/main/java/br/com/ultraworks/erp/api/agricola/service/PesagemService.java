package br.com.ultraworks.erp.api.agricola.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.pesagem.Pesagem;
import br.com.ultraworks.erp.api.agricola.domain.pesagem.PesagemDTO;
import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.SituacaoPesagem;
import br.com.ultraworks.erp.api.agricola.mapper.PesagemMapper;
import br.com.ultraworks.erp.api.agricola.repository.PesagemRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PesagemService extends GenericService<Pesagem, Long, PesagemDTO> {

	private PesagemClassificacaoService pesagemClassificacaoService;

	@Autowired
	public PesagemService(PesagemRepository repository, PesagemMapper mapper,
			PesagemClassificacaoService pesagemClassificacaoService) {
		super(repository, mapper);
		this.pesagemClassificacaoService = pesagemClassificacaoService;
	}

	@Override
	public Optional<Pesagem> getById(Long id) {
		Optional<Pesagem> pesagem = repository.findById(id);
		if (pesagem.isPresent()) {
			List<PesagemClassificacao> classificacoes = pesagemClassificacaoService.findByPesagemId(pesagem.get().getId());
			if (classificacoes != null && classificacoes.size() > 0) {
				pesagem.get().setClassificacoes(new ArrayList<>());
				pesagem.get().getClassificacoes()
					.addAll(classificacoes);
			}
		}
		return pesagem;
	}

	@Override
	public Pesagem save(Pesagem pesagem) {
		validarDadosPesagem(pesagem);

		Pesagem pesagemSaved = repository.save(pesagem);
		if (pesagem.getClassificacoes() != null)
			pesagem.getClassificacoes().forEach(classificacao -> classificacao.setPesagem(pesagemSaved));
		pesagemClassificacaoService.persistList(pesagemSaved.getId(), pesagemSaved.getClassificacoes());

		return pesagem;
	}

	@Override
	public void delete(Long id) {
		Pesagem pesagem = repository.findById(id).orElseThrow(RegisterNotFoundException::new);

		if (pesagem.getSituacao().equals(SituacaoPesagem.CANCELADO)) {
			throw new BusinessException("Pesagem já está cancelada");
		}

		pesagem.setSituacao(SituacaoPesagem.CANCELADO);
		repository.save(pesagem);
	}

	private void validarDadosPesagem(Pesagem pesagem) {
		if (!pesagem.getDataPesagem().isEqual(LocalDate.now())) {
			throw new BusinessException("Sem autonomia para informar data de pesagem diferente da data atual");
		}
		if (pesagem.getDataPesagem().isAfter(LocalDate.now())) {
			throw new BusinessException("Data da pesagem não pode ser maior que a data atual");
		}

		if (pesagem.getOperacaoInterna() == null) {
			throw new BusinessException("Operação interna deve ser informada");
		}

		if (!pesagem.getOperacaoInterna().isCaracteristicaAgricola()) {
			throw new BusinessException(
					"Operação interna deve ter característica agrícola para ser utilizada na pesagem");
		}

		if (!pesagem.getOperacaoInterna().getOperacaoInternaAgricola().isSelecionaPesagem()) {
			throw new BusinessException("Operação interna não pode ser utilizada na pesagem");
		}
		if (pesagem.getSituacao() == null) {
			throw new BusinessException("Situação da pesagem deve ser informada");
		}

		if (pesagem.isTemCadastro()) {
			if (pesagem.getParceiroLocal() == null) {
				throw new BusinessException("Local de parceiro deve ser informado");
			}
			if (pesagem.getParceiroLocalPropriedade() == null) {
				throw new BusinessException("Propriedade de parceiro deve ser informada");
			}
		} else {
			if (StringUtils.isAnyBlank(pesagem.getNomeParceiro(), pesagem.getNomePropriedade())) {
				throw new BusinessException("Nome do parceiro e nome de propriedade devem ser informados");
			}
		}

		if (pesagem.getItem() == null) {
			throw new BusinessException("Produto deve ser informado");
		}

		if (!pesagem.getItem().isInformaPesagemAgricola()) {
			throw new BusinessException("Produto não pode ser informado na pesagem");
		}

		if (pesagem.getItem().isInformaSafraAgricola()) {
			if (pesagem.getSafra() == null) {
				throw new BusinessException("Safra deve ser informada");
			}
		} else {
			if (pesagem.getSafra() != null) {
				throw new BusinessException("Não deve ser informado safra para este produto");
			}
		}

		if (pesagem.getSituacao().equals(SituacaoPesagem.FINALIZADA)) {
			if (pesagem.getOperacaoInterna().getNaturezaOperacao().getIndicadorOperacao()
					.equals(IndicadorOperacao.ENTRADA)) {
				if (pesagem.getPesoSaida().compareTo(pesagem.getPesoEntrada()) > 0) {
					throw new BusinessException("O peso de saída não pode ser maior que o peso de entrada");
				}
				BigDecimal pesoBruto = pesagem.getPesoEntrada().subtract(pesagem.getPesoSaida());
				if (!pesagem.getPesoBruto().equals(pesoBruto)) {
					throw new BusinessException("O peso bruto informado é diferente do peso bruto calculado");
				}
			} else {
				if (pesagem.getPesoEntrada().compareTo(pesagem.getPesoSaida()) > 0) {
					throw new BusinessException("O peso de entrada não pode ser maior que o peso de saída");
				}
				BigDecimal pesoBruto = pesagem.getPesoSaida().subtract(pesagem.getPesoEntrada());
				if (!pesagem.getPesoBruto().equals(pesoBruto)) {
					throw new BusinessException("O peso bruto informado é diferente do peso bruto calculado");
				}
			}

			if (pesagem.getPesoBruto().compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("O peso bruto deve ser maior ou igual a 0");
			}

			if (pesagem.getPesoLiquido().compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("O peso líquido deve ser maior ou igual a 0");
			}

			if (pesagem.getPesoLiquido().compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("O peso líquido deve ser maior ou igual a 0");
			}

			if (pesagem.getDescontos().compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("A quantidade de descontos deve ser maior ou igual a 0");
			}

			BigDecimal pesoLiquido = pesagem.getPesoBruto().subtract(pesagem.getDescontos());
			if (!pesagem.getPesoLiquido().equals(pesoLiquido)) {
				throw new BusinessException("O peso líquido informado é diferente do peso líquido calculado");
			}
			
			BigDecimal quantidadeDescontoCalculado = BigDecimal.ZERO; 
			
			if (pesagem.getClassificacoes() != null) {
				quantidadeDescontoCalculado = pesagem.getClassificacoes().stream()
	                   .map(PesagemClassificacao::getDesconto)
	                   .reduce(BigDecimal.ZERO, BigDecimal::add);
			}
			
			if (!pesagem.getDescontos().equals(quantidadeDescontoCalculado)) {
				throw new BusinessException("A quantidade de descontos informado é diferente da quantidade calculada");
			}
		}
		if (StringUtils.isBlank(pesagem.getPlaca1())) {
			throw new BusinessException("A placa 1 deve ser informada");
		}

		if (StringUtils.isBlank(pesagem.getNomeMotorista())) {
			throw new BusinessException("O nome do motorista deve ser informado");
		}

		if (StringUtils.isBlank(pesagem.getContatoMotorista())) {
			throw new BusinessException("O contato do motorista deve ser informado");
		}

		if (pesagem.isPesoEntradaManual() && StringUtils.isBlank(pesagem.getJustificaticaPesoEntrada())) {
			throw new BusinessException("A justificativa da pesagem manual de entrada deve ser informada");
		}

		if (pesagem.isPesoSaidaManual() && StringUtils.isBlank(pesagem.getJustificaticaPesoSaida())) {
			throw new BusinessException("A justificativa da pesagem manual de saída deve ser informada");
		}
	}

	public void finalizarPesagem(Long id) {
		Pesagem pesagem = repository.findById(id).orElseThrow(RegisterNotFoundException::new);

		if (pesagem.getSituacao().equals(SituacaoPesagem.CANCELADO)) {
			throw new BusinessException("Pesagem já está cancelada, não é possível finalizar");
		}

		if (pesagem.getSituacao().equals(SituacaoPesagem.FINALIZADA)) {
			throw new BusinessException("Pesagem já está finalizada");
		}

		pesagem.setSituacao(SituacaoPesagem.FINALIZADA);
		repository.save(pesagem);
	}
}