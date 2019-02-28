package com.ceiba.induccion.utilidad;

public final class RegistroConstants {

	private RegistroConstants() {
		throw new IllegalStateException("Clase de utilidad");
	}

	public static final String MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA = "El vehiculo no puede ingresar en semana";
	public static final String MENSAJE_ERROR_NO_HAY_CUPO = "El estacionamiento no cuenta con cupos para el vehiculo";
	public static final int CUPO_CARROS_PARQUEADERO = 20;
	public static final int CUPO_MOTOS_PARQUEADERO = 10;

}
