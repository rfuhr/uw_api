package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMensagemFiscalItemQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMensagemFiscalItem entity) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMensagemFiscalItem"), Tuple.class)
				.setParameter("mensagemFiscalId", entity.getConfigMensagemFiscal().getMensagemFiscal().getId())
				.setParameter("itemId", entity.getItem().getId())
				.setParameter("dataInicio", entity.getDataInicioVigencia())
				.setParameter("dataFinal", entity.getDataFinalVigencia())
				.setParameter("validaId", entity.getId() != null ? 0 : 1)
				.setParameter("id", entity.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração de Mensagem Fiscal - Item "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Item " + entity.getItem().getId() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração de Mensagem Fiscal - Item");
		}
	}
}
