package com.udit.atomicref;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {

	public static void main(String[] args) {

		String a1 = "First";
		String a2 = "Second";

		AtomicReference<String> ref = new AtomicReference<>(a1);
		System.out.println("Atomic ref initial value : " + ref.get());
		
		boolean isSet = ref.compareAndSet(a1, a2);
		System.out.println("Atomic ref updated : " + isSet + ", value : " + ref.get());

		isSet = ref.compareAndSet(a1, a2);
		System.out.println("Atomic ref updated : " + isSet + ", value : " + ref.get());

		isSet = ref.compareAndSet(a2, a1);
		System.out.println("Atomic ref updated : " + isSet + ", value : " + ref.get());

	}
}
