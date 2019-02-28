package com.ceiba.induccion.persistencia.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@Repository
public interface RegistroRepositorio extends CrudRepository<RegistroEntity, Long> {

	@Query("SELECT COUNT(r) FROM Registro r WHERE r.vehiculo.tipo = :tipo AND fin is null")
	int contarVehiculosEstacionados(@Param("tipo") TipoVehiculoEnum tipo);

}
