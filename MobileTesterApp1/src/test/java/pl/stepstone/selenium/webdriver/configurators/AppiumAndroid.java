package pl.stepstone.selenium.webdriver.configurators;

import java.io.File;

import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumAndroid {
	
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	private String Url;
	
	public AppiumAndroid(JsonObject params) {
		
		File appDir = new File(System.getProperty("user.dir"),params.get("appDir").getAsString());
		File app = new File(appDir, params.get("app").getAsString());
		
		dCaps.setCapability("appium-version", params.get("appium-version").getAsString());
	    dCaps.setCapability(CapabilityType.BROWSER_NAME, params.get("BROWSER_NAME").getAsString());
	    dCaps.setCapability("platformName", params.get("platformName").getAsString());
	    dCaps.setCapability("platformVersion", params.get("VERSION").getAsString());
	    dCaps.setCapability(CapabilityType.VERSION, params.get("VERSION").getAsString());
	    dCaps.setCapability("deviceName", params.get("deviceName").getAsString());
	    dCaps.setCapability("app", app.getAbsolutePath());
	    dCaps.setCapability("appPackage", params.get("appPackage").getAsString());
	    dCaps.setCapability("appActivity", params.get("appActivity").getAsString());
	    
	    Url = params.get("androidURL").getAsString();
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
