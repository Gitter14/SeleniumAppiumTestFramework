package pl.framework.selenium.guitests.android;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pl.framework.selenium.framework.TestExecuter;

public class AppiumPreTest implements TestExecuter{
	  private List<WebDriver> wdList;
	  private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public AppiumPreTest(
			  Map<String, String> cfgMap, List<WebDriver> wdList){
		  this.wdList = wdList;
		  driver = wdList.get(0);
	  }

	  @Before
	  public void setUp() throws InterruptedException {
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }	  
	  
	  @Test
	  public void testSeleniumAppium() throws InterruptedException {
		  driver.findElement(By.className("UIACollectionCell")).click();
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
