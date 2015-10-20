package pl.framework.selenium.data;

import java.util.ArrayList;
import java.util.Hashtable;

import pl.framework.selenium.exception.FrameworkException;

public class Test {
	private String name;
	private boolean nameIsNotSet = true;
	private Hashtable<String,String> testObjects;
	private ArrayList<Subtest> testList;
	
	public String getName() {
		return name;
	}
	public void setName(String name){
		if (nameIsNotSet) {
			this.name = name;
			nameIsNotSet = false;
		} else {
			try {
				throw new FrameworkException("Field \"name\" of class\n"+
						this.getClass().getName()+" has been already set with value\n\""+
						this.name+"\"");
			} catch (FrameworkException e) {
				e.printStackTrace();
			}
		}
	}
	public Hashtable<String, String> getTestObjects() {
		return testObjects;
	}
	public ArrayList<Subtest> getTestList() {
		return testList;
	}
}
