package com.backend.servicios.Interfaces;

import com.backend.servicios.Entity.Servicio;

import java.util.List;

public interface Servicioservices {
    List<Servicio> listar();
    Servicio listarId(int id);
    Servicio add(Servicio s);
    Servicio edit(Servicio s);
    Servicio delete(int id);
}
