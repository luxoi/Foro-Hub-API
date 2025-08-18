package com.forohub.forohub.Auth;

import com.forohub.forohub.jwt.JwtService;
import com.forohub.forohub.models.Usuario;

import com.forohub.forohub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registrar(RegisterRequest request) {
        // Crear usuario nuevo
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .correoElectronico(request.getCorreoElectronico())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .role("USER") // o asigna dinámicamente
                .build();

        usuarioRepository.save(usuario);

        // Generar JWT
        String token = jwtService.generarToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse autenticar(LoginRequest request) {
        // Spring Security valida usuario y contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoElectronico(),
                        request.getContrasena()
                )
        );

        // Recuperar usuario
        Usuario usuario = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Generar JWT
        String token = jwtService.generarToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
