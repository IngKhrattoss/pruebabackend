package com.backend.servicios.Interfaces;

import com.backend.servicios.Entity.Usuario;
import com.backend.servicios.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔐 REGISTRO
    public Usuario crearUsuario(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // 🔑 LOGIN
    public Usuario login(String username, String password) {
        Usuario user = repo.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }

}
