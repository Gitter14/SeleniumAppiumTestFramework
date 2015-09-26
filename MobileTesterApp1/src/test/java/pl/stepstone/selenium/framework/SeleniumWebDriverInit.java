package pl.stepstone.selenium.framework;

import pl.stepstone.selenium.framework.TestExecuter;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.google.gson.JsonArray;


public class SeleniumWebDriverInit {
	
	private List<String> driverInfo;
	private List<WebDriver> drvTabl;
	private HashMap<String,HashMap<String, String>> driversMap;
	private String driverName;
	private HashMap<String, String> driverMap;
	
	public SeleniumWebDriverInit(HashMap<String,HashMap<String, String>> driversMap) {
		this.driversMap = driversMap;
	}
	
	public List<WebDriver> getWebDrivers(JsonArray jsonDrvLst) {	
		int arrayLength = jsonDrvLst.getAsJsonArray().size();
		drvTabl = new ArrayList<WebDriver>(arrayLength);
		driverInfo = new ArrayList<String>(arrayLength);
		
		for(int a=0;a<arrayLength;a++){
			driverName = jsonDrvLst.get(a).toString().toLowerCase();
			driverMap = getDrvMap(driverName);
			driverInfo.add(driverName);
			
			if(driverName.startsWith("android")){
				
				File appDir = new File(System.getProperty("user.dir"),getDrvPar("appDir"));
				File app = new File(appDir, getDrvPar("app")); 
				
			    DesiredCapabilities dCaps = new DesiredCapabilities();
			    dCaps.setCapability("appium-version", getDrvPar("appium-version"));
			    dCaps.setCapability(CapabilityType.BROWSER_NAME, getDrvPar("BROWSER_NAME"));
			    dCaps.setCapability("platformName", getDrvPar("platformName"));
			    //dCaps.setCapability("platformVersion", "4.0.4");
			    dCaps.setCapability(CapabilityType.VERSION, getDrvPar("VERSION"));
			    dCaps.setCapability("deviceName", getDrvPar("deviceName"));
			    dCaps.setCapability("app", app.getAbsolutePath());
			    dCaps.setCapability("appPackage", getDrvPar("appPackage"));
			    dCaps.setCapability("appActivity", getDrvPar("appActivity"));
			    
			    try {
			    	drvTabl.add(new AndroidDriver(new URL(getDrvPar("androidURL")), dCaps));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			    
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
	
	private HashMap<String,String> getDrvMap(String driverName){
		HashMap<String,String> map = driversMap.get(driverName);
		if (map==null){
			System.out.println("There is no configuration named: "+driverName);
			System.exit(-1);
			return null;
		} else {
			return map;
		}
	}
	
	private String getDrvPar(String parName){
		String str = driverMap.get(parName);
		if (str==null){
			System.out.println("There is No value named: "+parName+" for driver: "+driverName);
			System.exit(-1);
			return null;
		} else {
			return str;
		}
	}
}
