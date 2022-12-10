package main.java.app.naive;

import main.java.app.AppController;
import main.java.dom2app.SimpleTableModel;

import java.util.ArrayList;
import java.util.List;

public class NaiveClient {

	public static void main(String args[]) {

		int[] myArray = {13, 14, 15};
		for (int i = 0; i < myArray.length; i++) {
			System.out.println("arr[" + i + "] = " + myArray[i]);
		}

		String[] myStringArray = {"11", "abcd", "and this", "and that"};
//		for (String i = 0; i < myArray.length; i++) {
//			System.out.println(myStringArray[i]);
//		}
		/**
		 * 		for (initial-condition; access-inside-the-for-loop-condition ; step)
		 * 	    for (int i = 0;
		 * 	                             i < myArray.length;
		 * 	                             									   i++
		 */


		for (int i = 0; i < myStringArray.length; i++) {
			System.out.println(myStringArray[i]);
		}


			//11, abdc, and this, and that
//		for (String i = String.valueOf(0); i myArray.length; i++); {
//			System.out.println(myStringArray);
//		}
//

		List<Integer> listOfInts = new ArrayList<>();
		listOfInts.add(2);
		listOfInts.add(5);
		int firstValueInListOfInts = listOfInts.indexOf(5);
		listOfInts.remove(0);
		listOfInts.add(10);
		for (int i = 0; i < listOfInts.size(); i++) {
			System.out.println(listOfInts.get(i));
			listOfInts.set(i, 0);
		}
		for (int i = 0; i < listOfInts.size(); i++) {
			System.out.println(listOfInts.get(i));
		}



		List<String> listOfStrings = new ArrayList<>();
		listOfStrings.add("whatever");
		listOfStrings.add("strings");
		System.out.println(listOfStrings);
		for (int i = 0; i < listOfStrings.size(); i++) {
			System.out.println(listOfStrings.get(i));
		}





		AppController appController = new AppController();
		SimpleTableModel m = appController.load("./src/main/resources/input/EggsScrambled.tsv", "\t");
		System.out.println();System.out.println();
		System.out.println("----------");
		System.out.println(m.toString());
		System.out.println("----------");
		
		m = appController.getByPrefix("Put");
		System.out.println();System.out.println();
		System.out.println(m.toString());

		m = appController.getById(200);
		System.out.println();System.out.println();
		System.out.println(m.toString());

		m = appController.getTopLevel();
		System.out.println();System.out.println();
		System.out.println(m.toString());
	
		appController.createReportText("./src/main/resources/output/Eggs.tsv");
		appController.createReportMd("./src/main/resources/output/Eggs.md");
		appController.createReportHtml("./src/main/resources/output/Eggs.html");
		System.out.println("End of dummy client");
	}

}
