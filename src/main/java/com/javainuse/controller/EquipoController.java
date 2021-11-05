package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import com.javainuse.model.Equipo;
import com.javainuse.instance.EquipoInstancia;
import com.javainuse.service.EquipoService;
import com.javainuse.instance.MensajeInstancia;

//Notación para indicar que es un controlador de tipo Rest
@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /equipo/nombreServicio
@RequestMapping("/equipo")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "*")
public class EquipoController {

    @Autowired
    EquipoService equipoService;

    @GetMapping("/listaEquipo")
    public ResponseEntity<List<Equipo>> listaEquipos(){

        List<Equipo> equipos = equipoService.listaEquipo();
        return new ResponseEntity<List<Equipo>>(equipos, HttpStatus.OK);
    }

    @GetMapping("/detalleEquipo/{idEquipo}")
    public ResponseEntity<Equipo> equipoById(@PathVariable("idEquipo") int idEquipo){

        if (!equipoService.existsByIdEquipo(idEquipo))
            return new ResponseEntity(new MensajeInstancia("No existe equipo"), HttpStatus.NOT_FOUND);

        Equipo equipo = equipoService.getEquipo(idEquipo).get();
        return new ResponseEntity(equipo, HttpStatus.OK);
    }

		@PostMapping("/crearEquipo")
    public ResponseEntity<?> creaEquipo(@RequestBody EquipoInstancia equipoInstancia){

        Equipo equipo = new Equipo(equipoInstancia.getNombre());
        equipoService.saveEquipo(equipo);
        return new ResponseEntity(new MensajeInstancia("Equipo creada"), HttpStatus.OK);
    }

    @PutMapping("/actualizarEquipo/{idEquipo}")
    public ResponseEntity<?> actualizarEquipo(@PathVariable("idEquipo") int idEquipo, @RequestBody EquipoInstancia equipoInstancia){

        if (!equipoService.existsByIdEquipo(idEquipo))
        return new ResponseEntity(new MensajeInstancia("No existe la equipo"), HttpStatus.NOT_FOUND);

        Equipo equipo = equipoService.getEquipo(idEquipo).get();

        if (equipoInstancia.getNombre() != null){
          equipo.setNombre(equipoInstancia.getNombre());
        }

        equipoService.saveEquipo(equipo);
        return new ResponseEntity(new MensajeInstancia("Equipo actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarEquipo/{idEquipo}")
    public ResponseEntity<?> borrarEquipo(@PathVariable("idEquipo") int idEquipo){
        if (!equipoService.existsByIdEquipo(idEquipo))
            return new ResponseEntity(new MensajeInstancia("No existe la equipo"), HttpStatus.NOT_FOUND);
        equipoService.deleteEquipo(idEquipo);
        return new ResponseEntity(new MensajeInstancia("Equipo eliminada"), HttpStatus.OK);
    }

}
