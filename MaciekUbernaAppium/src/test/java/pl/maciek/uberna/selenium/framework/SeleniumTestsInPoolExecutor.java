package pl.maciek.uberna.selenium.framework;

import java.util.List;
import java.util.Map;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SeleniumTestsInPoolExecutor {

	private Boolean enable;
	private String testName;
	private Map<String,String> testProperties;
	private List<String> testDriverNamesToRunTests;
	private Map<String,Hashtable<String,String>> allDriversProperties;
	
	public SeleniumTestsInPoolExecutor(Boolean enable, String testName, Map<String,String> testProperties,
			List<String> testDriverNamesToRunTests, Map<String,Hashtable<String,String>> allDriversProperties ){
		this.enable = enable;
		if (enable){
			this.allDriversProperties = allDriversProperties;
			this.testDriverNamesToRunTests = testDriverNamesToRunTests;
			this.testProperties = testProperties;
			this.testName = testName;
		}
	}
	
	public void executeMultipleTestInstances(int threads,long waitForSingleTestToFinish){
		if (enable) {
			SeleniumTestsRejectedExecutionHandler rh = new SeleniumTestsRejectedExecutionHandler();
			ThreadFactory threadFactory = Executors.defaultThreadFactory();
			
			ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, threads, waitForSingleTestToFinish*2, TimeUnit.SECONDS, 
					new ArrayBlockingQueue<Runnable>(testDriverNamesToRunTests.size()), threadFactory, rh);
			
			SeleniumTestsThreadPoolMonitor sttpm = new SeleniumTestsThreadPoolMonitor(executor, 2);
			Thread monitor = new Thread(sttpm);
			monitor.start();
			
			for (String testDriverNameToRunTest : testDriverNamesToRunTests) {
				Runnable testThread = new SeleniumTestFactory(testName, 
						testProperties, testDriverNameToRunTest, allDriversProperties);
				executor.execute(testThread);
			}
			executor.shutdown();
			while (!executor.isTerminated()){
				
			}
			System.out.println("{Finished test: "+testName+" for all devices given.}");
		}
	}
	
	public void executeSingleTestInstance(){
		if (enable) {
			for (String testDriverNameToRunTest : testDriverNamesToRunTests) {
				SeleniumTestFactory test = new SeleniumTestFactory(testName, 
						testProperties, testDriverNameToRunTest, allDriversProperties);
				test.run();
			}		
		}
	}
	
}
