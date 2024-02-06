package com.rocketseat.certification_nlw.modules.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificacaoEstudanteEntidade {

    private UUID id;
    private UUID estudanteId;
    private String tecnologia;
    private int notas;
    List<CertificacaoRespostasEntidade> certificacaoRespostasEntidades;
}
