package pl.maciek.uberna.test.runner;

import pl.maciek.uberna.test.selenium.framework.TestExecuter;
import pl.maciek.uberna.test.selenium.framework.WebDriversConfigJsonFileReader;

public class MainSelleniumRunner {
	
	public static void main(String args[]){
		
		int obligatoryParams = 0;
		String[] paramTbl = new String[15];
		
		String flags[] = {
						  	"-cfgTest",
						  	"-cfgBrowsers"
						 };
		String desc_pl[] = {
							"[Ścieżka do pliku z konfiguracjami testów.] -Wymagane-",
							"[Ścieżka do pliku z konfiguracją przeglądarek www.] -Wymagane-"
					  	};
		
		for (int i=0;i<args.length;i++){
			String flag = args[i].toLowerCase();
			switch(flag){
				case "-cfgtest":
					paramTbl[0]=args[i+1];
					obligatoryParams++;
					break;

				case "-cfgbrowsers":
					paramTbl[1]=args[i+1];
					obligatoryParams++;
					break;
				default:
					System.out.println("Złe parametry. \nDozwolone opcje to:");
					for(int j=0;j<flags.length;j++)
						System.out.println(flags[j]+" "+desc_pl[j]);
					System.exit(-1);
					break;
			}
			i++;
		}
		
		if (obligatoryParams<2){
			System.out.println("Za mało parametrów.");
			for(int j=0;j<flags.length;j++)
				System.out.println(flags[j]+" "+desc_pl[j]);
			System.exit(-2);
		}
		
		WebDriversConfigJsonFileReader wdcjf = new WebDriversConfigJsonFileReader(paramTbl[1]);
		
//		for(TestExecuter test:(new SeleniumParamValidator(paramTbl)).getSeleniumTests()) 
//			test.executeTest();
		
	}
}
