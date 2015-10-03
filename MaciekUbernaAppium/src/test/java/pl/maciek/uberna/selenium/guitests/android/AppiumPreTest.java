package pl.maciek.uberna.selenium.guitests.android;

import static org.junit.Assert.fail;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pl.maciek.uberna.selenium.framework.TestExecuter;

public class AppiumPreTest implements TestExecuter{
	  private List<WebDriver> wdList;
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public AppiumPreTest(
			  Hashtable<String, String> cfgMap, List<WebDriver> wdList){
		  this.wdList = wdList;
		  driver = wdList.get(0);
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
		for (WebDriver wd : wdList)
			wd.quit();
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
