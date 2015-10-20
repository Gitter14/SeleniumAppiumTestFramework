package pl.framework.selenium.webdriver.configurators;

import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OperaSimple {
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	
	public OperaSimple(Map<String, String> drvPar) {
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(Boolean.parseBoolean(drvPar.get("jsEnable")));
		System.setProperty("webdriver.opera.driver",drvPar.get("operaDriverLoc"));
		dCaps.setCapability("opera.binary", drvPar.get("operaDriverLoc"));
		dCaps.setCapability("opera.log.level", drvPar.get("logLevel"));
		dCaps.setCapability("opera.profile", drvPar.get("profile"));
		dCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, 
				Boolean.getBoolean(drvPar.get("ACCEPT_SSL_CERTS")));
	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
}
