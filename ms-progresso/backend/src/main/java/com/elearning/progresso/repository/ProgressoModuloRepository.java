package com.elearning.progresso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.progresso.entity.ProgressoModulo;

@Repository
public interface ProgressoModuloRepository extends JpaRepository<ProgressoModulo, Long> {
    List<ProgressoModulo> findByInscricaoId(Long inscricaoId);
}
