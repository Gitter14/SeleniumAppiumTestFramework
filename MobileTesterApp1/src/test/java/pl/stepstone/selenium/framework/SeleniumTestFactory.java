package pl.stepstone.selenium.framework;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SeleniumTestFactory implements Runnable{
	
	private boolean enable;
	private String testName;
	private JsonObject allTestProperties;
	private JsonObject allDriversProperties;
	private List<String> testDriversNames;
	
	private DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd/HH:mm:ss]");
	private Date data;
	
	public SeleniumTestFactory(String testName, JsonObject testProperties, JsonObject allDriversProperties){
		enable = testProperties.get("Enable").getAsBoolean();
		if(enable){
			this.testName = testName;
			this.allTestProperties = testProperties;
			this.allDriversProperties = allDriversProperties;
			JsonArray props = testProperties.getAsJsonArray("Webdrivers");
			testDriversNames = new ArrayList<String>(props.size());
			Iterator<JsonElement> driversNames = props.iterator();
			while (driversNames.hasNext())
				testDriversNames.add(driversNames.next().getAsString());
		}
	}
	
	private void setUp() throws InterruptedException{
		data = new Date();
		System.out.println(dateFormat.format(data)+" "+testName+" rozpoczęty.");
	}
	
	private void tearDown() throws Exception{
		data = new Date();
		System.out.println(dateFormat.format(data)+" "+testName+" zakończony.");
	}

	@Override
	public void run() {
		if (enable){
			try {
				setUp();
				Class<?> testClass = Class.forName(testName);
				Constructor<?> testClassConstructor = testClass.getConstructor(JsonObject.class,JsonObject.class);
				Object object = testClassConstructor.newInstance(new Object[] { allTestProperties, allDriversProperties } );
				if (object instanceof TestExecuter){
					TestExecuter testExecuter = (TestExecuter) object;
					testExecuter.executeTest();
				} else {
					System.out.println("Generated class by "+this.getClass().getName()+
							" is not TestExecuter type therefor canot be executed by it.");
					System.exit(-1);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					tearDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
