package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

@Repository
public interface PagoRepositorio extends CrudRepository<PagoEntity, Long> {

}
