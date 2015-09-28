package pl.maciek.uberna.selenium.framework;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class SeleniumWebDriverInit {
	
	private Map<String,Map<String,String>> allDrivesParams;
	private List<String> driverInfo;
	private List<WebDriver> drvTabl;
	
	public SeleniumWebDriverInit(Map<String,Map<String,String>> allDrivesParams) {
		this.allDrivesParams = allDrivesParams;
	}
	
	public List<WebDriver> getWebDrivers(String webDrvType) {
		String[] webDriversNames = webDrvType.split(",");
		
		for(String driverName : webDriversNames){
			if(driverName.startsWith("android")){
			    AppiumAndroid aa = new AppiumAndroid(allDrivesParams.get(driverName));
			    drvTabl.add(new AndroidDriver(aa.getHubUrl(), aa.getDesiredCapabilities()));    
			} else if (driverName.startsWith("firefox")){
				
			} else if (driverName.startsWith("chrome")) {
				
			} else if (driverName.startsWith("phantomjs")) {
				
			} else if (driverName.startsWith("opera")) {
				
			} else if (driverName.startsWith("ie")||driverName.startsWith("internetexplorer")) {
				
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
