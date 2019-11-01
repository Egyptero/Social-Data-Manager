package cxengage.facebook.data;

import org.junit.Test;

import cxengage.facebook.logger.RouterLog;

public class RouterLogTest {

	@Test
	public void test() {
		//request.getSession().getServletContext().getRealPath("WEB-INF/ids.json")
		RouterLog routerLog = new RouterLog("C:\\Users\\Mamdouh\\eclipse-workspace\\Inst-Genesys\\src\\main\\webapp\\WEB-INF\\ids.json");
		routerLog.save();
	}

}
