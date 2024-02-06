package com.rocketseat.certification_nlw.modules.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificacaoRespostasEntidade {

    private UUID id;
    private UUID certificacaoId;
    private UUID estudanteId;
    private UUID perguntaId;
    private UUID respostaId;
    private boolean estaCorreta;
}
