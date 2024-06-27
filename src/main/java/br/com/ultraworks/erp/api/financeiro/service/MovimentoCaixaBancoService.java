package br.com.ultraworks.erp.api.financeiro.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.MovimentoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoCaixaBancoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MovimentoCaixaBancoService extends GenericService<MovimentoCaixaBanco, Long, MovimentoCaixaBancoDTO> {

	private SaldoCaixaBancoService saldoCaixaBancoService;

	@Autowired
	public MovimentoCaixaBancoService(MovimentoCaixaBancoRepository repository, MovimentoCaixaBancoMapper mapper,
			SaldoCaixaBancoService saldoCaixaBancoService) {
		super(repository, mapper);
		this.saldoCaixaBancoService = saldoCaixaBancoService;
	}

	public MovimentoCaixaBanco save(MovimentoCaixaBanco entity) {
		throw new UnsupportedOperationException("Este método não pode ser usado neste serviço específico.");
	}

	@Transactional
	public MovimentoCaixaBanco inserirMovimento(MovimentoCaixaBanco movimentoCaixaBanco) {

		movimentoCaixaBanco.setId(null);
		movimentoCaixaBanco.setNossoNumero(NossoNumeroGenerator.gerarNossoNumero());
		movimentoCaixaBanco = repository.saveAndFlush(movimentoCaixaBanco);
		saldoCaixaBancoService.atualizaSaldoCaixaBanco(Date.valueOf(movimentoCaixaBanco.getDataMovimento()),
				Date.valueOf(movimentoCaixaBanco.getDataMovimento()), movimentoCaixaBanco.getConta().getId(),
				movimentoCaixaBanco.getCriadoPor());
		return movimentoCaixaBanco;
	}
	
	
}