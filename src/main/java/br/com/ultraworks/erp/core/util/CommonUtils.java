package br.com.ultraworks.erp.core.util;

public class CommonUtils {
	
	public static int calcularDigitoVerificador(String numero) {
        // Validação da entrada
        if (numero.length() < 1 || numero.length() > 10) {
            throw new IllegalArgumentException("O número deve ter de 1 a 10 caracteres.");
        }

        int soma = 0;
        int peso = 2; // O peso inicia em 2 e aumenta a cada dígito

        // Cálculo da soma ponderada
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            soma += digito * peso;
            peso++;
            if (peso > 9) { // Reseta o peso para 2 após 9
                peso = 2;
            }
        }

        // Cálculo do resto da divisão por 11
        int resto = soma % 11;
        int digitoVerificador;

        // Determinação do dígito verificador com base no resto
        if (resto == 0 || resto == 1) {
            digitoVerificador = 0; // Se resto é 0 ou 1, o dígito é 0
        } else {
            digitoVerificador = 11 - resto; // Caso contrário, é 11 - resto
        }

        return digitoVerificador;
    }
}
