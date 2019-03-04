package com.udit.hashcode;

public class TestHashCode {

	public static void main(String[] args) {

		Object obj = new Object();
		
		int h1 = obj.hashCode();
		int h2 = System.identityHashCode(obj);

		System.out.println("Hashcode 1 : " + h1);
		System.out.println("Hashcode 2 : " + h2);
		
		Object nullRef = null;
		
		System.out.println("Hashcode nullRef : " + System.identityHashCode(nullRef));
	}
}
