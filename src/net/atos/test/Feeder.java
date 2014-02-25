package net.atos.test;

import EDU.ksu.cis.viewer.BinarySearchTree;


public class Feeder implements Runnable  {

	
	 
	
	
	@Override
	public void run() {
		int ii =  TestTime.i.getAndIncrement();
		
		while (ii < TestTime.N ) {
	
			 TestTime.bt = TestTime.bt.put(TestTime.arr[ii], TestTime.arr[ii]);
			ii =  TestTime.i.getAndIncrement();

		}
		
		
		
	}

}
