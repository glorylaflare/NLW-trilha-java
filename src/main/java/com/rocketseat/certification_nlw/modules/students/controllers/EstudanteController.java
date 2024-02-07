package com.rocketseat.certification_nlw.modules.students.controllers;

import com.rocketseat.certification_nlw.modules.students.dto.VerificaCertificacaoDto;
import com.rocketseat.certification_nlw.modules.students.services.VerificaSeTemCertificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class EstudanteController {

    @Autowired
    private VerificaSeTemCertificacao verificaSeTemCertificacao;

    @PostMapping("/verify")
    private String verificaCertificacao(@RequestBody VerificaCertificacaoDto dto) {
        var resultado = this.verificaSeTemCertificacao.execute(dto);

        if(resultado) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova!";
    }
}