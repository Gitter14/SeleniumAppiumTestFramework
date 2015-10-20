package pl.framework.selenium.framework.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonFileReader {
	
	private JsonObject jsonObj;
	
	  /**
	   Constructor.
	   @param jsonPath full name of an existing, readable Json file.
	  */
	
	public JsonFileReader(String jsonPath){
		
		try {
			String configJsonString = new String(Files.readAllBytes(Paths.get(jsonPath)));
			jsonObj = new JsonParser().parse(configJsonString).getAsJsonObject();
		} catch (JsonIOException e1) {
			
			e1.printStackTrace();
		} catch (JsonSyntaxException e2) {
			
			e2.printStackTrace();
		} catch (FileNotFoundException e3) {
			
			e3.printStackTrace();
		} catch (IOException e4) {
			
			e4.printStackTrace();
		}	
	}
	
	public JsonObject getJson(){
		return jsonObj;
	}
	
}
