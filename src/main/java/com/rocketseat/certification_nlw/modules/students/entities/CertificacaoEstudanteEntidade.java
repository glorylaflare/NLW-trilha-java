package com.rocketseat.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certificacoes")
public class CertificacaoEstudanteEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "estudante_id")
    private UUID estudanteId;

    @Column(length = 100)
    private String tecnologia;

    @Column(length = 10)
    private Integer notas;

    @ManyToOne
    @JoinColumn(name = "estudante_id", insertable = false, updatable = false)
    private EstudanteEntidade estudanteEntidade;

    @OneToMany
    @JoinColumn(name = "resposta_certificacao_id", insertable = false, updatable = false)
    List<CertificacaoRespostasEntidade> certificacaoRespostasEntidades;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
