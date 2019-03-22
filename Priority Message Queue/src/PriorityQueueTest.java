/**
 *  This class tests the priority queue by running a set number of trials trials using 
 *  different random numbers from 0 to MAX_NUMBER for the number of messages
 *  added in each trial. A trial is a simulation where every "minute" a Message
 *  is added with a random priority from 0-4 (at a probability of .2) and 
 *  each Message takes four "minutes" to process and can only be removed if it
 *  is the highest priority Message . Then, the trial is analyzed and printed
 *  for several factors including: total messages, final time, average wait times
 *  per priority, number of messages per level, rate at which each priority level occurred, 
 *  and the deviation of said rate from .2. This class is attempting to assess
 *  the efficiency of the MessagePriorityQueue created for it.
 *  
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PriorityQueueTest {
	
	/**
	 * The number of trials there will be, where a trial represents
	 * creating a PriorityQueue adding the allotted number of Messages, and
	 * removing them as specified in the assignment then analyzing the data
	 */
	private static int NUM_TRIALS = 10;
	
	/**
	 * The maximum number of Messages that can be added during a trial
	 */
	private static int MAX_NUMBER = 10000;
	
	/**
	 * The number of priorities in the priority queue data structure we are 
	 * using
	 */
	private static int NUM_PRIORITIES = MessagePriorityQueue.NUM_PRIORITIES;
	
	/**
	 * The number of minutes it takes to process one Message
	 */
	private static int TIME_TO_PROCESS = 4;
	
	/**
	 * The current time of a trial/the number of minutes elapsed 
	 * since the beginning of a trial
	 */
	private static int time;
	
	/**
	 * An ArrayList of the waitingTimes, which will hold all of the waiting 
	 * times for each level
	 */
	private static ArrayList[] waitingTimes;
	
	/**
	 * Processes msg by calculating the waiting time then adding it to the
	 * correct position (its corresponding priority level) in waitingTimes
	 * 
	 * @param msg the Message to be processed
	 */
	private static void process(Message msg) {
		int wait = time - msg.getArrivalTime();
		ArrayList<Integer> times = (ArrayList<Integer>) (waitingTimes[msg.getPriority()]);
		times.add(wait);
	}
	
	
	/**
	 * Analyzes the trials and prints the pertinent information: the number
	 * of total messages, the final time at the end of the trial, and for
	 * each priority level: average wait times, number of messages, 
	 * rate at which messages of that level occurred, and the deviation of that
	 * rate from .2.
	 * 
	 * @param totalMessages the number of messages in the current trial
	 */
	private static void analyze(int totalMessages) {
		double[] averageWaits= new double[NUM_PRIORITIES];
		int sum;

		for(int i = 0; i < averageWaits.length; i++) {
			sum = 0;
			
			for(Integer wait: (ArrayList<Integer>) (waitingTimes[i]))
				sum += wait.intValue();
			
			if(waitingTimes[i].size() == 0)
				averageWaits[i] = 0;
			else
				averageWaits[i] = sum/((double) waitingTimes[i].size());
		}
		
		System.out.println("Total Messages: " + totalMessages);
		
		System.out.println("Final Time: " + time);
		
		System.out.println("\nAverage Wait Times: " );
		
		NumberFormat decimal = new DecimalFormat("#0.00");
		
		for(int i = 0; i < NUM_PRIORITIES; i++)
			System.out.println("\tPriority " + i 
					+ ": " + decimal.format(averageWaits[i]));
		
		int[] numEach = new int[NUM_PRIORITIES];
		
		System.out.println("\nNumber of Messages: ");
		for(int i = 0; i < NUM_PRIORITIES; i++) {
			numEach[i] = waitingTimes[i].size();
			System.out.println("\tPriority " + i 
					+ ": " + numEach[i]);
		}
		
		double[] rateForEach = new double[5];
		System.out.println("\nRate at which Each Occurred: ");
		for(int i = 0; i < NUM_PRIORITIES; i++) {
			rateForEach[i] = numEach[i]/(double) totalMessages;
			System.out.println("\tPriority " + i + ": " + 
					decimal.format(rateForEach[i]));
		}
		
		System.out.println("\nDeviation from Rate of .2: ");
		for(int i = 0; i < NUM_PRIORITIES; i++) {
			System.out.println("\tPriority " + i + ": " + 
					decimal.format(Math.abs(rateForEach[i] - 1.0/5)));
		}		
	}
	
	/**
	 * Simulates a trial under the following rules: a Message is added every
	 * minute to the priority queue and one is processed from the priority queue
	 * every four minutes. Then, the data from the trial is analyzed and printed
	 * using the analyze method.
	 * 
	 * @param totalMessages the number of Messages to be processed during
	 * 		the trial
	 */
	public static void trial(int totalMessages) {
		MessagePriorityQueue queue = new MessagePriorityQueue();
		time = 0;
		waitingTimes = new ArrayList[NUM_PRIORITIES];
		
		for(int i = 0; i < waitingTimes.length; i++)
			waitingTimes[i] = new ArrayList<Integer>();
		
		int numMessages = 0, random;
		
		
		while(numMessages < totalMessages) {
			time++;
			
			random = (int) (Math.random() * NUM_PRIORITIES);
			queue.add(new Message(random, time));
			numMessages++;
			
			if(time - queue.peek().getArrivalTime() >= TIME_TO_PROCESS) 
				process(queue.remove());
			
		}
		
		//this processes all remaining messages after you stop adding them
		while(! queue.isEmpty()) {
			time++;
			
			if(time - queue.peek().getArrivalTime() >= TIME_TO_PROCESS)
				process(queue.remove());
		}
		
		analyze(totalMessages);

	}

	public static void main(String[] args) {
		int random;
		
		for(int i = 0; i < NUM_TRIALS; i++) {
			random = (int) (Math.random() * (MAX_NUMBER + 1));
			System.out.println("Trial " + (i + 1) + "\n");
			trial(random);
			System.out.println("\n---------------------------------------");
		}
	}
}
