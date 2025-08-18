package com.forohub.forohub.services;


import com.forohub.forohub.models.Respuesta;
import com.forohub.forohub.models.Topico;
import com.forohub.forohub.repository.RespuestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;

    public RespuestaService(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    public List<Respuesta> listarPorTopico(Topico topico) {
        return respuestaRepository.findByTopico(topico);
    }

    public Optional<Respuesta> buscarPorId(Long id) {
        return respuestaRepository.findById(id);
    }

    public Respuesta guardar(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public void eliminar(Long id) {
        respuestaRepository.deleteById(id);
    }
}
