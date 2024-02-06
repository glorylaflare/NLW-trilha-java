package com.rocketseat.certification_nlw.modules.students.services;

import com.rocketseat.certification_nlw.modules.students.dto.VerificaCertificacaoDto;
import org.springframework.stereotype.Service;

@Service
public class VerificaSeTemCertificacao {

    public boolean execute(VerificaCertificacaoDto dto) {
        if(dto.getEmail().equals("marcelo@email.com") && dto.getTecnologia().equals("Java")) {
            return true;
        }
        return false;
    }
}
