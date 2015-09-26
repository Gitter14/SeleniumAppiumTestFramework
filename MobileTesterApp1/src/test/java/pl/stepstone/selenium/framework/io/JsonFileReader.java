package pl.stepstone.selenium.framework.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.internal.matchers.InstanceOf;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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
}
