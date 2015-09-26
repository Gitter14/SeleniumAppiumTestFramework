package pl.stepstone.selenium.framework;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class SeleniumTestFactory implements TestExecuter{
	
	private boolean enable;
	
	private String testName;
	private HashMap<String,String> testMap;
	private HashMap<String, HashMap<String,String>> driversMap;
	
	private DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd/HH:mm:ss]");
	private Date data;
	
	public SeleniumTestFactory(String testName, HashMap<String,String> testMap, HashMap<String, HashMap<String, String>> driversMap){
		enable = Boolean.parseBoolean(testMap.get("enable"));
		if(enable){
			this.testName = testName;
			this.testMap = testMap;
			this.driversMap = driversMap;
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
	public void executeTest() {
		if (enable){
			try {
				setUp();
				Class<?> testClass = Class.forName(testName);
				Constructor<?> testClassConstructor = testClass.getConstructor(HashMap.class,List.class);
				Object object = testClassConstructor.newInstance(new Object[] { driversMap, testMap } );
				TestExecuter testExecuter = (TestExecuter) object;
				testExecuter.executeTest();
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
