package com.backend.servicios.Implements;

import com.backend.servicios.Entity.Servicio;
import com.backend.servicios.Interfaces.Servicioservices;
import com.backend.servicios.Repository.Serviciorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Servicioservicesimplements implements Servicioservices {
    @Autowired
    private Serviciorepository serviciorepository;


    @Override
    public List<Servicio> listar() {
        return serviciorepository.findAll();
    }

    @Override
    public Servicio listarId(int id) {
        return null;
    }

    @Override
    public Servicio add(Servicio s) {
    return serviciorepository.save(s);
    }

    @Override
    public Servicio edit(Servicio s) {

return  serviciorepository.save(s);
    }
    @Override
    public Servicio delete(int id) {
        Servicio s = serviciorepository.findById(id);

        if (s != null) {
            serviciorepository.delete(s);
        }

        return s;
    }
}
