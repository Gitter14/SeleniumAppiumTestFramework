package pl.stepstone.selenium.guitests.android;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pl.stepstone.selenium.framework.TestExecuter;

public class AppiumPreTest implements TestExecuter{
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public AppiumPreTest(HashMap<String, String> cfgMap, List<WebDriver> lst){
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
