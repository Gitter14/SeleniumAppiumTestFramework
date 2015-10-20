package pl.framework.selenium.data;

import java.util.Hashtable;
import java.util.ArrayList;

public class Subtest {
	private Boolean enable;
	private ArrayList<String> webdrivers;
	private Hashtable<String,String> parameters;
	
	public Boolean enable(){
		return enable;
	}

	public ArrayList<String> drivers() {
		return webdrivers;
	}

	public Hashtable<String, String> params() {
		return parameters;
	}
}
