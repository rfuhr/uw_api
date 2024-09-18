package br.com.ultraworks.erp.api.agricola.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.pesagem.Pesagem;
import br.com.ultraworks.erp.api.agricola.domain.pesagem.PesagemDTO;
import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.SituacaoPesagem;
import br.com.ultraworks.erp.api.agricola.repository.PesagemRepository;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalPropriedadeRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PesagemMapper extends GenericMapper<Pesagem, PesagemDTO> {

	private EmpresaFilialRepository empresaFilialRepository;
	private OperacaoInternaService operacaoInternaService;
	private ParceiroLocalRepository parceiroLocalRepository;
	private ParceiroLocalPropriedadeRepository parceiroLocalPropriedadeRepository;
	private ItemRepository itemRepository;
	private SafraRepository safraRepository;
	private PesagemClassificacaoMapper pesagemClassificacaoMapper;

	public PesagemMapper(PesagemRepository pesagemRepository, EmpresaFilialRepository empresaFilialRepository,
			OperacaoInternaService operacaoInternaService, ParceiroLocalRepository parceiroLocalRepository,
			ParceiroLocalPropriedadeRepository parceiroLocalPropriedadeRepository, ItemRepository itemRepository,
			SafraRepository safraRepository, PesagemClassificacaoMapper pesagemClassificacaoMapper) {
		super(pesagemRepository, Pesagem::new, PesagemDTO::new);
		this.empresaFilialRepository = empresaFilialRepository;
		this.operacaoInternaService = operacaoInternaService;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.parceiroLocalPropriedadeRepository = parceiroLocalPropriedadeRepository;
		this.itemRepository = itemRepository;
		this.safraRepository = safraRepository;
		this.pesagemClassificacaoMapper = pesagemClassificacaoMapper;
	}

	@Override
	protected void setValuesToEntity(PesagemDTO dto, Pesagem entity) {
		entity.setId(dto.getId());
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado empresa filial com id " + dto.getEmpresaFilialId())));
		entity.setDataPesagem(dto.getDataPesagem());
		entity.setOperacaoInterna(operacaoInternaService.getById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		entity.setTemCadastro(dto.isTemCadastro());
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
		entity.setNomeParceiro(dto.getNomeParceiro());
		entity.setNomePropriedade(dto.getNomePropriedade());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getSafraId() != null) {
			entity.setSafra(safraRepository.findById(dto.getItemId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado safra com id " + dto.getSafraId())));
		}
		entity.setPesoEntrada(dto.getPesoEntrada());
		entity.setPesoSaida(dto.getPesoSaida());
		entity.setPesoBruto(dto.getPesoBruto());
		entity.setDescontos(dto.getDescontos());
		entity.setPesoLiquido(dto.getPesoLiquido());
		entity.setPlaca1(dto.getPlaca1());
		entity.setPlaca2(dto.getPlaca2());
		entity.setPlaca3(dto.getPlaca3());
		entity.setNomeMotorista(dto.getNomeMotorista());
		entity.setContatoMotorista(dto.getContatoMotorista());
		entity.setObservacao(dto.getObservacao());
		entity.setPesoEntradaManual(dto.isPesoEntradaManual());
		entity.setJustificaticaPesoEntrada(dto.getJustificativaPesoEntrada());
		entity.setPesoSaidaManual(dto.isPesoSaidaManual());
		entity.setJustificaticaPesoSaida(dto.getJustificativaPesoSaida());
		entity.setSituacao(SituacaoPesagem.fromValue(dto.getSituacao()));

		if (dto.getClassificacoes() != null) {
			if (entity.getClassificacoes() == null)
				entity.setClassificacoes(new ArrayList<>());
			else
				entity.getClassificacoes().clear();

			List<PesagemClassificacao> classificacoes = pesagemClassificacaoMapper.toEntity(dto.getClassificacoes());
			classificacoes.forEach(classificacao -> {
				if (classificacao.getPesagem() == null)
					classificacao.setPesagem(entity);
			});
			entity.getClassificacoes().addAll(classificacoes);
		} else {
			entity.getClassificacoes().clear();
		}
	}

	@Override
	protected void setValuesToDto(Pesagem entity, PesagemDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setDataPesagem(entity.getDataPesagem());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setTemCadastro(entity.isTemCadastro());
		if (entity.getParceiroLocal() != null) {
			dto.setParceiroLocalId(entity.getParceiroLocal().getId());
			dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
			dto.setParceiroNome(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		}
		if (entity.getParceiroLocalPropriedade() != null) {
			dto.setParceiroLocalPropriedadeId(entity.getParceiroLocalPropriedade().getId());
			dto.setParceiroLocalPropriedadeNome(entity.getParceiroLocalPropriedade().getIdentificacao());
		}
		dto.setNomeParceiro(entity.getNomeParceiro());
		dto.setNomePropriedade(entity.getNomePropriedade());
		dto.setItemId(entity.getItem().getId());
		if (entity.getSafra() != null) {
			dto.setSafraId(entity.getSafra().getId());
			dto.setSafraNome(entity.getSafra().getNome());

		}
		dto.setPesoEntrada(entity.getPesoEntrada());
		dto.setPesoSaida(entity.getPesoSaida());
		dto.setPesoBruto(entity.getPesoBruto());
		dto.setDescontos(entity.getDescontos());
		dto.setPesoLiquido(entity.getPesoLiquido());
		dto.setPlaca1(entity.getPlaca1());
		dto.setPlaca2(entity.getPlaca2());
		dto.setPlaca3(entity.getPlaca3());
		dto.setNomeMotorista(entity.getNomeMotorista());
		dto.setContatoMotorista(entity.getContatoMotorista());
		dto.setObservacao(entity.getObservacao());
		dto.setPesoEntradaManual(entity.isPesoEntradaManual());
		dto.setJustificativaPesoEntrada(entity.getJustificaticaPesoEntrada());
		dto.setPesoSaidaManual(entity.isPesoSaidaManual());
		dto.setJustificativaPesoSaida(entity.getJustificaticaPesoSaida());
		dto.setSituacao(entity.getSituacao().getValue());

		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setItemNome(entity.getItem().getNome());
		dto.setSituacaoNome(entity.getSituacao().getName());

		if (entity.getClassificacoes() != null && entity.getClassificacoes().size() > 0) {
			dto.setClassificacoes(new ArrayList<>());
			entity.getClassificacoes().forEach(
					classificacao -> dto.getClassificacoes().add(pesagemClassificacaoMapper.toDto(classificacao)));
		}
	}
}
