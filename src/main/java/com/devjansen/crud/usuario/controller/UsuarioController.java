package com.devjansen.crud.usuario.controller;

import com.devjansen.crud.usuario.business.UsuarioService;

import com.devjansen.crud.usuario.controller.Dtos.UsuarioDTO;
import com.devjansen.crud.usuario.infrastructure.entity.Usuario;
import com.devjansen.crud.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuario(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuario(@PathVariable String email){
        usuarioService.deletaUsuario(email);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getSenha()
                )
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }
}
