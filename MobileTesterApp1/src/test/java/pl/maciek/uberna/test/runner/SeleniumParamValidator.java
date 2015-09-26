package pl.maciek.uberna.test.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.maciek.uberna.test.selenium.framework.ConfigWithScanner;
import pl.maciek.uberna.test.selenium.framework.SeleniumWebDriverInit;
import pl.maciek.uberna.test.selenium.framework.TestExecuter;

public class SeleniumParamValidator {
	
	private List<TestExecuter> theTests;
	
	public SeleniumParamValidator(String[] cfgTbl) {
		
		HashMap<String,String> browserCfg=null;
		if(cfgTbl[1]!=null && validateFile(cfgTbl[1])){
			browserCfg = getBrowserConfig(cfgTbl[1]);
		} else {
			System.out.println("browsers-config error.");
			System.exit(-3);
		}
		
		if(cfgTbl[0]!=null && validateFile(cfgTbl[0])){
			if(browserCfg != null){
				//System.out.println("cfgTbl[0]:"+cfgTbl[0]);
				theTests = getSeleniumTests(cfgTbl[0],browserCfg);
			} else {
				System.out.println("test-config error: null in browserCfg.");
				System.exit(-4);
			}
		} else {
			System.out.println("test-config error.");
			System.exit(-3);
		}
	}
	
	private List<TestExecuter> getSeleniumTests(String testCfgPath,HashMap<String,String> browserCfg){
		  ConfigWithScanner scanner = new ConfigWithScanner(testCfgPath);
		  try {
			  scanner.processLineByLine();
		  } catch(IOException e) {
			  e.printStackTrace();
			  System.exit(-4);
		  }
		  
		  HashMap<String,ArrayList<HashMap<String,String>>> map = scanner.getConf();
		  List<String> tags = scanner.getTags();
		  List<TestExecuter> te = new ArrayList<TestExecuter>(tags.size());
		  
		  for(String testTag:tags){
			  if(testTag!=null){
				for(HashMap<String, String> testMap:map.get(testTag)){
					//te.add(new SeleniumWebDriverInit(browserCfg, testMap, testTag));
				}
			  }
		  }
		  
		  return te;
	}
	
	private HashMap<String, String> getBrowserConfig(String cfg){
		  ConfigWithScanner scanner = new ConfigWithScanner(cfg);
		  try {
			  scanner.processLineByLine();
		  } catch(IOException e) {
			  e.printStackTrace();
			  System.exit(-4);
		  }
		  
		  HashMap<String,ArrayList<HashMap<String,String>>> cfgMap = scanner.getConf();
		  String firstTag = scanner.getTags().get(0);
		  
		  if(firstTag!=null){
			  return cfgMap.get(firstTag).get(0);
		  } else {
			  System.out.println("Browser cfg tag = null. Exiting...");
			  System.exit(-4);
		  }
		  return null;
	}
	
	private boolean validateFile(String path){
		File f = new File(path);
		
		String komunikaty_pl[] = {
				"Nie istnieje.",
				"To nie jest plik tylko folder.",
				"Nie da się czytać tego pliku."
		};
		
		if (!f.exists()) {
			System.out.println(path+" "+komunikaty_pl[0]);
			return false;
		}
		
		if (f.isDirectory()) {
			System.out.println(path+" "+komunikaty_pl[1]);
			return false;
		}

		if (!f.canRead()) {
			System.out.println(path+" "+komunikaty_pl[2]);
			return false;
		}

		return true;
	}
	
	public List<TestExecuter> getSeleniumTests(){
		return theTests;
	}
	
}
