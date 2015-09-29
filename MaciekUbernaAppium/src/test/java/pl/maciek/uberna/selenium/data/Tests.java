package pl.maciek.uberna.selenium.data;

import java.util.ArrayList;
import java.util.Hashtable;

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
}
