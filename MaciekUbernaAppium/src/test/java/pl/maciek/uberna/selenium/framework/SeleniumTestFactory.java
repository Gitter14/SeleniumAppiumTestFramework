package pl.maciek.uberna.selenium.framework;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import pl.maciek.uberna.selenium.framework.SeleniumWebDriverInit;

public class SeleniumTestFactory implements Runnable{
	
	private String testName;
	private String driversInitString;
	private Map<String,String> testProperties;
	private Map<String,Hashtable<String,String>> allDriversProperties;
	
	private DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd/HH:mm:ss]");
	private Date data;
	
	public SeleniumTestFactory(String testName, Map<String,String> testProperties, 
			String driversInitString, Map<String,Hashtable<String,String>> allDriversProperties){
		this.testName = testName;
		this.testProperties = testProperties;
		this.allDriversProperties = allDriversProperties;
		this.driversInitString = driversInitString;
	}
	
	private void setUp() throws InterruptedException{
		data = new Date();
		System.out.println(dateFormat.format(data)+" "+testName+" "+driversInitString+" started test.");
	}
	
	private void tearDown() throws Exception{
		data = new Date();
		System.out.println(dateFormat.format(data)+" "+testName+" "+driversInitString+" ended test.");
	}

	@Override
	public void run() {
		try {
			setUp();
			Class<?> testClass = Class.forName(testName);
			Constructor<?> testClassConstructor = testClass.getConstructor(Map.class,List.class);
			Object object = testClassConstructor.newInstance(new Object[] { testProperties, 
					(new SeleniumWebDriverInit(allDriversProperties)).getWebDrivers(driversInitString) } );
			if (object instanceof TestExecuter){
				TestExecuter testExecuter = (TestExecuter) object;
				testExecuter.executeTest();
			} else {
				System.out.println("Generated class by "+this.getClass().getName()+
						" is not TestExecuter type therefor canot be executed by it.");
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
