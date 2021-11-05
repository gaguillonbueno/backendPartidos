package com.javainuse.service;

import com.javainuse.dao.PartidoDao;
import com.javainuse.model.Partido;
import com.javainuse.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

//Notación para indicar que es un servicio
@Service
//Asegura que toda la data requerida este segura hasta que la transacción termine
//Recomiendo leer acerca de esta notación (es un mundo completo jeje)
@Transactional
public class PartidoService {

    //Inyección de dependecias (crea una instancia cuando lo requiera)
    @Autowired
    PartidoDao partidoDao;

    //Por defecto el repositorio al extender de JPA trae el metodo por defecto
    public List<Partido> listaPartido(){
        List<Partido> partidos = new ArrayList();
        partidoDao.findAll().forEach(partidos::add);
        return partidos;
    }

    public Optional<Partido> getPartido(int idPartido){
        return  partidoDao.findById(idPartido);
    }

    public Iterable<Partido> getPartidoUsuario(Usuario usuario){
        return  partidoDao.findAllByUsuario(usuario);
    }

    public void savePartido(Partido partido){
        partidoDao.save(partido);
    }

    public void deletePartido(int idPartido){
        partidoDao.deleteById(idPartido);
    }

    public boolean existsByIdPartido(int idPartido){
        return partidoDao.existsById(idPartido);
    }

}
