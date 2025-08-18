package com.forohub.forohub.repository;

import com.forohub.forohub.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends  JpaRepository<Curso, Long> {
}
