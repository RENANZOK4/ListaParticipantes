package com.MundoSenaiTDSN.ListaParticipantes.Service;

import org.springframework.stereotype.Service;

@Service
public class S_LimpaNumero {
    public static String limpar(String numero) {
        // Remover caracteres não numéricos
        return numero.replaceAll("[^0-9]", "");
    }
}
