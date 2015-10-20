package pl.framework.selenium.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.framework.selenium.exception.FrameworkException;
import pl.framework.selenium.framework.SeleniumTestsInPoolExecutor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Tests {
	private int numberOfThreadsForTheTests;
	private List<Test> testy;
	private Hashtable<String,Hashtable<String,String>> allDriversConfigurations;
	
	@SuppressWarnings("unchecked")
	public Tests(JsonObject tests, JsonObject drivers,int threads){
		numberOfThreadsForTheTests = threads;
		Gson gsonReceiver = new Gson();
		Set<Map.Entry<String,JsonElement>> entries = tests.entrySet();
		testy = new ArrayList<Test>(entries.size());
		Type type = new TypeToken<Test>(){}.getType();
		for( Map.Entry<String,JsonElement> e : entries){
			Object o = gsonReceiver.fromJson(e.getValue(),type);
			if (o instanceof Test){
				Test t = ((Test) o);
				t.setName(e.getKey());
				testy.add(t);
			} else {
				try {
					throw new FrameworkException("Error: Wrong Json format: Shoule be of \"Test\" type.");
				} catch(FrameworkException ex) {
					ex.printStackTrace();
					System.exit(-1);
				}
			}
		}
		type = new TypeToken<Hashtable<String,Hashtable<String,String>>>(){}.getType();
		Object o = gsonReceiver.fromJson(drivers,type);
		if (o instanceof Hashtable<?,?>){
			allDriversConfigurations = ((Hashtable<String,Hashtable<String,String>>) o);
		} else {
			try {
				throw new FrameworkException("Error: Wrong Json format: Shoule be of \"Hashtable<?,?>\" type.");
			} catch(FrameworkException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		//System.out.println(allDriversConfigurations.get("Android_SonyEricson_Lista").toString());
	}
	
	public void executeTests(){
		for(Test t : testy){
			for (Subtest s : t.getTestList()){
				SeleniumTestsInPoolExecutor executor = 
						new SeleniumTestsInPoolExecutor(s.enable(), t.getName(), 
								s.params(), s.drivers(), allDriversConfigurations);
				executor.executeMultipleTestInstances(numberOfThreadsForTheTests, 30);
			}
		}
	}
	
}
