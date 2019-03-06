package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.RegistroEntity;

public interface ReglasEstacionamientoVehiculo {

	double calcularCosto(RegistroEntity registroEntity);

	boolean existeCupo(int numero);

}
