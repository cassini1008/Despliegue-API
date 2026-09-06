package com.edu.unicordoba.registro_visitantes.controlador;

import com.edu.unicordoba.registro_visitantes.modelo.Visitante;
import com.edu.unicordoba.registro_visitantes.servicio.VisitanteService;
import com.edu.unicordoba.registro_visitantes.util.TextoUtil;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visitantes")

public class VisitanteController {

    private final VisitanteService  servicio;

    // Inyección por constructor: NO es static
    public VisitanteController(VisitanteService s) {
        this.servicio = s;
    }

    @PostMapping
    public Visitante registrar(@RequestParam String nombre, @RequestParam int edad) {
        return servicio.registrar(nombre, edad);
    }

    @GetMapping
    public List<Visitante> listar() {
        return servicio.listar();
    }

    @GetMapping("/conteos")
    public Map<String, Object> conteos() {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("registradosEnElServicio", servicio.contarRegistrados());
        r.put("creadosEnLaClase", servicio.contarCreadosEnLaClase());
        r.put("edadMinima", Visitante.EDAD_MINIMA);
        return r;
    }

    @GetMapping("/normalizar")
    public Map<String, String> normalizar(@RequestParam String texto) {
        return Map.of("normalizado", TextoUtil.normalizarNombre(texto)); // static
    }

    // Endpoint fantasma para ver la diferencia de contadores
    @PostMapping("/fantasma")
    public Map<String, Object> fantasma() {
        new Visitante("objeto fantasma", 30); // Se crea pero no se guarda en el servicio
        return Map.of(
            "registradosEnElServicio", servicio.contarRegistrados(),
            "creadosEnLaClase", Visitante.getTotalCreados()
        );
    }

    private static final Instant ARRANQUE = Instant.now();

    @GetMapping("/instancia")
    public Map<String, Object> instancia() throws Exception {
    Map<String, Object> r = new LinkedHashMap<>();
    r.put("host", InetAddress.getLocalHost().getHostName());
    r.put("arranqueJvm", ARRANQUE.toString());
    r.put("creados", Visitante.getTotalCreados());
    r.put("registrados", servicio.contarRegistrados());
    return r;
    }

}