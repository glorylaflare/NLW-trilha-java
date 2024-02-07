package com.rocketseat.certification_nlw.modules.questions.entities;

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
@Entity(name = "perguntas")
public class PerguntasEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String tecnologia;

    private String descricao;

    @OneToMany
    @JoinColumn(name = "pergunta_id")
    private List<AlternativasEntidade> alternativasEntidade;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
