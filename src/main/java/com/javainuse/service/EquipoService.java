package com.javainuse.service;

import com.javainuse.dao.EquipoDao;
import com.javainuse.model.Equipo;
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
public class EquipoService {

    //Inyección de dependecias (crea una instancia cuando lo requiera)
    @Autowired
    EquipoDao equipoDao;

    //Por defecto el repositorio al extender de JPA trae el metodo por defecto
    public List<Equipo> listaEquipo(){
        List<Equipo> equipos = new ArrayList();
        equipoDao.findAll().forEach(equipos::add);
        return equipos;
    }

    public Optional<Equipo> getEquipo(int idEquipo){
        return equipoDao.findById(idEquipo);
    }

    public void saveEquipo(Equipo equipo){
        equipoDao.save(equipo);
    }

    public void deleteEquipo(int idEquipo){
        equipoDao.deleteById(idEquipo);
    }

    public boolean existsByIdEquipo(int idEquipo){
        return equipoDao.existsById(idEquipo);
    }


	/**
	* Default empty EquipoService constructor
	*/
	public EquipoService() {
		super();
	}

}
