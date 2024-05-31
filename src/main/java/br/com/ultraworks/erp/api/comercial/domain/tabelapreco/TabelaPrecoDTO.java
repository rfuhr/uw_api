package br.com.ultraworks.erp.api.comercial.domain.tabelapreco;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilialDTO;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItemDTO;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TabelaPrecoDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	private Long tipoPrecoId;
	private String tipoPrecoNome;
	private int tipoPrecoCodigo;
	private Long empresaFilialId;
	private String empresaFilialNome;
	private String empresaFilialSigla;
	private Long grupoContabilId;
	private String grupoContabilNome;
	private int grupoContabilCodigo;
	private boolean promocional;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<TabelaPrecoEmpresaFilialDTO> tabelaPrecoEmpresaFiliais;
	
	@Valid
	private List<TabelaPrecoItemDTO> tabelaPrecoItens;
}
