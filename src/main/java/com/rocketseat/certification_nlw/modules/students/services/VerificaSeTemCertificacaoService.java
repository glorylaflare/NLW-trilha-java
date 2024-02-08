package com.rocketseat.certification_nlw.modules.students.services;

import com.rocketseat.certification_nlw.modules.students.dto.VerificaCertificacaoDto;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificacaoEstudanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaSeTemCertificacaoService {

    @Autowired
    private CertificacaoEstudanteRepositorio certificacaoEstudanteRepositorio;

    public boolean execute(VerificaCertificacaoDto dto) {
        var result = this.certificacaoEstudanteRepositorio.findByStudentEmailAndTecnology(dto.getEmail(), dto.getTecnologia());
        if(!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
