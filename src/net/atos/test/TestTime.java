package net.atos.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import EDU.ksu.cis.viewer.BSTInterface;
import EDU.ksu.cis.viewer.BinarySearchTree;

public class TestTime {
	public static final int N = 10000;
	public static BSTInterface bt = null;
	public static int[] arr = null;
	public static  AtomicInteger i = new AtomicInteger();

	static private BSTInterface insertOneThread(int[] arr) {
		BSTInterface bt = new BinarySearchTree();

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			bt = bt.put(arr[i], arr[i]);
		}
		System.out.println("time for filling array in one thread ="
				+ (System.currentTimeMillis() - startTime) + " ms");

		return bt;

	}

	static private BSTInterface insertMoreThreads(int[] arr, int n) {
		bt = new BinarySearchTree();

		ExecutorService executor = Executors.newFixedThreadPool(n);
		for (int i = 0; i < n; i++) {
			Runnable worker = new Feeder();
			executor.execute(worker);
		}

		try {
			executor.shutdown();
			executor.awaitTermination(50, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bt;

	}

	static private int[] randomArray() {
		int[] arr = new int[N];

		Random generator = new Random();

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < N; i++) {
			arr[i] = generator.nextInt();
		}
		System.out.println("time for filling array="
				+ (System.currentTimeMillis() - startTime) + " ms");
		return arr;
	}
	
	static private void runRange() {
		arr = randomArray();

		// bt = insertOneThread(arr);
		for (int j = 1; j < 20; j++) {
			long time = System.currentTimeMillis();
			for (int k = 0 ; k < 5; k++) {
				i = new AtomicInteger();
				insertMoreThreads(arr, j);
			}
			time = System.currentTimeMillis() - time;
			System.out.println(" N = " + N + " threads=" + j + " time = " + time / 5);
			
		}
		
	}
	static private void runOnce() {
		arr = randomArray();

		// bt = insertOneThread(arr);
		
			long time = System.currentTimeMillis();
			for (int k = 0 ; k < 5; k++) {
				i = new AtomicInteger();
				insertMoreThreads(arr, 5);
			}
			
			time = System.currentTimeMillis() - time;
			System.out.println(" N = " + N + " threads=" + 5 + " time = " + time / 5);
			
		
		
	}

	public static void main(String[] args) {

//		runOnce();

		runRange();
	}

}
