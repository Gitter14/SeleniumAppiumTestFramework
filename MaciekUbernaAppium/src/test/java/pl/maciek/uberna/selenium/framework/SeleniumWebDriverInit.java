package pl.maciek.uberna.selenium.framework;

import io.appium.java_client.android.AndroidDriver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import pl.maciek.uberna.selenium.webdriver.configurators.AppiumAndroid;
import pl.maciek.uberna.selenium.webdriver.configurators.ChromeSimple;
import pl.maciek.uberna.selenium.webdriver.configurators.FirefoxSimple;
import pl.maciek.uberna.selenium.webdriver.configurators.InternetExplorerSimple;
import pl.maciek.uberna.selenium.webdriver.configurators.OperaSimple;
import pl.maciek.uberna.selenium.webdriver.configurators.PhantomJsSimple;


public class SeleniumWebDriverInit {
	
	private Map<String,Hashtable<String,String>> allDrivesParams;
	private List<String> driverInfo;
	private List<WebDriver> drvTabl;
	
	public SeleniumWebDriverInit(Map<String,Hashtable<String,String>> allDrivesParams) {
		this.allDrivesParams = allDrivesParams;
	}
	
	public List<WebDriver> getWebDrivers(String webDrvType) {
		String[] webDriversNames = webDrvType.split(",");
		drvTabl = new ArrayList<WebDriver>(webDriversNames.length);
		driverInfo = new ArrayList<String>(webDriversNames.length);
		for(String driverName : webDriversNames){
			driverInfo.add(driverName);
			driverName = driverName.toLowerCase();
			if(driverName.startsWith("android")){
			    AppiumAndroid aa = new AppiumAndroid(allDrivesParams.get(driverName));
			    drvTabl.add(new AndroidDriver(aa.getHubUrl(), aa.getDesiredCapabilities()));    
			} else if (driverName.startsWith("firefox")){
				FirefoxSimple fS = new FirefoxSimple(allDrivesParams.get(driverName));
				drvTabl.add(new FirefoxDriver(fS.getDesiredCapabilities()));
			} else if (driverName.startsWith("chrome")) {
				ChromeSimple chS = new ChromeSimple(allDrivesParams.get(driverName));
				drvTabl.add(new FirefoxDriver(chS.getDesiredCapabilities()));
			} else if (driverName.startsWith("phantomjs")) {
				PhantomJsSimple pJsS = new PhantomJsSimple(allDrivesParams.get(driverName));
				drvTabl.add(new PhantomJSDriver(pJsS.getDesiredCapabilities()));				
			} else if (driverName.startsWith("opera")) {
				OperaSimple oS = new OperaSimple(allDrivesParams.get(driverName));
				drvTabl.add(new OperaDriver(oS.getDesiredCapabilities()));				
			} else if (driverName.startsWith("ie")||driverName.startsWith("internetexplorer")) {
				InternetExplorerSimple ieS = new InternetExplorerSimple(allDrivesParams.get(driverName));
				drvTabl.add(new InternetExplorerDriver(ieS.getDesiredCapabilities()));				
			} else {
				System.out.println("Error unknown browser type: "+driverName);
				System.exit(-1);
			}
		}
			
		return drvTabl;
	}
	
	public String getDriverInfo(int index){
		return driverInfo.get(index);
	}
	
}
