package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.RegistroEntity;

public interface Vehiculo {

	double calcularCosto(RegistroEntity registroEntity);

	boolean existeCupo(int numero);

}
