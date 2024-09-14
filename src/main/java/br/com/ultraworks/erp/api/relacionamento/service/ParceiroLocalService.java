package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;

@Service
public class ParceiroLocalService extends GenericService<ParceiroLocal, Long, ParceiroLocalDTO> {

	ParceiroLocalRepository repository;
	ParceiroLocalMapper mapper;
	private ParceiroLocalFisicaService parceiroFisicaService;
	private ParceiroLocalJuridicaService parceiroJuridicaService;
	private ParceiroLocalTipoParceiroService parceiroLocalTipoParceiroService;
	private ParceiroLocalEnderecoService parceiroLocalEnderecoService;
	private ParceiroLocalTelefoneService parceiroLocalTelefoneService;
	private ParceiroLocalEmailService parceiroLocalEmailService;
	private ParceiroLocalPropriedadeService parceiroLocalPropriedadeService;

	@Autowired
	public ParceiroLocalService(ParceiroLocalRepository repository, ParceiroLocalMapper mapper,
			ParceiroLocalFisicaService parceiroFisicaService, ParceiroLocalJuridicaService parceiroJuridicaService,
			ParceiroLocalTipoParceiroService parceiroLocalTipoParceiroService,
			ParceiroLocalEnderecoService parceiroLocalEnderecoService,
			ParceiroLocalTelefoneService parceiroLocalTelefoneService,
			ParceiroLocalEmailService parceiroLocalEmailService,
			ParceiroLocalPropriedadeService parceiroLocalPropriedadeService) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
		this.parceiroFisicaService = parceiroFisicaService;
		this.parceiroJuridicaService = parceiroJuridicaService;
		this.parceiroLocalTipoParceiroService = parceiroLocalTipoParceiroService;
		this.parceiroLocalEnderecoService = parceiroLocalEnderecoService;
		this.parceiroLocalTelefoneService = parceiroLocalTelefoneService;
		this.parceiroLocalEmailService = parceiroLocalEmailService;
		this.parceiroLocalPropriedadeService = parceiroLocalPropriedadeService;
	}

	public List<ParceiroLocal> getAllByParceiro(Long id) {
		List<ParceiroLocal> listRegistros = new ArrayList<>();

		repository.findByParceiroId(id).forEach(parceiroLocal -> {
			getDadosListasDependentes(parceiroLocal);
			listRegistros.add(parceiroLocal);
		});
		return listRegistros;
	}

	@Override
	public Optional<ParceiroLocal> getById(Long id) {
		Optional<ParceiroLocal> opt = repository.findById(id);
		if (opt.isPresent()) {
			getDadosListasDependentes(opt.get());
			return opt;
		}
		return null;
	}

	private void getDadosListasDependentes(ParceiroLocal parceiroLocal) {
		parceiroLocal.getDadosPessoaFisica().addAll(parceiroFisicaService.getAllByParceiroLocal(parceiroLocal.getId()));
		parceiroLocal.getDadosPessoaJuridica()
				.addAll(parceiroJuridicaService.getAllByParceiroLocal(parceiroLocal.getId()));
		parceiroLocal.getEnderecos().addAll(parceiroLocalEnderecoService.getAllByParceiroLocal(parceiroLocal.getId()));
		parceiroLocal.getTelefones().addAll(parceiroLocalTelefoneService.getAllByParceiroLocal(parceiroLocal.getId()));
		parceiroLocal.getEmails().addAll(parceiroLocalEmailService.getAllByParceiroLocal(parceiroLocal.getId()));
		parceiroLocal.getPropriedades()
				.addAll(parceiroLocalPropriedadeService.getAllByParceiroLocal(parceiroLocal.getId()));
	}

	@Override
	public ParceiroLocal save(ParceiroLocal entity) {
		List<ParceiroLocalFisica> dadosFisicaSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			dadosFisicaSalvos = parceiroFisicaService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalJuridica> dadosJuridicaSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			dadosJuridicaSalvos = parceiroJuridicaService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalTipoParceiro> tiposParceirosSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			tiposParceirosSalvos = parceiroLocalTipoParceiroService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalEndereco> enderecosSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			enderecosSalvos = parceiroLocalEnderecoService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalTelefone> telefonesSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			telefonesSalvos = parceiroLocalTelefoneService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalEmail> emailsSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			emailsSalvos = parceiroLocalEmailService.getAllByParceiroLocal(entity.getId());
		}

		List<ParceiroLocalPropriedade> propriedadesSalvos = new ArrayList<>();
		if (entity.getId() != null) {
			propriedadesSalvos = parceiroLocalPropriedadeService.getAllByParceiroLocal(entity.getId());
		}

		repository.save(entity);

		if (entity.getDadosPessoaFisica() != null && entity.getDadosPessoaFisica().size() > 0) {
			entity.getDadosPessoaFisica().forEach(dadoFisica -> {
				dadoFisica.setParceiroLocal(entity);
				dadoFisica = parceiroFisicaService.save(dadoFisica);
			});
		}

		if (entity.getDadosPessoaJuridica() != null && entity.getDadosPessoaJuridica().size() > 0) {
			entity.getDadosPessoaJuridica().forEach(dadoJuridica -> {
				dadoJuridica.setParceiroLocal(entity);
				dadoJuridica = parceiroJuridicaService.save(dadoJuridica);
			});
		}

		if (entity.getTiposParceiro() != null && entity.getTiposParceiro().size() > 0) {
			entity.getTiposParceiro().forEach(tipoParceiro -> {
				tipoParceiro.setParceiroLocal(entity);
				tipoParceiro = parceiroLocalTipoParceiroService.save(tipoParceiro);
			});
		}

		if (entity.getEnderecos() != null && entity.getEnderecos().size() > 0) {
			entity.getEnderecos().forEach(endereco -> {
				endereco.setParceiroLocal(entity);
				endereco = parceiroLocalEnderecoService.save(endereco);
			});
		}

		if (entity.getTelefones() != null && entity.getTelefones().size() > 0) {
			entity.getTelefones().forEach(tel -> {
				tel.setParceiroLocal(entity);
				tel = parceiroLocalTelefoneService.save(tel);
			});
		}

		if (entity.getEmails() != null && entity.getEmails().size() > 0) {
			entity.getEmails().forEach(email -> {
				email.setParceiroLocal(entity);
				email = parceiroLocalEmailService.save(email);
			});
		}

		if (entity.getPropriedades() != null && entity.getPropriedades().size() > 0) {
			entity.getPropriedades().forEach(propriedade -> {
				propriedade.setParceiroLocal(entity);
				propriedade = parceiroLocalPropriedadeService.save(propriedade);
			});
		}

		List<ParceiroLocalFisica> dadosFisicaExcluir = (List<ParceiroLocalFisica>) ListUtils
				.compararListasERetornaDiferenca(dadosFisicaSalvos, entity.getDadosPessoaFisica());
		if (dadosFisicaExcluir.size() > 0) {
			dadosFisicaExcluir.forEach(local -> {
				parceiroFisicaService.delete(local.getId());
			});
		}

		List<ParceiroLocalJuridica> dadosJuridicaExcluir = (List<ParceiroLocalJuridica>) ListUtils
				.compararListasERetornaDiferenca(dadosJuridicaSalvos, entity.getDadosPessoaJuridica());
		if (dadosJuridicaExcluir.size() > 0) {
			dadosJuridicaExcluir.forEach(local -> {
				parceiroJuridicaService.delete(local.getId());
			});
		}

		List<ParceiroLocalTipoParceiro> tiposParceirosExcluir = (List<ParceiroLocalTipoParceiro>) ListUtils
				.compararListasERetornaDiferenca(tiposParceirosSalvos, entity.getTiposParceiro());
		if (tiposParceirosExcluir.size() > 0) {
			tiposParceirosExcluir.forEach(local -> {
				parceiroLocalTipoParceiroService.delete(local.getId());
			});
		}

		List<ParceiroLocalEndereco> enderecosExcluir = (List<ParceiroLocalEndereco>) ListUtils
				.compararListasERetornaDiferenca(enderecosSalvos, entity.getEnderecos());
		if (enderecosExcluir.size() > 0) {
			enderecosExcluir.forEach(endereco -> {
				parceiroLocalEnderecoService.delete(endereco.getId());
			});
		}

		List<ParceiroLocalTelefone> telefonesExcluir = (List<ParceiroLocalTelefone>) ListUtils
				.compararListasERetornaDiferenca(telefonesSalvos, entity.getTelefones());
		if (telefonesExcluir.size() > 0) {
			telefonesExcluir.forEach(tel -> {
				parceiroLocalTelefoneService.delete(tel.getId());
			});
		}

		List<ParceiroLocalEmail> emailsExcluir = (List<ParceiroLocalEmail>) ListUtils
				.compararListasERetornaDiferenca(emailsSalvos, entity.getEmails());
		if (emailsExcluir.size() > 0) {
			emailsExcluir.forEach(email -> {
				parceiroLocalEmailService.delete(email.getId());
			});
		}

		List<ParceiroLocalPropriedade> propriedadesExcluir = (List<ParceiroLocalPropriedade>) ListUtils
				.compararListasERetornaDiferenca(propriedadesSalvos, entity.getPropriedades());
		if (propriedadesExcluir.size() > 0) {
			propriedadesExcluir.forEach(propriedade -> {
				parceiroLocalPropriedadeService.delete(propriedade.getId());
			});
		}

		return entity;
	}

	@Override
	public void delete(Long id) {
		ParceiroLocal parceiroLocal = repository.findById(id).orElseThrow(RegisterNotFoundException::new);
		parceiroLocal.getDadosPessoaFisica().forEach(local -> {
			parceiroFisicaService.delete(local.getId());
		});
		parceiroLocal.getDadosPessoaJuridica().forEach(local -> {
			parceiroJuridicaService.delete(local.getId());
		});
		parceiroLocal.getEnderecos().forEach(local -> {
			parceiroLocalEnderecoService.delete(local.getId());
		});
		parceiroLocal.getTelefones().forEach(local -> {
			parceiroLocalTelefoneService.delete(local.getId());
		});
		parceiroLocal.getEmails().forEach(local -> {
			parceiroLocalEmailService.delete(local.getId());
		});
		parceiroLocal.getPropriedades().forEach(propriedade -> {
			parceiroLocalPropriedadeService.delete(propriedade.getId());
		});
		repository.deleteById(id);
	}

	public ParceiroLocal findByCpfCnpj(String cpfCnpj) {
		ParceiroLocal parceiroLocal = repository.findByCpfCnpj(cpfCnpj);
		getDadosListasDependentes(parceiroLocal);
		return parceiroLocal;
	}
}
