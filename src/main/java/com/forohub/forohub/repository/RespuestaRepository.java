package com.forohub.forohub.repository;

import com.forohub.forohub.models.Respuesta;
import com.forohub.forohub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByTopico(Topico topico);
}