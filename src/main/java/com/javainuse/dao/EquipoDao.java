package com.javainuse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.Equipo;

@Repository
public interface EquipoDao extends CrudRepository<Equipo, Integer> {
}
