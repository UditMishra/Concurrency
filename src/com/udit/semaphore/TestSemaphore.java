package com.udit.semaphore;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

	private static Semaphore s = new Semaphore(5);
	private static final long SLEEP_TIME = 3 * 1000l;

	public static void main(String[] args) {

		System.out.println("Program execution beings..\nAvailable permits = " + s.availablePermits());
		for (int i = 0; i < 20; i++) {
			new Contender("Contender-" + (i + 1)).start();
		}
	}

	static class Contender extends Thread {
		public Contender(String name) {
			super(name);
		}

		@Override
		public void run() {

			String name = Thread.currentThread().getName();
			System.out.println(name + " is waiting to acquire permit..Available permits = " + s.availablePermits());

			try {
				s.acquire();
				System.out.println(name + " is has acquired permit..Available permits = " + s.availablePermits());

				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				s.release();
				System.out.println(name + " has released permit..Available permits = " + s.availablePermits());
			}
		}

	}
}
