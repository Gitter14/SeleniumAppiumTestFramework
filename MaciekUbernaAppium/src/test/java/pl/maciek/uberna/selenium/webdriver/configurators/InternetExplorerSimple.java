package pl.maciek.uberna.selenium.webdriver.configurators;

import java.util.Map;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InternetExplorerSimple {
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	
	public InternetExplorerSimple(Map<String, String> drvPar) {
		System.setProperty("webdriver.ie.driver", drvPar.get("ieDriverLoc"));
		dCaps = DesiredCapabilities.internetExplorer();
		dCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, 
				Boolean.parseBoolean(drvPar.get("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS")));
		dCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, 
				Boolean.parseBoolean(drvPar.get("forceSslTrust")));
		dCaps.setJavascriptEnabled(Boolean.parseBoolean(drvPar.get("jsEnable")));		
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
}
