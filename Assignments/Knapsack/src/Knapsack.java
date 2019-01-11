import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author AnneMarie Caballero (<a> href="https://github.com/annemariecabs">annemariecabs</a>)
 *
 */

public class Knapsack {
	
	public static final String OUTPUT_NAME = "knapsack.txt";
	public static List<Integer> list;
	
	//TODO: make sure you check if something is invalid and if so, print something appropriate to knapsack.txt
	public static void main(String[] args) {
		list = new ArrayList<Integer>();
		
		int[] watermelon3 = {30, 35, 40, 50, 25, 10, 13}; 
		System.out.println(knapsackSum(watermelon3, watermelon3.length, 85, list));
		System.out.println(list);
		 /*
		int[] watermelon1 = {2, 17, 5, 3};
		System.out.println(knapsackSum(watermelon1, watermelon1.length, 132, list));
		System.out.println(list);
		/*
		int[] watermelon2 = {30, 40, 50};
		System.out.println(knapsackSum(watermelon2, watermelon2.length, 25));
		
		int[] watermelon3 = {70, 10, 60, 65, 15, 20}; 
		System.out.println(knapsackSum(watermelon3, watermelon3.length, 85));
		*/
		
		
	}
	
	//PRECONDITION: N = LENGTH OF ARRAY
	private static int knapsackSum(int[] w, int n, int limit) {
		int added, notAdded;
		
		//TODO: do i take out limit < 0 , IS N - 1 right or N
		if(n <= 0 || limit < 0)
			return 0;
		else if(w[n - 1] <= limit) {
			added = knapsackSum(w, n - 1, limit - w[n - 1]) + w[n - 1];
			notAdded = knapsackSum(w, n - 1, limit);
			
			if(added > notAdded)
				return added;
			else
				return notAdded;
		}
		else
			return knapsackSum(w, n - 1, limit);
			
	}
	
	//PRECONDITION: N = LENGTH OF ARRAY
	//TODO: Does order of items matter bc order of items is reversed
	private static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		int added, notAdded;
		ArrayList<Integer> addedList = new ArrayList<Integer>(), notAddedList = new ArrayList<Integer>();
		
		//TODO: Should I remove redundancy?
		//TODO: If there are multiple possible paths, do I have to show them all?
		if(n <= 0 || limit < 0)
			return 0;
		else if(w[n - 1] <= limit) {
			addedList.add(w[n -1]);
			
			added = knapsackSum(w, n - 1, limit - w[n - 1], addedList) + w[n - 1];
			notAdded = knapsackSum(w, n - 1, limit, notAddedList);
			
			if(added > notAdded) {
				for(Integer i: addedList) {
					list.add(i);
				}
				return added;
			}
			else {
				for(Integer i: notAddedList) {
					list.add(i);
				}
				return notAdded;	
			}
		}
		else
			return knapsackSum(w, n - 1, limit, addedList);
	}
	
	public static int[] readFileWithItems (String inputName) {
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(inputName);
		String temp = "";
		Scanner keyboard = new Scanner(System.in);
		
		while (! file.exists()){
			System.out.println("Please enter the name of an existing input file"
					+ " (type exit to exit the program)");
			
			temp = keyboard.nextLine();
			
			if(temp.equals("exit")) 
				System.exit(1);
			else 
				file = new File(temp);
		}
		
		keyboard.close();
		
		Path path = file.toPath();
		try {
			lines = (ArrayList<String>) Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Change this catch block to a System.exit and an explanation
			//or let it keep going and print an acceptable explanation to knapsack.txt
			e.printStackTrace();
		}
		
		int num;
		int[] weights = new int[lines.size()];
		
		for(int j = 0; j < lines.size(); j++) {
			
			//TODO: should I check for negatives in og parseInt? 
			//TODO: SHOULD I DO READINT instead 
			 try {
				 num = Integer.parseInt(lines.get(j));
				 weights[j] = num;
				 
			 }
			 catch(NumberFormatException n) {
				 int[] invalid = {-1};
				 return invalid;
			 }
		}
		
		return weights;
		
	}
	
	public static void writeTestToFile(String fileName, int limit, int[] w) throws FileNotFoundException {
		
		PrintWriter output = new PrintWriter(OUTPUT_NAME);
		
		output.println(fileName + "\t" + limit + "\t" + Arrays.toString(w) + "\n");
		
		ArrayList<Integer> watermelons = new ArrayList<Integer>();
		
		knapsackSum(w, w.length - 1, limit, watermelons);
		
		for(Integer watermelonWeight: watermelons) {
			output.println(watermelonWeight + " pound watermelon");
		}
		
		output.close();
		
	}
}
