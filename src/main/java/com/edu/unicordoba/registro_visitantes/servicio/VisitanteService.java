package com.edu.unicordoba.registro_visitantes.servicio;

import com.edu.unicordoba.registro_visitantes.modelo.Visitante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitanteService {
    private final List<Visitante> reg = new ArrayList<>();

    public Visitante registrar(String n, int e) {
        Visitante v = new Visitante(n, e);
        reg.add(v);
        return v;
    }

    public List<Visitante> listar() {
        return List.copyOf(reg);
    }

    public int contarRegistrados() {
        return reg.size();
    }

    public int contarCreadosEnLaClase() {
        return Visitante.getTotalCreados();
    }
}