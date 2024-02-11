package br.com.ultraworks.erp.api.tabela.domain.cidade;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.tabela.domain.pais.PaisDTO;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CidadeDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String nome;
	@NotNull
	private Long codigoIBGE;	
	private String siglaPais;
	private String siglaUF;
	private PaisDTO pais;
	private UfDTO uf;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Boolean capital;

}
