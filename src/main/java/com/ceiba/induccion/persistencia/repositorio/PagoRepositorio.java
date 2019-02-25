package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

public interface PagoRepositorio extends CrudRepository<PagoEntity, Long> {

}
