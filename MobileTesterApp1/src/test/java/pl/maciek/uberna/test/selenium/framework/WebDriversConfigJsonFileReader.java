package pl.maciek.uberna.test.selenium.framework;

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

public class WebDriversConfigJsonFileReader {
	
	private Map<String, HashMap<String,String>> driversMap;
	private List<String> driversNamesList;
	
	  /**
	   Constructor.
	   @param jsonPath full name of an existing, readable Json file.
	  */
	
	@SuppressWarnings("unchecked")
	public WebDriversConfigJsonFileReader(String jsonPath){
		try {
			String configJsonString = new String(Files.readAllBytes(Paths.get(jsonPath)));
			JsonElement json=new JsonParser().parse(configJsonString);
			Type type = new TypeToken<Map<String, HashMap<String,String>>>(){}.getType();
			Gson gson=new Gson();
			Object o = gson.fromJson(configJsonString,type);
			
			if (o instanceof Map<?,?>)
				driversMap=(Map<String,HashMap<String,String>>) o;
			else 
				driversMap = null;
			
			JsonObject obj=json.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
			driversNamesList = new ArrayList<String>(entries.size());
			for (Map.Entry<String, JsonElement> entry: entries) {
				driversNamesList.add(entry.getKey());
				JsonObject obj2=obj.getAsJsonObject(entry.getKey());
				Set<Map.Entry<String, JsonElement>> params = obj2.entrySet();
				for (Map.Entry<String, JsonElement> param: params) {
					System.out.println("Parametr "+entry.getKey()+" to "+param.getKey()+
							" o wartości "+param.getValue());
				}
			}
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
