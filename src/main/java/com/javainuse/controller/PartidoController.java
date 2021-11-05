package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import com.javainuse.model.*;
import com.javainuse.service.*;
import com.javainuse.instance.PartidoInstancia;
import com.javainuse.instance.MensajeInstancia;

//Notación para indicar que es un controlador de tipo Rest
@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /partido/nombreServicio
@RequestMapping("/partido")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "http://localhost:3000")
public class PartidoController {

    @Autowired
    PartidoService partidoService;

    @Autowired
    EquipoService equipoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listaPartido")
    public ResponseEntity<List<Partido>> listaPartidos(){

        List<Partido> partidos = partidoService.listaPartido();
        return new ResponseEntity<List<Partido>>(partidos, HttpStatus.OK);
    }

    @GetMapping("/detallePartido/{idPartido}")
    public ResponseEntity<Partido> partidoById(@PathVariable("idPartido") int idPartido){

        if (!partidoService.existsByIdPartido(idPartido))
            return new ResponseEntity(new MensajeInstancia("No existe partido"), HttpStatus.NOT_FOUND);

        Partido partido = partidoService.getPartido(idPartido).get();
        return new ResponseEntity(partido, HttpStatus.OK);
    }

    @GetMapping("/usuarioPartidos/{idUsuario}")
    public ResponseEntity<Partido> partidoByUsuario(@PathVariable("idUsuario") int idUsuario){

        Usuario usuario = usuarioService.getUsuario(idUsuario).get();
        Iterable<Partido> partidos = partidoService.getPartidoUsuario(usuario);
        return new ResponseEntity(partidos, HttpStatus.OK);
    }

		@PostMapping("/crearPartido")
    public ResponseEntity<?> creaPartido(@RequestBody PartidoInstancia partidoInstancia){
        System.out.println("partido instancia--->" + partidoInstancia);

        Equipo local = equipoService.getEquipo(partidoInstancia.getLocal()).get();
        Equipo visitante = equipoService.getEquipo(partidoInstancia.getVisitante()).get();
        Usuario usuario = usuarioService.getUsuario(partidoInstancia.getUsuario()).get();

        Partido partido = new Partido();
        partido.setFecha(partidoInstancia.getFecha());
        partido.setGoles_local(partidoInstancia.getGoles_local());
        partido.setGoles_visitante(partidoInstancia.getGoles_visitante());
        partido.setLocal(local);
        partido.setVisitante(visitante);
        partido.setUsuario(usuario);
        partidoService.savePartido(partido);
        return new ResponseEntity(new MensajeInstancia("Partido creada"), HttpStatus.OK);
    }

    @PutMapping("/actualizarPartido/{idPartido}")
    public ResponseEntity<?> actualizarPartido(@PathVariable("idPartido") int idPartido, @RequestBody PartidoInstancia partidoInstancia){

        if (!partidoService.existsByIdPartido(idPartido))
        return new ResponseEntity(new MensajeInstancia("No existe la partido"), HttpStatus.NOT_FOUND);

        Partido partido = partidoService.getPartido(idPartido).get();

        if (partidoInstancia.getFecha() != null){
          partido.setFecha(partidoInstancia.getFecha());
        }
        if (partidoInstancia.getGoles_local() != null){
          partido.setGoles_local(partidoInstancia.getGoles_local());
        }
        if (partidoInstancia.getGoles_visitante() != null){
          partido.setGoles_visitante(partidoInstancia.getGoles_visitante());
        }
        if (partidoInstancia.getLocal() != null){
          Equipo local = equipoService.getEquipo(partidoInstancia.getLocal()).get();
          partido.setLocal(local);
        }
        if (partidoInstancia.getVisitante() != null){
          Equipo visitante = equipoService.getEquipo(partidoInstancia.getVisitante()).get();
          partido.setVisitante(visitante);
        }
        if (partidoInstancia.getUsuario() != null){
          Usuario usuario = usuarioService.getUsuario(partidoInstancia.getUsuario()).get();
          partido.setUsuario(usuario);
        }

        partidoService.savePartido(partido);
        return new ResponseEntity(new MensajeInstancia("Partido actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarPartido/{idPartido}")
    public ResponseEntity<?> borrarPartido(@PathVariable("idPartido") int idPartido){
        if (!partidoService.existsByIdPartido(idPartido))
            return new ResponseEntity(new MensajeInstancia("No existe la partido"), HttpStatus.NOT_FOUND);
        partidoService.deletePartido(idPartido);
        return new ResponseEntity(new MensajeInstancia("Partido eliminada"), HttpStatus.OK);
    }

}
