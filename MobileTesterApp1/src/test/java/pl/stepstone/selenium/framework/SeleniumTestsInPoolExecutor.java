package pl.stepstone.selenium.framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.JsonObject;

public class SeleniumTestsInPoolExecutor {
	
	public SeleniumTestsInPoolExecutor(String testName, JsonObject testProperties, JsonObject allDriversProperties ){
		ExecutorService executor = Executors.newFixedThreadPool(5);
	}
	
}
