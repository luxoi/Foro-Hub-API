package com.forohub.forohub.services;

import com.forohub.forohub.models.Curso;
import com.forohub.forohub.models.Topico;
import com.forohub.forohub.models.Usuario;
import com.forohub.forohub.repository.CursoRepository;
import com.forohub.forohub.repository.TopicoRepository;
import com.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    // Guardar tópico usando solo IDs de autor y curso
    public Topico guardar(Topico topico) {
        // Buscar usuario por id
        Usuario autor = usuarioRepository.findById(topico.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // Buscar curso por id
        Curso curso = cursoRepository.findById(topico.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public Topico actualizar(Long id, Topico datos) {
        Topico existente = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        existente.setTitulo(datos.getTitulo());
        existente.setMensaje(datos.getMensaje());
        // si quieres, también actualizar curso/autor validando ids
        existente.setStatus(datos.getStatus());
        System.out.println("Aquí tu mensaje" + existente.getStatus());

        return topicoRepository.save(existente);
    }



    public void eliminar(Long id) {
        topicoRepository.deleteById(id);
    }
}