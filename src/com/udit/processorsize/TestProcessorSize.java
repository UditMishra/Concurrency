package com.udit.processorsize;

public class TestProcessorSize {

	public static void main(String[] args) {
		
		int noOfCores = Runtime.getRuntime().availableProcessors();
		System.out.println("No of processor cores are : " + noOfCores);
		
	}

}
