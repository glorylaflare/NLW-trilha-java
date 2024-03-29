package com.rocketseat.certification_nlw.modules.questions.repositories;

import com.rocketseat.certification_nlw.modules.questions.entities.PerguntasEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PerguntaRepositorio extends JpaRepository<PerguntasEntidade, UUID> {

    List<PerguntasEntidade> findByTecnologia(String tecnologia);
}
