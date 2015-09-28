package pl.maciek.uberna.selenium.webdriver.configurators;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumAndroid {
	
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	private String Url;
	
	public AppiumAndroid(Map<String, String> drvPar) {
		
		File appDir = new File(System.getProperty("user.dir"),drvPar.get("appDir"));
		File app = new File(appDir, drvPar.get("app"));
		
		dCaps.setCapability("appium-version", drvPar.get("appium-version"));
	    dCaps.setCapability(CapabilityType.BROWSER_NAME, drvPar.get("BROWSER_NAME"));
	    dCaps.setCapability("platformName", drvPar.get("platformName"));
	    dCaps.setCapability("platformVersion", drvPar.get("VERSION"));
	    dCaps.setCapability(CapabilityType.VERSION, drvPar.get("VERSION"));
	    dCaps.setCapability("deviceName", drvPar.get("deviceName"));
	    dCaps.setCapability("app", app.getAbsolutePath());
	    dCaps.setCapability("appPackage", drvPar.get("appPackage"));
	    dCaps.setCapability("appActivity", drvPar.get("appActivity"));
	    
	    Url = drvPar.get("androidURL");
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
	
	public URL getHubUrl(){
		try {
			return new URL(Url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
