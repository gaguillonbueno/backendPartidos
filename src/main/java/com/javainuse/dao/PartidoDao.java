package com.javainuse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.Partido;
import com.javainuse.model.Usuario;

@Repository
public interface PartidoDao extends CrudRepository<Partido, Integer> {
    Iterable<Partido> findAllByUsuario(Usuario usuario);
}
