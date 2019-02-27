package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

@Repository
public interface VehiculoRepositorio extends CrudRepository<VehiculoEntity, Long> {

}
