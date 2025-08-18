package com.forohub.forohub.services;

import com.forohub.forohub.models.Usuario;
import com.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

//    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
//        return usuarioRepository.findById(id)
//                .map(usuario -> {
//                    usuario.setNombre(usuarioActualizado.getNombre());
//                    usuario.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
//                    usuario.setContrasena(usuarioActualizado.getContrasena());
//                    usuario.setPerfiles(usuarioActualizado.getPerfiles());
//                    return usuarioRepository.save(usuario);
//                })
//                .orElse(null);
//    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}