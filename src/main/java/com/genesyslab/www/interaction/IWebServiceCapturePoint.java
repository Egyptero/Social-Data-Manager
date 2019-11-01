/**
 * IWebServiceCapturePoint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.genesyslab.www.interaction;

public interface IWebServiceCapturePoint extends java.rmi.Remote {
    public void submit(java.lang.String interactionId, java.lang.String externalId, java.lang.String mediaType, java.lang.String interactionType, java.lang.String interactionSubtype, java.lang.Integer tenantId, java.lang.String queue, java.lang.String workbin, java.lang.String workbinAgentId, java.lang.String workbinAgentGroupId, java.lang.String workbinPlaceId, java.lang.String workbinPlaceGroupId, java.lang.String parentInteractionId, java.lang.Boolean isOnline, java.lang.String receivedAt, com.genesyslab.www.interaction.KVPair[] inQueues, com.genesyslab.www.interaction.KVPair[] outQueues, com.genesyslab.www.interaction.KVPair[] userData, java.lang.Boolean hold, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
    public void hold(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
    public void stop(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
    public void update(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.KVPair[] changed, com.genesyslab.www.interaction.KVPair[] deleted, java.lang.String queue, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
    public void resume(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
    public void ping(com.genesyslab.www.interaction.holders.KVListHolder extension, javax.xml.rpc.holders.StringHolder eventTime, com.genesyslab.www.interaction.holders.KVListHolder userData, com.genesyslab.www.interaction.holders.KVListHolder pingInfo) throws java.rmi.RemoteException;
    public com.genesyslab.www.interaction.KVPair[] getInfo(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage;
}
