package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.ParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividade;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;

@Service
public class ParceiroService extends GenericService<Parceiro, Long, ParceiroDTO> {

	ParceiroRepository repository;
	ParceiroMapper mapper;
	private ParceiroLocalService parceiroLocalService;
	private ParceiroRegraAtividadeService parceiroRegraAtividadeService;

	@Autowired
	public ParceiroService(ParceiroRepository repository, ParceiroMapper mapper,
			ParceiroLocalService parceiroLocalService, ParceiroRegraAtividadeService parceiroRegraAtividadeService) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
		this.parceiroLocalService = parceiroLocalService;
		this.parceiroRegraAtividadeService = parceiroRegraAtividadeService;
	}

	@Override
	public Optional<Parceiro> getById(Long id) {
		Optional<Parceiro> parceiro = repository.findById(id);
		if (parceiro.isPresent()) {
			parceiro.get().getLocais().addAll(parceiroLocalService.getAllByParceiro(parceiro.get().getId()));
			List<ParceiroRegraAtividade> regras = parceiroRegraAtividadeService.getAllByParceiro(parceiro.get().getId());
			if (!regras.isEmpty())
				parceiro.get().getRegras().addAll(regras);
		}
		return parceiro;
	}

	@Override
	public Parceiro save(Parceiro entity) {
		List<ParceiroLocal> locaisSalvos = new ArrayList<ParceiroLocal>();
		if (entity.getId() != null) {
			locaisSalvos = parceiroLocalService.getAllByParceiro(entity.getId());
		}
		List<ParceiroRegraAtividade> regrasSalvos = new ArrayList<ParceiroRegraAtividade>();
		if (entity.getId() != null) {
			regrasSalvos = parceiroRegraAtividadeService.getAllByParceiro(entity.getId());
		}
		repository.save(entity);

		if (entity.getLocais() != null && entity.getLocais().size() > 0) {
			entity.getLocais().forEach(local -> {
				local.setParceiro(entity);
				local = parceiroLocalService.save(local);
			});
		}
		
		if (entity.getRegras() != null && entity.getRegras().size() > 0) {
			entity.getRegras().forEach(regra -> {
				regra.setParceiro(entity);
				regra = parceiroRegraAtividadeService.save(regra);
			});
		}

		List<ParceiroLocal> locaisExcluir = (List<ParceiroLocal>) ListUtils
				.compararListasERetornaDiferenca(locaisSalvos, entity.getLocais());
		if (locaisExcluir.size() > 0) {
			locaisExcluir.forEach(local -> {
				parceiroLocalService.delete(local.getId());
			});
		}
		
		List<ParceiroRegraAtividade> regrasExcluir = (List<ParceiroRegraAtividade>) ListUtils
				.compararListasERetornaDiferenca(regrasSalvos, entity.getRegras());
		if (regrasExcluir.size() > 0) {
			regrasExcluir.forEach(regra -> {
				parceiroRegraAtividadeService.delete(regra.getId());
			});
		}

		return entity;
	}

	@Override
	public void delete(Long id) {
		Optional<Parceiro> parceiro = this.getById(id);
		if (parceiro.isPresent()) {
			parceiro.get().getLocais().forEach(local -> {
				parceiroLocalService.delete(local.getId());
			});
			parceiro.get().getRegras().forEach(regra -> {
				parceiroRegraAtividadeService.delete(regra.getId());
			});
			repository.deleteById(id);
		}
	}
}
