package com.rocketseat.certification_nlw.modules.students.repositories;

import com.rocketseat.certification_nlw.modules.students.entities.CertificacaoEstudanteEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificacaoEstudanteRepositorio extends JpaRepository<CertificacaoEstudanteEntidade, UUID> {

    @Query("SELECT c FROM certificacoes c INNER JOIN c.estudanteEntidade std WHERE std.email = :email AND c.tecnologia = :tecnologia")
    List<CertificacaoEstudanteEntidade> findByStudentEmailAndTecnology(String email, String tecnologia);

    @Query("SELECT c FROM certificacoes c ORDER BY c.notas DESC LIMIT 10")
    List<CertificacaoEstudanteEntidade> findTop10ByOrderByGradeDesc();
}
