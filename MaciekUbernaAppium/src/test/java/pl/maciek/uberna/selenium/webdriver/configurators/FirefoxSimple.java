package pl.maciek.uberna.selenium.webdriver.configurators;

import java.util.Map;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxSimple {
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	
	public FirefoxSimple(Map<String, String> drvPar) {
		FirefoxProfile fp = new FirefoxProfile();
		dCaps = DesiredCapabilities.firefox();
		dCaps.setCapability(FirefoxDriver.PROFILE, fp);
		dCaps.setJavascriptEnabled(Boolean.parseBoolean(drvPar.get("setJavascriptEnabled")));
		dCaps.setCapability("trustAllSSLCertificates", drvPar.get("trustAllSSLCertificates"));
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
}
