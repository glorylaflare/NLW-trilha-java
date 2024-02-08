package com.rocketseat.certification_nlw.modules.certifications.services;

import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoEstudanteEntidade;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificacaoEstudanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ranking10Service {

    @Autowired
    private CertificacaoEstudanteRepositorio certificacaoEstudanteRepositorio;

    public List<CertificacaoEstudanteEntidade> execute() {
        var resultado = this.certificacaoEstudanteRepositorio.findTop10ByOrderByGradeDesc();
        return resultado;
    }
}
