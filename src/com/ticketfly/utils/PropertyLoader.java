package com.ticketfly.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	 public static Properties loadLinkerProperties() {

         Properties testProps = new Properties();
         try {
			testProps.load(PropertyLoader.class.getResourceAsStream("/test.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return testProps;
	 }

}
