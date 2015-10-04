package pl.maciek.uberna.selenium.webdriver.configurators;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeSimple {
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	
	public ChromeSimple(Map<String, String> drvPar) {
		System.setProperty("webdriver.chrome.driver", drvPar.get("chromeDriverLoc"));
		dCaps = DesiredCapabilities.chrome();
		ChromeOptions arguments = new ChromeOptions();
	    for(String args:drvPar.get("chromeArguments").split(","))
	    	arguments.addArguments(args);
	    dCaps.setCapability("chrome.binary", drvPar.get("chromeDriverLoc"));
	    dCaps.setJavascriptEnabled(Boolean.parseBoolean(drvPar.get("jsEnable")));
	    dCaps.setCapability(ChromeOptions.CAPABILITY, arguments);	    
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
}
