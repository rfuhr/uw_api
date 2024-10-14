package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;

public class ContainerSelecaoRomaneioParaBaixa {

	private List<RomaneioSelecaoFixacaoDTO> romaneiosValidos;
	private List<RomaneioProblemaFixacaoDTO> romaneiosProblemas;

	public ContainerSelecaoRomaneioParaBaixa() {
		romaneiosValidos = new ArrayList<>();
		romaneiosProblemas = new ArrayList<>();
	}

	private void addRomaneioValido(RomaneioSelecaoFixacaoDTO romaneio) {
		romaneiosValidos.add(romaneio);
	}

	private void addRomaneioProblema(RomaneioAgricola romaneio, String problema) {
		RomaneioProblemaFixacaoDTO romaneioProblemaFixacaoDTO = new RomaneioProblemaFixacaoDTO();
		romaneiosProblemas.add(romaneioProblemaFixacaoDTO);
	}
}
