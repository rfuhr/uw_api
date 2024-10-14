package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.PrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PrecoAgricolaMapper extends GenericMapper<PrecoAgricola, PrecoAgricolaDTO> {

	private ItemRepository itemRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;
	private EmpresaRepository empresaRepository;
	private EmpresaFilialRepository empresaFilialRepository;
	private DepartamentoRepository departamentoRepository;
	private PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository;
	private SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository;

	public PrecoAgricolaMapper(PrecoAgricolaRepository repository, ItemRepository itemRepository,
			EmpresaRepository empresaRepository, EmpresaFilialRepository empresaFilialRepository,
			DepartamentoRepository departamentoRepository,
			SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository,
			PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository,
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository) {
		super(repository, PrecoAgricola::new, PrecoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.empresaRepository = empresaRepository;
		this.empresaFilialRepository = empresaFilialRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
		this.departamentoRepository = departamentoRepository;
		this.predefinicaoPrecoAgricolaRepository = predefinicaoPrecoAgricolaRepository;
		this.subItemClassificacaoAgricolaRepository = subItemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(PrecoAgricolaDTO dto, PrecoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}
		if (dto.getEmpresaId() != null) {
			entity.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado empresa com id " + dto.getEmpresaId())));
		}
		if (dto.getEmpresaFilialId() != null) {
			entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado filial com id " + dto.getEmpresaFilialId())));
		}
		if (dto.getDepartamentoId() != null) {
			entity.setDepartamento(departamentoRepository.findById(dto.getDepartamentoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado departamento com id " + dto.getDepartamentoId())));
		}
		if (dto.getPredefinicaoPrecoAgricolaId() != null) {
			entity.setPredefinicaoPrecoAgricola(
					predefinicaoPrecoAgricolaRepository.findById(dto.getPredefinicaoPrecoAgricolaId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado predefinição de preço agrícola com id "
									+ dto.getPredefinicaoPrecoAgricolaId())));
		}
		if (dto.getNivelClass1Id() != null) {
			entity.setNivelClass1(subItemClassificacaoAgricolaRepository.findById(dto.getNivelClass1Id())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 1 de classificação com id " + dto.getNivelClass1Id())));
		}
		if (dto.getNivelClass2Id() != null) {
			entity.setNivelClass2(subItemClassificacaoAgricolaRepository.findById(dto.getNivelClass2Id())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 2 de classificação com id " + dto.getNivelClass2Id())));
		}
		if (dto.getNivelClass3Id() != null) {
			entity.setNivelClass3(subItemClassificacaoAgricolaRepository.findById(dto.getNivelClass3Id())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 3 de classificação com id " + dto.getNivelClass3Id())));
		}
		if (dto.getNivelClass4Id() != null) {
			entity.setNivelClass4(subItemClassificacaoAgricolaRepository.findById(dto.getNivelClass4Id())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado nivel 4 de classificação com id " + dto.getNivelClass4Id())));
		}
		entity.setValorUnitario(dto.getValorUnitario());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(PrecoAgricola entity, PrecoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setItemNome(entity.getItem().getNome());

		dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
		dto.setTipoPrecoAgricolaNome(entity.getTipoPrecoAgricola().getNome());
		if (entity.getEmpresa() != null) {
			dto.setEmpresaId(entity.getEmpresa().getId());
			dto.setEmpresaNome(entity.getEmpresa().getNome());
		}
		if (entity.getEmpresaFilial() != null) {
			dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
			dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		}
		if (entity.getDepartamento() != null) {
			dto.setDepartamentoId(entity.getDepartamento().getId());
			dto.setDepartamentoNome(entity.getDepartamento().getNome());
		}
		dto.setPredefinicaoPrecoAgricolaId(entity.getPredefinicaoPrecoAgricola().getId());
		dto.setPredefinicaoPrecoAgricolaNome(entity.getPredefinicaoPrecoAgricola().getNome());

		if (entity.getNivelClass1() != null)
			dto.setNivelClass1Id(entity.getNivelClass1().getId());
		if (entity.getNivelClass2() != null)
			dto.setNivelClass1Id(entity.getNivelClass2().getId());
		if (entity.getNivelClass3() != null)
			dto.setNivelClass3Id(entity.getNivelClass3().getId());
		if (entity.getNivelClass4() != null)
			dto.setNivelClass1Id(entity.getNivelClass4().getId());

		dto.setValorUnitario(entity.getValorUnitario());

		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
