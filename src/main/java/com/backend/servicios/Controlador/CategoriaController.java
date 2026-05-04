package com.backend.servicios.Controlador;


import com.backend.servicios.Entity.Categoria;
import com.backend.servicios.Entity.Servicio;
import com.backend.servicios.Interfaces.CategoriaRepository;
import com.backend.servicios.Repository.Serviciorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("https://moonlit-kringle-d82d5f.netlify.app")
public class CategoriaController {
    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private Serviciorepository servicioRepo;

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Categoria add(@RequestBody Categoria c) {
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {

        List<Servicio> servicios = servicioRepo.findByCategoriaId(id);

        if (!servicios.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No puedes eliminar una categoría con servicios asociados"
            );
        }

        repo.deleteById(id);
    }
}
