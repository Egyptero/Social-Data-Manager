/**
 * IWebServiceCapturePointHttpBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.genesyslab.www.interaction;

public class IWebServiceCapturePointHttpBindingStub extends org.apache.axis.client.Stub implements com.genesyslab.www.interaction.IWebServiceCapturePoint {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Submit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "MediaType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionSubtype"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "TenantId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Queue"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Workbin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "WorkbinAgentId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "WorkbinAgentGroupId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "WorkbinPlaceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "WorkbinPlaceGroupId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ParentInteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "IsOnline"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReceivedAt"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InQueues"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "OutQueues"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "UserData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Hold"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Hold");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonSystemName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonDescription"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Stop");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonSystemName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonDescription"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Resume");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonSystemName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ReasonDescription"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Changed"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Deleted"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Queue"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"));
        oper.setReturnClass(com.genesyslab.www.interaction.KVPair[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "InteractionProperties"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "FaultMessage"),
                      "com.genesyslab.www.interaction.FaultMessage",
                      new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Ping");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "EventTime"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "UserData"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "PingInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList"), com.genesyslab.www.interaction.KVPair[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

    }

    public IWebServiceCapturePointHttpBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public IWebServiceCapturePointHttpBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public IWebServiceCapturePointHttpBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", ">FaultMessage");
            cachedSerQNames.add(qName);
            cls = com.genesyslab.www.interaction.FaultMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVList");
            cachedSerQNames.add(qName);
            cls = com.genesyslab.www.interaction.KVPair[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVPair");
            qName2 = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVPair");
            cachedSerQNames.add(qName);
            cls = com.genesyslab.www.interaction.KVPair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVPairValue");
            cachedSerQNames.add(qName);
            cls = com.genesyslab.www.interaction.KVPairValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void submit(java.lang.String interactionId, java.lang.String externalId, java.lang.String mediaType, java.lang.String interactionType, java.lang.String interactionSubtype, java.lang.Integer tenantId, java.lang.String queue, java.lang.String workbin, java.lang.String workbinAgentId, java.lang.String workbinAgentGroupId, java.lang.String workbinPlaceId, java.lang.String workbinPlaceGroupId, java.lang.String parentInteractionId, java.lang.Boolean isOnline, java.lang.String receivedAt, com.genesyslab.www.interaction.KVPair[] inQueues, com.genesyslab.www.interaction.KVPair[] outQueues, com.genesyslab.www.interaction.KVPair[] userData, java.lang.Boolean hold, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Submit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, mediaType, interactionType, interactionSubtype, tenantId, queue, workbin, workbinAgentId, workbinAgentGroupId, workbinPlaceId, workbinPlaceGroupId, parentInteractionId, isOnline, receivedAt, inQueues, outQueues, userData, hold, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void hold(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Hold"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, reasonSystemName, reasonDescription, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void stop(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Stop"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, reasonSystemName, reasonDescription, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void resume(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Resume"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, reasonSystemName, reasonDescription, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void update(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.KVPair[] changed, com.genesyslab.www.interaction.KVPair[] deleted, java.lang.String queue, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, changed, deleted, queue, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.genesyslab.www.interaction.KVPair[] getInfo(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "GetInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {interactionId, externalId, extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
            try {
                return (com.genesyslab.www.interaction.KVPair[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.genesyslab.www.interaction.FaultMessage) {
              throw (com.genesyslab.www.interaction.FaultMessage) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void ping(com.genesyslab.www.interaction.holders.KVListHolder extension, javax.xml.rpc.holders.StringHolder eventTime, com.genesyslab.www.interaction.holders.KVListHolder userData, com.genesyslab.www.interaction.holders.KVListHolder pingInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Ping"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {extension.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension"));
            } catch (java.lang.Exception _exception) {
                extension.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "Extension")), com.genesyslab.www.interaction.KVPair[].class);
            }
            try {
                eventTime.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "EventTime"));
            } catch (java.lang.Exception _exception) {
                eventTime.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "EventTime")), java.lang.String.class);
            }
            try {
                userData.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "UserData"));
            } catch (java.lang.Exception _exception) {
                userData.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "UserData")), com.genesyslab.www.interaction.KVPair[].class);
            }
            try {
                pingInfo.value = (com.genesyslab.www.interaction.KVPair[]) _output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "PingInfo"));
            } catch (java.lang.Exception _exception) {
                pingInfo.value = (com.genesyslab.www.interaction.KVPair[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "PingInfo")), com.genesyslab.www.interaction.KVPair[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
