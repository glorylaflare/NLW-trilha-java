package com.rocketseat.certification_nlw.modules.students.repositories;

import com.rocketseat.certification_nlw.modules.students.entities.EstudanteEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstudanteRepositorio extends JpaRepository<EstudanteEntidade, UUID> {

    public Optional<EstudanteEntidade> findByEmail(String email);
}
