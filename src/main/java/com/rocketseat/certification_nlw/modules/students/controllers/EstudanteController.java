package com.rocketseat.certification_nlw.modules.students.controllers;

import com.rocketseat.certification_nlw.modules.questions.dto.EstudanteCertificacaoRespostaDto;
import com.rocketseat.certification_nlw.modules.students.dto.VerificaCertificacaoDto;
import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoEstudanteEntidade;
import com.rocketseat.certification_nlw.modules.students.services.EstudanteCertificacaoRespostasService;
import com.rocketseat.certification_nlw.modules.students.services.VerificaSeTemCertificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class EstudanteController {

    @Autowired
    private VerificaSeTemCertificacaoService verificaSeTemCertificacaoService;

    @Autowired
    private EstudanteCertificacaoRespostasService estudanteCertificacaoRespostasService;

    @PostMapping("/verify")
    public String verificaCertificacao(@RequestBody VerificaCertificacaoDto dto) {
        var resultado = this.verificaSeTemCertificacaoService.execute(dto);

        if(resultado) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova!";
    }

    @PostMapping("/certificacao/resposta")
    public ResponseEntity<Object> certificacaoResposta(@RequestBody EstudanteCertificacaoRespostaDto dto) throws Exception {
        try {
            var resultado = estudanteCertificacaoRespostasService.execute(dto);
            return ResponseEntity.ok().body(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}