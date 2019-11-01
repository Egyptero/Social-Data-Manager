package com.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateConverter {

	@Test
	public void test() {
		//fail("Not yet implemented");
		String sample = "2018-04-22T15:00:24+0000";
		String another = "2018-04-24";
		try {
			//OffsetDateTime dt = OffsetDateTime.parse(sample);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS");
			Date date = sdf.parse(sample);
			System.out.println(date.toString());

			SimpleDateFormat sdfA = new SimpleDateFormat("yyyy-MM-dd");
			Date dateOrg = sdfA.parse(another);
			System.out.println(dateOrg.toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
