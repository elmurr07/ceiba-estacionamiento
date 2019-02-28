package com.ceiba.induccion.controlador;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.wsdl.TCRMServicesInterfaceProxy;
import com.ceiba.induccion.wsdl.TcrmResponse;

@RestController
@RequestMapping("trm")
public class TRMController {

	private static final String CTRM_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

	@GetMapping("obtenerActual")
	public Float obtenerActual() throws RemoteException {
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(CTRM_SERVICE_URL);
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		return tcrmResponse.getValue();
	}

}
