package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMensagemFiscalQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMensagemFiscal configMensagemFiscal) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMensagemFiscal"), Tuple.class)
				.setParameter("mensagemFiscalId", configMensagemFiscal.getMensagemFiscal().getId())
				.setParameter("dataInicio", configMensagemFiscal.getDataInicioVigencia())
				.setParameter("dataFinal", configMensagemFiscal.getDataFinalVigencia())
				.setParameter("validaId", configMensagemFiscal.getId() != null ? 0 : 1)
				.setParameter("id", configMensagemFiscal.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração de Mensagem Fiscal "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração de Mensagem Fiscal " + configMensagemFiscal.getMensagemFiscal().getNome() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração de Mensagem Fiscal");
		}
	}
}
