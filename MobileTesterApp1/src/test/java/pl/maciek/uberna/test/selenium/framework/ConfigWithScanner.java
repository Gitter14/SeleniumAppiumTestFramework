package pl.maciek.uberna.test.selenium.framework;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class ConfigWithScanner {
	  
	  /**
	   Constructor.
	   @param aFileName full name of an existing, readable file.
	  */
	  public ConfigWithScanner(String aFileName){
	    fFilePath = Paths.get(aFileName);
	    currTags = new ArrayList<String>(70);
	    conf = new HashMap<String,ArrayList<HashMap<String, String>>>(70);
	  }
	  
	  
	  public HashMap<String, ArrayList<HashMap<String, String>>> getConf() {
		return conf;
	  }
	  
	  public ArrayList<String> getTags(){
		  return currTags;
	  }


	/** Template method that calls {@link #processLine(String)}.  */
	  public final void processLineByLine() throws IOException {
	    try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
	      boolean started = false;
	      while (scanner.hasNextLine()){
	    	  String tmp = scanner.nextLine();
	    	  if(!started){
	    		  if(processHeaderLine(tmp)) started=true;
	    	  } else {
	    		  if (!processContentLine(tmp))
	    			  processHeaderLine(tmp);
	    	  }
	      }
	    }
	  }
	  
	  private boolean processHeaderLine(String aLine){
	    //use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(aLine);
	    scanner.useDelimiter("#");
	    if (scanner.hasNext()){
	    	String tmp = scanner.next();
	    	if(!aLine.contains("#")){
	    		scanner.close();
	    		return false;
	    	} else {
	    		currTag = tmp;
	    		if(!conf.containsKey(tmp)){
	    			currTags.add(tmp);
	    			conf.put(tmp,new ArrayList<HashMap<String,String>>(20));
	    		}
	    		conf.get(tmp).add(new HashMap<String,String>(20));
	    	    scanner.close();
	    	    return true;
	    	}
	    }
	    
	    scanner.close();
	    return false;
	  }
	  
	  private boolean processContentLine(String aLine){
		    //use a second Scanner to parse the content of each line 
		    Scanner scanner = new Scanner(aLine);
		    scanner.useDelimiter("\\|");
		    if (scanner.hasNext()){
		    	String tmp = scanner.next();
		    	if(!aLine.contains("|")){
		    		scanner.close();
		    		return false;
		    	} else {
		    		if(scanner.hasNext()){
		    			//System.out.println("tmpConfigScanner:"+tmp);
		    			String sTmp = scanner.next();
			    		conf.get(currTag).get(conf.get(currTag).size()-1).put(tmp, sTmp);
			    		if(scanner.hasNext()){
			    			conf.get(currTag).get(conf.get(currTag).size()-1).put(sTmp, scanner.next());
			    		}
			    	    scanner.close();
			    	    return true;
		    		} else {
		    			scanner.close();
		    			return false;
		    		}
		    	}
		    }
		    
		    scanner.close();
		    return false;
	  }
	  
	  // PRIVATE 
	  private final Path fFilePath;
	  private final static Charset ENCODING = StandardCharsets.UTF_8;
	  private ArrayList<String> currTags;
	  private HashMap<String,ArrayList<HashMap<String, String>>> conf;
	  private String currTag;
}
