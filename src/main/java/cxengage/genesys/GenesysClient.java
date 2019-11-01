package cxengage.genesys;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.StringHolder;

import org.apache.log4j.Logger;

import com.genesyslab.www.interaction.IWebServiceCapturePointHttpBindingStub;
import com.genesyslab.www.interaction.KVPair;
import com.genesyslab.www.interaction.KVPairValue;
import com.genesyslab.www.interaction.holders.KVListHolder;

public class GenesysClient {
	private transient Logger logger = Logger.getLogger(GenesysClient.class);

	public boolean submitToGenesys(String userID, String commentId, String text, String queueName, String endpoint,
			String userName, String tenantId, String mediaType)
			throws MalformedURLException, ServiceException, RemoteException {
		logger.info("Create genesys interaction for userID:" + userID + " and commentID:" + commentId);
		URL endPointUrl;
		boolean outCome = false;

		if (endpoint == null || endpoint.isEmpty()) {
			logger.error("Undefined endpoint");
			//endpoint = "http://ec2-34-205-57-32.compute-1.amazonaws.com:7008/Genesys/Interaction/INS_CP/WebServiceCapturePoint?wsdl"; // 172.31.68.154
			return outCome;
		}
		if (queueName == null || queueName.isEmpty()) {
			logger.error("Undefined queue name");
			//queueName = "Instagram Inbound Queue";
			return outCome;
		}
		if (tenantId == null || tenantId.isEmpty()) {
			logger.error("Undefined tenant ID");
			//tenantId = "101";
			return outCome;
		}
		if (mediaType == null || mediaType.isEmpty()) {
			logger.error("Undefined Media Type");
			//mediaType = "instagram";
			return outCome;
		}

		try {
			Integer.parseInt(tenantId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

		try {
			endPointUrl = new URL(endpoint);

			QName sName = new QName("http://www.genesyslab.com/interaction", "WebServiceCapturePoint");
			Service service = new org.apache.axis.client.Service(endpoint, sName);
			IWebServiceCapturePointHttpBindingStub iWebServiceCP = new IWebServiceCapturePointHttpBindingStub(
					endPointUrl, service);
			KVPair kvPair[] = new KVPair[1];
			KVListHolder kvPairHolder = new KVListHolder();
			Boolean F = false;

			KVPair[] userData = new KVPair[5];
			userData[0] = new KVPair();
			userData[0].setKey("postId");
			KVPairValue postIdValue = new KVPairValue();
			postIdValue.setValueString(userID);
			userData[0].setValue(postIdValue);

			userData[1] = new KVPair();
			userData[1].setKey("commentId");
			KVPairValue commentIdValue = new KVPairValue();
			commentIdValue.setValueString(commentId);
			userData[1].setValue(commentIdValue);

			userData[2] = new KVPair();
			userData[2].setKey("commentText");
			KVPairValue commentTextValue = new KVPairValue();
			commentTextValue.setValueString(convertToUTF8(text));
			userData[2].setValue(commentTextValue);

			userData[3] = new KVPair();
			userData[3].setKey("userName");
			KVPairValue userNameValue = new KVPairValue();
			userNameValue.setValueString(userName);
			userData[3].setValue(userNameValue);

			userData[4] = new KVPair();
			userData[4].setKey("firstName");
			KVPairValue firstNameValue = new KVPairValue();
			firstNameValue.setValueString(userName);
			userData[4].setValue(firstNameValue);

			com.genesyslab.www.interaction.holders.KVListHolder extension = new KVListHolder();
			javax.xml.rpc.holders.StringHolder eventTime = new StringHolder(userID);
			com.genesyslab.www.interaction.holders.KVListHolder pingInfo = new KVListHolder();
			logger.info("Ping response time:" + eventTime.value);
			logger.info("PingInfo" + pingInfo.value);
			logger.info("Extension" + extension.value);
			logger.info("link" + userID);

			iWebServiceCP.submit("", userName + ";" + "New Message", mediaType, "", "", Integer.parseInt(tenantId),
					queueName, "", "", // instagram
					"", "", "", "", F, "", kvPair, kvPair, userData, F, kvPairHolder);
			outCome = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			outCome = false;
		}
		return outCome;
	}
	 public String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
	 
	public static void main(String[] args) {
		try {
			new GenesysClient().submitToGenesys("1636279212065971627_6243317004", "12234", "hello", null, null, "",
					"101", "instagram");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
