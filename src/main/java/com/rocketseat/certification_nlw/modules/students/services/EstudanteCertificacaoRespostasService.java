package com.rocketseat.certification_nlw.modules.students.services;

import com.rocketseat.certification_nlw.modules.questions.dto.EstudanteCertificacaoRespostaDto;
import com.rocketseat.certification_nlw.modules.questions.entities.PerguntasEntidade;
import com.rocketseat.certification_nlw.modules.questions.repositories.PerguntaRepositorio;
import com.rocketseat.certification_nlw.modules.students.dto.VerificaCertificacaoDto;
import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoEstudanteEntidade;
import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoRespostasEntidade;
import com.rocketseat.certification_nlw.modules.students.entities.EstudanteEntidade;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificacaoEstudanteRepositorio;
import com.rocketseat.certification_nlw.modules.students.repositories.EstudanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EstudanteCertificacaoRespostasService {

    @Autowired
    private EstudanteRepositorio estudanteRepositorio;
    @Autowired
    private PerguntaRepositorio perguntaRepositorio;

    @Autowired
    private CertificacaoEstudanteRepositorio certificacaoEstudanteRepositorio;

    @Autowired
    private VerificaSeTemCertificacaoService verificaSeTemCertificacaoService;

    public CertificacaoEstudanteEntidade execute(EstudanteCertificacaoRespostaDto dto) throws Exception {
        var SeTemCertificacao = this.verificaSeTemCertificacaoService.execute(new VerificaCertificacaoDto(dto.getEmail(), dto.getTecnologia()));
        if(SeTemCertificacao) {
            throw new Exception("Você já tirou sua certificação!");
        }


        List<PerguntasEntidade> perguntasEntidade = perguntaRepositorio.findByTecnologia(dto.getTecnologia());
        List<CertificacaoRespostasEntidade> respostasCertificacao = new ArrayList<>();

        AtomicInteger respostasCorretas = new AtomicInteger(0);

        dto.getPerguntasERespostasDto().stream().forEach(perguntaResposta -> {
            var pergunta = perguntasEntidade.stream()
                    .filter(p -> p.getId()
                            .equals(perguntaResposta.getPerguntaId()));

            var encontrarAlternativaCorreta = pergunta.findFirst().get()
                    .getAlternativasEntidade().stream()
                    .filter(alternativa -> alternativa.isEstaCorreta())
                    .findFirst().get();

            if(encontrarAlternativaCorreta.getId().equals(perguntaResposta.getAlternativaId())) {
                perguntaResposta.setEstaCorreto(true);
                respostasCorretas.getAndIncrement();
            } else {
                perguntaResposta.setEstaCorreto(false);
            }

            var certificacaoRespostaEntidade = CertificacaoRespostasEntidade.builder()
                    .respostaId(perguntaResposta.getAlternativaId())
                    .perguntaId(perguntaResposta.getPerguntaId())
                    .estaCorreta(perguntaResposta.isEstaCorreto()).build();

            respostasCertificacao.add(certificacaoRespostaEntidade);
        });

        var estudante = estudanteRepositorio.findByEmail(dto.getEmail());
        UUID estudanteId;

        if (estudante.isEmpty()) {
            var estudanteCriado = EstudanteEntidade.builder().email(dto.getEmail()).build();
            estudanteCriado = estudanteRepositorio.save(estudanteCriado);
            estudanteId = estudanteCriado.getId();
        } else {
            estudanteId = estudante.get().getId();
        }

        CertificacaoEstudanteEntidade certificacaoEstudanteEntidade =
                CertificacaoEstudanteEntidade.builder()
                        .tecnologia(dto.getTecnologia())
                        .estudanteId(estudanteId)
                        .notas(respostasCorretas.get())
                        .build();

        var certificacaoEstudanteCriada = certificacaoEstudanteRepositorio.save(certificacaoEstudanteEntidade);

        respostasCertificacao.stream().forEach(certificacaoResposta -> {
            certificacaoResposta.setCertificacaoId(certificacaoEstudanteEntidade.getId());
            certificacaoResposta.setCertificacaoEstudanteEntidade(certificacaoEstudanteEntidade);
        });

        certificacaoEstudanteEntidade.setCertificacaoRespostasEntidades(respostasCertificacao);

        certificacaoEstudanteRepositorio.save(certificacaoEstudanteEntidade);

        return certificacaoEstudanteCriada;
    }
}
