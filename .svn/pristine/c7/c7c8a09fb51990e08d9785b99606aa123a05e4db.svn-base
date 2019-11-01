/**
 * WebServiceCapturePointLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.genesyslab.www.interaction;

public class WebServiceCapturePointLocator extends org.apache.axis.client.Service implements com.genesyslab.www.interaction.WebServiceCapturePoint {

    public WebServiceCapturePointLocator() {
    }


    public WebServiceCapturePointLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServiceCapturePointLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for iWebServiceCapturePointHttpBinding
    private java.lang.String iWebServiceCapturePointHttpBinding_address = "http://172.31.68.154:7008/Genesys/Interaction/INS_CP/WebServiceCapturePoint";

    public java.lang.String getiWebServiceCapturePointHttpBindingAddress() {
        return iWebServiceCapturePointHttpBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String iWebServiceCapturePointHttpBindingWSDDServiceName = "iWebServiceCapturePointHttpBinding";

    public java.lang.String getiWebServiceCapturePointHttpBindingWSDDServiceName() {
        return iWebServiceCapturePointHttpBindingWSDDServiceName;
    }

    public void setiWebServiceCapturePointHttpBindingWSDDServiceName(java.lang.String name) {
        iWebServiceCapturePointHttpBindingWSDDServiceName = name;
    }

    public com.genesyslab.www.interaction.IWebServiceCapturePoint getiWebServiceCapturePointHttpBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(iWebServiceCapturePointHttpBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getiWebServiceCapturePointHttpBinding(endpoint);
    }

    public com.genesyslab.www.interaction.IWebServiceCapturePoint getiWebServiceCapturePointHttpBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.genesyslab.www.interaction.IWebServiceCapturePointHttpBindingStub _stub = new com.genesyslab.www.interaction.IWebServiceCapturePointHttpBindingStub(portAddress, this);
            _stub.setPortName(getiWebServiceCapturePointHttpBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setiWebServiceCapturePointHttpBindingEndpointAddress(java.lang.String address) {
        iWebServiceCapturePointHttpBinding_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.genesyslab.www.interaction.IWebServiceCapturePoint.class.isAssignableFrom(serviceEndpointInterface)) {
                com.genesyslab.www.interaction.IWebServiceCapturePointHttpBindingStub _stub = new com.genesyslab.www.interaction.IWebServiceCapturePointHttpBindingStub(new java.net.URL(iWebServiceCapturePointHttpBinding_address), this);
                _stub.setPortName(getiWebServiceCapturePointHttpBindingWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("iWebServiceCapturePointHttpBinding".equals(inputPortName)) {
            return getiWebServiceCapturePointHttpBinding();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "WebServiceCapturePoint");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "iWebServiceCapturePointHttpBinding"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("iWebServiceCapturePointHttpBinding".equals(portName)) {
            setiWebServiceCapturePointHttpBindingEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
