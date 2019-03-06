package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.RegistroEntity;

public interface Calculador {

	double calcularCosto(RegistroEntity registroEntity);

	boolean existeCupo(int numero);

}
