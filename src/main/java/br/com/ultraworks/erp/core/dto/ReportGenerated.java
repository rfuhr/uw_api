package br.com.ultraworks.erp.core.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportGenerated {

	private String nameReport;
	private byte[] bytes;
}
