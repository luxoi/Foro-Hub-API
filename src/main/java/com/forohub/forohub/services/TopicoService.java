package com.example.foro.service;

import com.example.foro.model.Topico;
import com.example.foro.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Service
    public class TopicoService {

        @Autowired
        private TopicoRepository topicoRepository;


    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico guardar(Topico topico) {
        return topicoRepository.save(topico);
    }

    public Topico actualizar(Long id, Topico topicoActualizado) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(topicoActualizado.getTitulo());
                    topico.setMensaje(topicoActualizado.getMensaje());
                    topico.setStatus(topicoActualizado.getStatus());
                    topico.setCurso(topicoActualizado.getCurso());
                    topico.setAutor(topicoActualizado.getAutor());
                    return topicoRepository.save(topico);
                })
                .orElse(null);
    }

    public void eliminar(Long id) {
        topicoRepository.deleteById(id);
    }
}
