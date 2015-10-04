package pl.maciek.uberna.selenium.webdriver.configurators;

import java.util.Map;

import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJsSimple {
	private DesiredCapabilities dCaps = new DesiredCapabilities();
	
	public PhantomJsSimple(Map<String, String> drvPar) {
		System.setProperty("phantomjs.binary.path",drvPar.get("phantomjsDriverLoc"));
		dCaps = new DesiredCapabilities();
	    dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
	    		drvPar.get("phantomjsArguments"));
		dCaps.setJavascriptEnabled(Boolean.parseBoolean(drvPar.get("jsEnable")));
		dCaps.setCapability("takesScreenshot", Boolean.parseBoolean(drvPar.get("takesScreenshot")));
		dCaps.setCapability("localToRemoteUrlAccessEnabled",
				Boolean.parseBoolean(drvPar.get("localToRemoteUrlAccessEnabled")));
		dCaps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                drvPar.get("phantomjsDriverLoc")
            );

	}
	
	public DesiredCapabilities getDesiredCapabilities(){
		return dCaps;
	}
}
