package com.rocketseat.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certificacoes")
@Builder
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resposta_certificacao_id", insertable = false, updatable = false)
    @JsonManagedReference
    List<CertificacaoRespostasEntidade> certificacaoRespostasEntidades;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
