package com.backend.servicios.Controlador;

import com.backend.servicios.Entity.Servicio;
import com.backend.servicios.Interfaces.Servicioservices;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/servicios")
public class Controlador {

    @Autowired
    Servicioservices servicioservices;

    @Autowired
    Cloudinary cloudinary;

    

    @GetMapping
    public List<Servicio> listar(){
        return servicioservices.listar();
    }

    @PostMapping
    public Servicio add(@RequestBody Servicio s){
        return servicioservices.add(s);
    }

    @PutMapping
    public Servicio edit(@RequestBody Servicio s){
        return servicioservices.edit(s);
    }

    @DeleteMapping("/{id}")
    public Servicio delete(@PathVariable int id){
        return servicioservices.delete(id);
    }

    @GetMapping("/{id}")
    public Servicio listarId(@PathVariable int id){
        return servicioservices.listarId(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("secure_url");

            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            e.printStackTrace(); // para ver el error exacto en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al subir imagen: " + e.getMessage()));
        }
    }

}
