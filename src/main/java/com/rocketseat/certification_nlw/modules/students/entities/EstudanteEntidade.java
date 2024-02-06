package com.rocketseat.certification_nlw.modules.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudanteEntidade {

    private UUID id;
    private String email;
    private List<CertificacaoEstudanteEntidade> certificacaoEstudanteEntidade;
}
