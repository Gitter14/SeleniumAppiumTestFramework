package pl.maciek.uberna.selenium.data;

import java.util.ArrayList;
import java.util.Hashtable;

public class Test {
	private String name;
	private Hashtable<String,String> testObjects;
	private ArrayList<Subtest> testList;
	
	public String getName() {
		return name;
	}
	public Hashtable<String, String> getTestObjects() {
		return testObjects;
	}
	public ArrayList<Subtest> getTestList() {
		return testList;
	}
	
}
