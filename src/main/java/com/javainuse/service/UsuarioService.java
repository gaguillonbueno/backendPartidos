package com.javainuse.service;

import com.javainuse.dao.UserDao;
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
public class UsuarioService {

    //Inyección de dependecias (crea una instancia cuando lo requiera)
    @Autowired
    UserDao userDao;

    //Por defecto el repositorio al extender de JPA trae el metodo por defecto
    public List<Usuario> listaUsuario(){
        List<Usuario> usuarios = new ArrayList();
        userDao.findAll().forEach(usuarios::add);
        return usuarios;
    }

    public Optional<Usuario> getUsuario(int idUsuario){
        return  userDao.findById(idUsuario);
    }

    public Usuario getByUsername(String username){
        return userDao.findByUsername(username);
    }

    public void saveUsuario(Usuario usuario){
        userDao.save(usuario);
    }

    public void deleteUsuario(int idUsuario){
        userDao.deleteById(idUsuario);
    }

    public boolean existsByIdUsuario(int idUsuario){
        return userDao.existsById(idUsuario);
    }


	/**
	* Default empty UsuarioService constructor
	*/
	public UsuarioService() {
		super();
	}

}
