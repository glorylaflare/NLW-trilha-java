package com.rocketseat.certification_nlw.modules.certifications.controllers;

import com.rocketseat.certification_nlw.modules.certifications.services.Ranking10Service;
import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoEstudanteEntidade;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificacaoEstudanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Ranking10Service ranking10Service;

    @GetMapping("/top10")
    public List<CertificacaoEstudanteEntidade> top10() {
        return this.ranking10Service.execute();
    }
}
