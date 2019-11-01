package com.genesyslab.www.interaction;

public class IWebServiceCapturePointProxy implements com.genesyslab.www.interaction.IWebServiceCapturePoint {
  private String _endpoint = null;
  private com.genesyslab.www.interaction.IWebServiceCapturePoint iWebServiceCapturePoint = null;
  
  public IWebServiceCapturePointProxy() {
    _initIWebServiceCapturePointProxy();
  }
  
  public IWebServiceCapturePointProxy(String endpoint) {
    _endpoint = endpoint;
    _initIWebServiceCapturePointProxy();
  }
  
  private void _initIWebServiceCapturePointProxy() {
    try {
      iWebServiceCapturePoint = (new com.genesyslab.www.interaction.WebServiceCapturePointLocator()).getiWebServiceCapturePointHttpBinding();
      if (iWebServiceCapturePoint != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iWebServiceCapturePoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iWebServiceCapturePoint)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iWebServiceCapturePoint != null)
      ((javax.xml.rpc.Stub)iWebServiceCapturePoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.genesyslab.www.interaction.IWebServiceCapturePoint getIWebServiceCapturePoint() {
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    return iWebServiceCapturePoint;
  }
  
  public void submit(java.lang.String interactionId, java.lang.String externalId, java.lang.String mediaType, java.lang.String interactionType, java.lang.String interactionSubtype, java.lang.Integer tenantId, java.lang.String queue, java.lang.String workbin, java.lang.String workbinAgentId, java.lang.String workbinAgentGroupId, java.lang.String workbinPlaceId, java.lang.String workbinPlaceGroupId, java.lang.String parentInteractionId, java.lang.Boolean isOnline, java.lang.String receivedAt, com.genesyslab.www.interaction.KVPair[] inQueues, com.genesyslab.www.interaction.KVPair[] outQueues, com.genesyslab.www.interaction.KVPair[] userData, java.lang.Boolean hold, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.submit(interactionId, externalId, mediaType, interactionType, interactionSubtype, tenantId, queue, workbin, workbinAgentId, workbinAgentGroupId, workbinPlaceId, workbinPlaceGroupId, parentInteractionId, isOnline, receivedAt, inQueues, outQueues, userData, hold, extension);
  }
  
  public void hold(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.hold(interactionId, externalId, reasonSystemName, reasonDescription, extension);
  }
  
  public void stop(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.stop(interactionId, externalId, reasonSystemName, reasonDescription, extension);
  }
  
  public void update(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.KVPair[] changed, com.genesyslab.www.interaction.KVPair[] deleted, java.lang.String queue, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.update(interactionId, externalId, changed, deleted, queue, extension);
  }
  
  public void resume(java.lang.String interactionId, java.lang.String externalId, java.lang.String reasonSystemName, java.lang.String reasonDescription, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.resume(interactionId, externalId, reasonSystemName, reasonDescription, extension);
  }
  
  public void ping(com.genesyslab.www.interaction.holders.KVListHolder extension, javax.xml.rpc.holders.StringHolder eventTime, com.genesyslab.www.interaction.holders.KVListHolder userData, com.genesyslab.www.interaction.holders.KVListHolder pingInfo) throws java.rmi.RemoteException{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    iWebServiceCapturePoint.ping(extension, eventTime, userData, pingInfo);
  }
  
  public com.genesyslab.www.interaction.KVPair[] getInfo(java.lang.String interactionId, java.lang.String externalId, com.genesyslab.www.interaction.holders.KVListHolder extension) throws java.rmi.RemoteException, com.genesyslab.www.interaction.FaultMessage{
    if (iWebServiceCapturePoint == null)
      _initIWebServiceCapturePointProxy();
    return iWebServiceCapturePoint.getInfo(interactionId, externalId, extension);
  }
  
  
}