package com.ceiba.induccion.wsdl;

/**
 * Business TCRM response
 * 
 * @author Alex Vicente Chacon Jimenez (alex.chacon@software-colombia.com)
 *
 */
public class TcrmResponse extends Tcrm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String message;

	private java.lang.Boolean success;

	public TcrmResponse() {
	}

	public TcrmResponse(java.lang.Boolean displayToUser, java.lang.Long id, java.lang.String unit,
			java.util.Calendar validityFrom, java.util.Calendar validityTo, java.lang.Float value,
			java.lang.String message, java.lang.Boolean success) {
		super(displayToUser, id, unit, validityFrom, validityTo, value);
		this.message = message;
		this.success = success;
	}

	/**
	 * Gets the message value for this TcrmResponse.
	 * 
	 * @return message
	 */
	public java.lang.String getMessage() {
		return message;
	}

	/**
	 * Sets the message value for this TcrmResponse.
	 * 
	 * @param message
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	/**
	 * Gets the success value for this TcrmResponse.
	 * 
	 * @return success
	 */
	public java.lang.Boolean getSuccess() {
		return success;
	}

	/**
	 * Sets the success value for this TcrmResponse.
	 * 
	 * @param success
	 */
	public void setSuccess(java.lang.Boolean success) {
		this.success = success;
	}

	private java.lang.Object xequalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof TcrmResponse))
			return false;

		TcrmResponse other = (TcrmResponse) obj;

		if (this == obj)
			return true;
		if (xequalsCalc != null) {
			return (xequalsCalc == obj);
		}
		xequalsCalc = obj;
		boolean equals;
		equals = super.equals(obj)
				&& ((this.message == null && other.getMessage() == null)
						|| (this.message != null && this.message.equals(other.getMessage())))
				&& ((this.success == null && other.getSuccess() == null)
						|| (this.success != null && this.success.equals(other.getSuccess())));
		xequalsCalc = null;
		return equals;
	}

	private boolean xhashCodeCalc = false;

	@Override
	public synchronized int hashCode() {
		if (xhashCodeCalc) {
			return 0;
		}
		xhashCodeCalc = true;
		int xhashCode = super.hashCode();
		if (getMessage() != null) {
			xhashCode += getMessage().hashCode();
		}
		if (getSuccess() != null) {
			xhashCode += getSuccess().hashCode();
		}
		xhashCodeCalc = false;
		return xhashCode;
	}

	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			TcrmResponse.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName(
				"http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "tcrmResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("message");
		elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("success");
		elemField.setXmlName(new javax.xml.namespace.QName("", "success"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.Class<?> javaType,
			javax.xml.namespace.QName xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(javaType, xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.Class<?> javaType,
			javax.xml.namespace.QName xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(javaType, xmlType, typeDesc);
	}

}
