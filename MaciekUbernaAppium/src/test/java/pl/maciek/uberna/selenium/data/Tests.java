package pl.maciek.uberna.selenium.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Tests {
	private int numberOfThreadsForTheTests;
	private ArrayList<Test> testy;
	private Hashtable<String,Hashtable<String,String>> allDriversConfigurations;
	
	public int getNumberOfThreadsForTheTests() {
		return numberOfThreadsForTheTests;
	}
	public ArrayList<Test> getTesty() {
		return testy;
	}
	public Hashtable<String, Hashtable<String, String>> getAllDriversConfigurations() {
		return allDriversConfigurations;
	}
	
	public void setTesty(JsonObject tests){
		Gson gsonReceiver = new Gson();
		Set<Map.Entry<String,JsonElement>> entries = tests.entrySet();
		testy = new ArrayList<Test>(entries.size());
		Type type = new TypeToken<Test>(){}.getType();
		for( Map.Entry<String,JsonElement> e : entries){
			Object o = gsonReceiver.fromJson(e.getValue(),type);
			if (o instanceof Test)
				testy.add((Test) o);
			else 
				System.out.println("Error: Wrong Json format.");
		}
	}
	
}
