package com.rocketseat.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certificacoes_respostas_estudantes")
@Builder
public class CertificacaoRespostasEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certificacao_id")
    private UUID certificacaoId;

    @ManyToOne
    @JoinColumn(name = "certificacao_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificacaoEstudanteEntidade certificacaoEstudanteEntidade;

    @Column(name = "estudante_id")
    private UUID estudanteId;

    @ManyToOne
    @JoinColumn(name = "estudante_id", insertable = false, updatable = false)
    private EstudanteEntidade estudanteEntidade;

    @Column(name = "pergunta_id")
    private UUID perguntaId;

    @Column(name = "resposta_id")
    private UUID respostaId;

    @Column(name = "esta_correta")
    private boolean estaCorreta;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
