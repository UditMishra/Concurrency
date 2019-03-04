package com.udit.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestExchanger {

	public static void main(String[] args) {

		// Exchanger acts like two way SynchronousQueue
		Exchanger<String> exchanger = new Exchanger<>();

		MyThread a = new MyThread("A", "a", exchanger);
		MyThread b = new MyThread("B", "b", exchanger);
		
		a.start();
		b.start();
		
	}

	static class MyThread extends Thread {

		private String data;
		private Exchanger<String> exchanger;

		public MyThread(String name, String data, Exchanger<String> exchanger) {
			super(name);
			this.data = data;
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			System.out.println("Before exchange Thread " + Thread.currentThread().getName() + " has data " + data);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				data = exchanger.exchange(data, 5000, TimeUnit.SECONDS);
			} catch (InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}

			System.out.println("After exchange Thread " + Thread.currentThread().getName() + " has data " + data);

		}
	}

}
