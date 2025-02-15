package es.ufv.dis.back.final2025.nsb.controller;

import es.ufv.dis.back.final2025.nsb.model.Usuario;
import es.ufv.dis.back.final2025.nsb.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


    UsuarioService usuarioService;

    public UsuarioController(@Autowired UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Usuario>> getUsuarios() {
        ArrayList<Usuario> usuarios = usuarioService.getUsuarios();
        return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") String id) {
        Usuario usuario = usuarioService.getUsuario(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario response = usuarioService.addUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        Usuario response = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pdf")
    public ResponseEntity<Boolean> createPDF() {
        boolean response = usuarioService.createPDF();
        return response ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().build();
    }


}
