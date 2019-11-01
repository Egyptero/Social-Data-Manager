package cxengage.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.log4j.Logger;

public class ManualDataLoader extends Thread {

	Client client = ClientBuilder.newClient();
	WebTarget resource = client.target("http://18.208.70.80:5046");
	Logger logger = Logger.getLogger(ManualDataLoader.class);
	
	@Override
	public void run() {
		logger.debug("Loading Old Json Data");
		
		File directory = new File("C:\\apache-tomcat-9.0.11\\webapps\\InstGenesys\\WEB-INF\\");
		String[] directoryContents = directory.list();

		logger.debug("The Files loaded are counting:"+directoryContents.length);
		for (String fileName : directoryContents) {
			File temp = new File(String.valueOf(directory), fileName);
			if (fileName.contains("TwitterHistory_batch")) {

				String feeds = readFile(temp);
				if(sendToRest(feeds))
					temp.renameTo(new File(String.valueOf(directory), fileName.replace(".json", ".back")));
				logger.debug("Sending file name = " + fileName+"\nAnd content is:"+feeds);
			}
			//System.out.println(String.valueOf(temp));
		}
	}


	String readFile(File filename) {
		
		try {
			byte[] bytes = Files.readAllBytes(filename.toPath());
			return new String(bytes, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	boolean sendToRest(String input) {
		Builder request = resource.request(MediaType.APPLICATION_JSON);

		request.accept(MediaType.APPLICATION_JSON);

		Response response = null;
		response = request.post(Entity.entity(input, MediaType.APPLICATION_JSON));

		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			return true;
		} else {
			return false;
		}
		
	}

}
