package com.udit.sleepinterrupt;

public class TestInterrupt {

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Inside thread..");
				System.out.println("Going to sleep for 10 secs...");
				try {
					Thread.sleep(10 * 1000L);
				} catch (InterruptedException e) {
					System.out.println("Got interrupted..");
				}

			}
		});
		t.start();
		t.interrupt();
	}

}
