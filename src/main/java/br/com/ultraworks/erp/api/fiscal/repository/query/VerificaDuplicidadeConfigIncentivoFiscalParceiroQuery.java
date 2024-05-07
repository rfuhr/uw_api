package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigIncentivoFiscalParceiroQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigIncentivoFiscalParceiro configIncentivoFiscalParceiro) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigIncentivoFiscalParceiro"), Tuple.class)
				.setParameter("parceiroLocalId", configIncentivoFiscalParceiro.getParceiroLocal().getId())
				.setParameter("tipoIncentivoFiscalId", configIncentivoFiscalParceiro.getConfigIncentivoFiscal().getTipoIncentivoFiscal().getId())
				.setParameter("dataInicio", configIncentivoFiscalParceiro.getDataInicioVigencia())
				.setParameter("dataFinal", configIncentivoFiscalParceiro.getDataFinalVigencia())
				.setParameter("validaId", configIncentivoFiscalParceiro.getId() != null ? 0 : 1)
				.setParameter("id", configIncentivoFiscalParceiro.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Incentivo Fiscal do Parceiro " + configIncentivoFiscalParceiro.getParceiroLocal().getParceiro().getNomeRazaoSocial(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração de Incentivo Fiscal do Parceiro " + configIncentivoFiscalParceiro.getParceiroLocal().getParceiro().getNomeRazaoSocial() + 
					", pois existe uma Configuração cadastrada na Configuração de Incentivo Fiscal com o Identificador: " + resultTuples.iterator().next().get("config_incentivo_fiscal_id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração de Incentivo Fiscal do Parceiro");
		}
	}
}
