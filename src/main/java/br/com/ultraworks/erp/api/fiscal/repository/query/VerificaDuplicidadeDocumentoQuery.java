package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeDocumentoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(Documento documento) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeDocumento"), Tuple.class)
				.setParameter("numero", documento.getNumero())
				.setParameter("dataDocumento", documento.getDataDocumento())
				.setParameter("parceiroLocalId", documento.getParceiroLocal().getId())
				.setParameter("operacaoInternaId", documento.getOperacaoInterna().getId())
				.setParameter("valor", documento.getValor())
				.setParameter("validaId", documento.getId() != null ? 0 : 1)
				.setParameter("id", documento.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Documento "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Documento " + documento.getNumero() + 
					", pois existe um Documento cadastrado com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade de Documento");
		}
	}
}
