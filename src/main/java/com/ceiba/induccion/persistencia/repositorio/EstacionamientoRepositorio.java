package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@Repository
public interface EstacionamientoRepositorio extends CrudRepository<EstacionamientoEntity, Long> {

	@Query("SELECT COUNT(e) FROM Estacionamiento e WHERE e.vehiculo.tipo = :tipo AND fin is null")
	int contarVehiculosEstacionados(@Param("tipo") TipoVehiculoEnum tipo);

}
