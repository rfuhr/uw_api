package br.com.ultraworks.erp.core.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationErrorResponse {
	
    private List<ValidationError> errors = new ArrayList<>();
}
