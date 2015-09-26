package pl.maciek.uberna.guitests.android.test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pl.maciek.uberna.test.selenium.framework.TestExecuter;

public class AppiumTest implements TestExecuter{
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public AppiumTest(HashMap<String, String> cfgMap, List<WebDriver> lst){
		  	driver = lst.get(0);
	  }

	  @Before
	  public void setUp() throws InterruptedException {
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }	  
	  
	  @Test
	  public void testSeleniumAppium() throws InterruptedException {
		  
	  }
	  
	  @After
	  public void tearDown() throws InterruptedException {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	@Override
	public void executeTest() {
		try {
			setUp();
			testSeleniumAppium();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				tearDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
