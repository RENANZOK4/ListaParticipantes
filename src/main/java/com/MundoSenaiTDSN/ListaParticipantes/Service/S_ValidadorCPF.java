package com.MundoSenaiTDSN.ListaParticipantes.Service;

import org.springframework.stereotype.Service;

@Service
public class S_ValidadorCPF {
    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = S_LimpaNumero.limpar(cpf);
        // Verificar se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        // Calcular e verificar os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }

        int resto1 = soma1 % 11;
        int digitoVerificador1 = (resto1 < 2) ? 0 : 11 - resto1;

        if (digitos[9] != digitoVerificador1) {
            return false;
        }

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }

        int resto2 = soma2 % 11;
        int digitoVerificador2 = (resto2 < 2) ? 0 : 11 - resto2;

        return digitos[10] == digitoVerificador2;
    }
}

