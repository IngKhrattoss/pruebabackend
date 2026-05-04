package com.backend.servicios.Repository;

import com.backend.servicios.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface Serviciorepository extends Repository<Servicio, Integer> {
    List<Servicio> findAll();
    Servicio findByNombre(String nombre);


    Servicio save(Servicio s);
    Servicio findById(int id);
    void delete(Servicio s);
    List<Servicio> findByCategoriaId(int id);
}
