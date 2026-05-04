package com.backend.servicios.Controlador;

import com.backend.servicios.Component.JwtUtil;
import com.backend.servicios.Entity.Usuario;
import com.backend.servicios.Interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")

public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService service;

    // 👉 crear admin (solo una vez)
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario user) {
        return service.crearUsuario(user);
    }

    // 👉 login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        Usuario logged = service.login(user.getUsername(), user.getPassword());

        if (logged != null) {
            String token = jwtUtil.generarToken(logged.getUsername());
            return ResponseEntity.ok().body(
                    java.util.Map.of(
                            "token", token,
                            "username", user.getUsername()
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }



}
