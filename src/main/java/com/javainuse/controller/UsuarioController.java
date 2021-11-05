package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import com.javainuse.model.Usuario;
import com.javainuse.instance.UsuarioInstancia;
import com.javainuse.service.UsuarioService;
import com.javainuse.instance.MensajeInstancia;

//Notación para indicar que es un controlador de tipo Rest
@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /usuario/nombreServicio
@RequestMapping("/usuario")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
  	private PasswordEncoder bcryptEncoder;

    @GetMapping("/listaUsuario")
    public ResponseEntity<List<Usuario>> listaUsuarios(){

        List<Usuario> usuarios = usuarioService.listaUsuario();
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/detalleUsuario/{idUsuario}")
    public ResponseEntity<Usuario> usuarioById(@PathVariable("idUsuario") int idUsuario){

        if (!usuarioService.existsByIdUsuario(idUsuario))
            return new ResponseEntity(new MensajeInstancia("No existe usuario"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getUsuario(idUsuario).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PutMapping("/actualizarUsuario/{idUsuario}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("idUsuario") int idUsuario, @RequestBody UsuarioInstancia usuarioInstancia){

        if (!usuarioService.existsByIdUsuario(idUsuario))
        return new ResponseEntity(new MensajeInstancia("No existe la usuario"), HttpStatus.NOT_FOUND);

        if (usuarioInstancia.getUsername() != null && usuarioService.getByUsername(usuarioInstancia.getUsername()).getId() != idUsuario)
            return new ResponseEntity(new MensajeInstancia("El username del usuario ya existe"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getUsuario(idUsuario).get();

        if (usuarioInstancia.getUsername() != null){
          usuario.setUsername(usuarioInstancia.getUsername());
        }
        if (usuarioInstancia.getCorreo() != null){
          usuario.setCorreo(usuarioInstancia.getCorreo());
        }
        if (usuarioInstancia.getNombre() != null){
          usuario.setNombre(usuarioInstancia.getNombre());
        }
        if (usuarioInstancia.getPassword() != null){
          usuario.setPassword(bcryptEncoder.encode(usuarioInstancia.getPassword()));
        }

        usuarioService.saveUsuario(usuario);
        return new ResponseEntity(new MensajeInstancia("Usuario actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarUsuario/{idUsuario}")
    public ResponseEntity<?> borrarUsuario(@PathVariable("idUsuario") int idUsuario){
        if (!usuarioService.existsByIdUsuario(idUsuario))
            return new ResponseEntity(new MensajeInstancia("No existe la usuario"), HttpStatus.NOT_FOUND);
        usuarioService.deleteUsuario(idUsuario);
        return new ResponseEntity(new MensajeInstancia("Usuario eliminada"), HttpStatus.OK);
    }

}
