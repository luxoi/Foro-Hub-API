package com.forohub.forohub.services;

import com.forohub.forohub.models.Curso;
import com.forohub.forohub.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    // Crear un curso
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Obtener todos los cursos
    public List<Curso> obtenerCursos() {
        return cursoRepository.findAll();
    }

    // Obtener curso por ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Actualizar curso
//    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
//        Curso curso = cursoRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
//
//        curso.setNombre(cursoActualizado.getNombre());
//        curso.setCategoria(cursoActualizado.getCategoria());
//
//        return cursoRepository.save(curso);
//    }

    // Eliminar curso
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
