package pl.maciek.uberna.guitests.utils;

import java.util.concurrent.TimeUnit;

//import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WebUtils {
	
	private static final long DEFAULT_IMPLICIT_WAIT = 30;

	public static WebElement getWebElement(final WebDriver wd, final String by, final String id){
		switch(id){
		case "id":
			return getWait2(wd,By.id(by));
		case "xpath":
			return getWait1(wd,By.xpath(by));
		case "name":
			return getWait1(wd,By.name(by));
		case "css":
			return getWait1(wd,By.cssSelector(by));
		case "link":
			return getWait1(wd,By.linkText(by));
		case "partialLink":
			return getWait1(wd,By.partialLinkText(by));
		case "tag":
			return getWait1(wd,By.tagName(by));
		case "class":
			return getWait1(wd,By.className(by));
		default:
			System.out.println("Unknown command: "+id);
			return null;
		}
	}

	public static WebElement getWebElement(WebDriver wd, String by, String id, String param){
		switch(id){
		case "id":
			return wd.findElement(By.id(by+"[.='"+param+"']"));
		case "xpath":
			return wd.findElement(By.xpath(by+"[.='"+param+"']"));
		case "name":
			return wd.findElement(By.name(by+"[.='"+param+"']"));
		case "css":
			return wd.findElement(By.cssSelector(by+"[.='"+param+"']"));
		case "link":
			return wd.findElement(By.linkText(by+"[.='"+param+"']"));
		case "partialLink":
			return wd.findElement(By.partialLinkText(by+"[.='"+param+"']"));
		case "tag":
			return wd.findElement(By.tagName(by+"[.='"+param+"']"));
		case "class":
			return wd.findElement(By.className(by+"[.='"+param+"']"));
		default:
			System.out.println("Unknown command: "+by+"[.='"+param+"']");
			return null;
		}
	}
	
	private static WebElement getWait1(final WebDriver d, final By locator ) {
		  StaticLogger.logg( "Get element by locator: " + locator.toString() );  	
		  long startTime = System.currentTimeMillis();
		  d.manage().timeouts().implicitlyWait( 9, TimeUnit.SECONDS );
		  WebElement we = null;
		  boolean unfound = true;
		  boolean visible = false;
		  int tries = 0;
		  while ( unfound && !visible && tries < 4 ) {
		    tries += 1;
		    StaticLogger.logg("Locating remaining time: " + (36-(9*(tries-1) )) + " seconds." );
		    try {
		      we = d.findElement( locator );
		      unfound = false; // FOUND IT
		      StaticLogger.logg("Element is displayed:"+we.isDisplayed());
		      visible = we.isDisplayed();
		    } catch ( StaleElementReferenceException ser ) {						
		      StaticLogger.logg( "ERROR: Stale element. " + locator.toString() );
		      unfound = true;
		    } catch ( NoSuchElementException nse ) {						
		      StaticLogger.logg( "ERROR: No such element. " + locator.toString() );
		      unfound = true;
		    }  catch ( Exception e ) {
		      StaticLogger.logg( e.getMessage() );
		    }
		  } 
		  long endTime = System.currentTimeMillis();
		  long totalTime = endTime - startTime;
		  StaticLogger.logg("Finished click after waiting for " + totalTime + " milliseconds.");
		  d.manage().timeouts().implicitlyWait( DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS );
		  return we;
		}

	
	private static WebElement getWait2( final WebDriver wd, final By locator ) {
		  StaticLogger.logg( "Get element by locator: " + locator.toString() );  
		  final long startTime = System.currentTimeMillis();
		  Wait<WebDriver> wait = new FluentWait<WebDriver>( wd )
		    .withTimeout(30, TimeUnit.SECONDS)
		    .pollingEvery(5, TimeUnit.SECONDS)
		    .ignoring( StaleElementReferenceException.class ) ;
		  int tries = 0;
		  boolean found = false;
		  WebElement we = null;
		  while ( (System.currentTimeMillis() - startTime) < 91000 ) {
		   StaticLogger.logg( "Searching for element. Try number " + (tries++) ); 
		   try {
		    we = wait.until( ExpectedConditions.presenceOfElementLocated( locator ) );
		    found = true;
		    break;
		   } catch ( StaleElementReferenceException e ) {      
		    StaticLogger.logg( "Stale element: \n" + e.getMessage() + "\n");
		   }
		  }
		  long endTime = System.currentTimeMillis();
		  long totalTime = endTime - startTime;
		  if ( found ) {
		   StaticLogger.logg("Found element after waiting for " + totalTime + " milliseconds." );
		  } else {
		   StaticLogger.logg( "Failed to find element after " + totalTime + " milliseconds." );
		  }
		  return we;
		}
}
