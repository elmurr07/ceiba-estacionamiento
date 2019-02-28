package com.ceiba.induccion.wsdl;

public class TCRMServicesInterfaceProxy implements TCRMServicesInterface {

	private static final String XML_ADDRESS = "javax.xml.rpc.service.endpoint.address";
	private String xendpoint = null;
	private TCRMServicesInterface tCRMServicesInterface = null;

	public TCRMServicesInterfaceProxy() {
		xinitTCRMServicesInterfaceProxy();
	}

	public TCRMServicesInterfaceProxy(String endpoint) {
		xendpoint = endpoint;
		xinitTCRMServicesInterfaceProxy();
	}

	private void xinitTCRMServicesInterfaceProxy() {
		try {
			tCRMServicesInterface = (new TCRMServicesWebServiceLocator()).getTCRMServicesWebServicePort();
			if (tCRMServicesInterface != null) {
				if (xendpoint != null)
					((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty(XML_ADDRESS, xendpoint);
				else
					xendpoint = (String) ((javax.xml.rpc.Stub) tCRMServicesInterface)._getProperty(XML_ADDRESS);
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
			// No implementation
		}
	}

	public String getEndpoint() {
		return xendpoint;
	}

	public void setEndpoint(String endpoint) {
		xendpoint = endpoint;
		if (tCRMServicesInterface != null)
			((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty(XML_ADDRESS, xendpoint);

	}

	public TCRMServicesInterface getTCRMServicesInterface() {
		if (tCRMServicesInterface == null)
			xinitTCRMServicesInterfaceProxy();
		return tCRMServicesInterface;
	}

	public TcrmResponse queryTCRM(java.util.Calendar tcrmQueryAssociatedDate) throws java.rmi.RemoteException {
		if (tCRMServicesInterface == null)
			xinitTCRMServicesInterfaceProxy();
		return tCRMServicesInterface.queryTCRM(tcrmQueryAssociatedDate);
	}

}