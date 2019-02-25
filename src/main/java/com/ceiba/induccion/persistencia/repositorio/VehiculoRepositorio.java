package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public interface VehiculoRepositorio extends CrudRepository<VehiculoEntity, Long> {

}
