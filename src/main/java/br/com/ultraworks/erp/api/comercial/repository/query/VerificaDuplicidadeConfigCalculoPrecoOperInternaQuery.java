package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigCalculoPrecoOperInternaQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigCalculoPrecoOperInterna configCalculoPrecoOperInterna) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigCalculoPrecoOperInterna"), Tuple.class)
				.setParameter("operacaoInternaId", configCalculoPrecoOperInterna.getOperacaoInterna().getId())
				.setParameter("tipoPrecoId", configCalculoPrecoOperInterna.getConfigCalculoPreco().getTipoPreco().getId())
				.setParameter("dataInicio", configCalculoPrecoOperInterna.getDataInicioVigencia())
				.setParameter("dataFinal", configCalculoPrecoOperInterna.getDataFinalVigencia())
				.setParameter("validaId", configCalculoPrecoOperInterna.getId() != null ? 0 : 1)
				.setParameter("id", configCalculoPrecoOperInterna.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Operação Interna da Configuração de Cálculo de Preço  " + configCalculoPrecoOperInterna.getOperacaoInterna().getNome(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Índice da Configuração de Mark Up do Item " + configCalculoPrecoOperInterna.getOperacaoInterna().getNome() + 
					", pois existe uma Configuração cadastrada na Configuração de Cálculo de Preço com o Identificador: " + resultTuples.iterator().next().get("config_calculo_preco_id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Operação Interna da Configuração de Cálculo de Preço");
		}
	}
}
