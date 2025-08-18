package com.forohub.forohub.controller;


import com.forohub.forohub.models.Respuesta;
import com.forohub.forohub.models.Topico;
import com.forohub.forohub.services.RespuestaService;
import com.forohub.forohub.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    @Autowired
    private RespuestaService respuestaService;
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerPorId(@PathVariable Long id) {
        return topicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public Topico crear(@RequestBody Topico topico) {
        return topicoService.guardar(topico);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @RequestBody Topico topico) {
//        Topico actualizado = topicoService.actualizar(id, topico);
//        if (actualizado == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(actualizado);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



    //RESPUESTAS CONTROLLER
    @GetMapping("/{topicoId}/respuestas")
    public ResponseEntity<List<Respuesta>> listarRespuestas(@PathVariable Long topicoId) {
        Topico topico = topicoService.buscarPorId(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return ResponseEntity.ok(respuestaService.listarPorTopico(topico));
    }

    @PostMapping("/{topicoId}/respuestas")
    public ResponseEntity<Respuesta> crearRespuesta(@PathVariable Long topicoId, @RequestBody Respuesta respuesta) {
        Topico topico = topicoService.buscarPorId(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        respuesta.setTopico(topico);
        return ResponseEntity.ok(respuestaService.guardar(respuesta));
    }


}
