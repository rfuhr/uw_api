package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigCalculoPrecoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigCalculoPreco configCalculoPreco) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigCalculoPreco"), Tuple.class)
				.setParameter("tipoPrecoId", configCalculoPreco.getTipoPreco().getId())
				.setParameter("dataInicio", configCalculoPreco.getDataInicioVigencia())
				.setParameter("dataFinal", configCalculoPreco.getDataFinalVigencia())
				.setParameter("validaId", configCalculoPreco.getId() != null ? 0 : 1)
				.setParameter("id", configCalculoPreco.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração do Cálculo do Preço "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração do Cálculo do Preço " + configCalculoPreco.getTipoPreco().getNome() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração do Cálculo do Preço");
		}
	}
}
