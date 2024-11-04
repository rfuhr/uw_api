package br.com.ultraworks.erp.api.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabil;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ValidaDepartamentoGrupoContabilRepository extends UWRepository<ValidaDepartamentoGrupoContabil, Long> {

	@Query(value = "select vdgc.*			 											"
			+ " from   valida_departamento_grupo_contabil vdgc							"
			+ " where  vdgc.departamento_id = :departamentoId 							"
			+ " and    vdgc.grupo_contabil_id = :grupoContabilId						"
			+ " union 																	"
			+ " select vdgc.*															"
			+ " from   valida_departamento_grupo_contabil vdgc							"
			+ " 	   join departamento d on d.id = vdgc.departamento_id				"
			+ " where  vdgc.grupo_contabil_id = :grupoContabilId						"
			+ " and    d.general = true													"
			+ " union 																	"
			+ " select vdgc.*															"
			+ " from   valida_departamento_grupo_contabil vdgc							"
			+ " 	   join grupo_contabil gc on gc.id = vdgc.grupo_contabil_id			"
			+ " where  vdgc.departamento_id   = :departamentoId 						"
			+ " and    gc.general = true												"
			+ " union 																	"
			+ " select vdgc.*															"
			+ " from   valida_departamento_grupo_contabil vdgc							"
			+ " 	   join departamento d on d.id = vdgc.departamento_id				"
			+ " 	   join grupo_contabil gc on gc.id = vdgc.grupo_contabil_id			"
			+ " where  gc.general = true and d.general = true	limit 1					", nativeQuery = true)	
	Optional<ValidaDepartamentoGrupoContabil> getValidacao(Long departamentoId, Long grupoContabilId);

}
