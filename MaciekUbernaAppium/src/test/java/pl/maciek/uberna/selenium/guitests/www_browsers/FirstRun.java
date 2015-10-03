package pl.maciek.uberna.selenium.guitests.www_browsers;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import pl.maciek.uberna.selenium.framework.TestExecuter;

public class FirstRun implements TestExecuter{
  private List<WebDriver> wdList;
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  
  public FirstRun(
		  Hashtable<String, String> cfgMap, List<WebDriver> wdList){
	this.wdList = wdList;
  	driver = wdList.get(0);
  	baseUrl = cfgMap.get("baseUrl");
  }
  
  @Before
  public void setUp() throws InterruptedException {
	driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSeleniumIDErecord1() throws InterruptedException {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.id("main-search-text")).clear();
	    driver.findElement(By.id("main-search-text")).sendKeys("quadrocopter");
	    if (!driver.findElement(By.cssSelector("input[name=\"description\"]")).isSelected()) {
	      driver.findElement(By.cssSelector("input[name=\"description\"]")).click();
	    };
	    driver.findElement(By.cssSelector("strong[title=\"wszystkie działy\"]")).click();
	    driver.findElement(By.linkText("wszystkie działy")).click();
	    driver.findElement(By.cssSelector("input.sprite.search-btn")).click();
	    driver.findElement(By.cssSelector("a.param-toggle > span")).click();
	    driver.findElement(By.cssSelector("label.buyNow > a.param-toggle > span")).click();
	    driver.findElement(By.id("price_from")).clear();
	    driver.findElement(By.id("price_from")).sendKeys("100");
	    driver.findElement(By.id("price_to")).clear();
	    driver.findElement(By.id("price_to")).sendKeys("400");
	    new Select(driver.findElement(By.name("state"))).selectByVisibleText("z mazowieckiego");
	    driver.findElement(By.id("city_id")).clear();
	    driver.findElement(By.id("city_id")).sendKeys("warszawa");
	    driver.findElement(By.xpath("//section[@id='featured-offers']/article[5]")).click();
	    driver.navigate().back();
	    driver.findElement(By.xpath("//section[@id='featured-offers']/article[1]")).click();
	    driver.navigate().back();
	    driver.findElement(By.xpath("//div[@class='listing-options-bottom']//span[.='2']")).click();
	    driver.findElement(By.xpath("//section[@id='featured-offers']/article[1]")).click();
	    driver.navigate().back();  
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
			testSeleniumIDErecord1();
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
