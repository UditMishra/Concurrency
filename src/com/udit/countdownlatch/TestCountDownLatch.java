package com.udit.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

	private static CountDownLatch startLatch = new CountDownLatch(1);
	private static CountDownLatch workLatch = new CountDownLatch(3);

	public static void main(String[] args) {

		WorkerThread t1 = new WorkerThread("Thread-1");
		WorkerThread t2 = new WorkerThread("Thread-2");
		WorkerThread t3 = new WorkerThread("Thread-3");

		t1.start();
		t2.start();
		t3.start();

		System.out.println("All threads started..they will wait for startLatch..");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Main thread is now ready, counting down on startLatch..");
		startLatch.countDown();
		System.out.println("Now main thread is waiting for work to complete from all threads..");

		try {
			workLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All tasks completed..main task is also completed..");

	}

	static class WorkerThread extends Thread {

		public WorkerThread(String name) {
			super(name);
		}

		@Override
		public void run() {

			try {
				System.out.println(Thread.currentThread().getName() + " is waiting to start..");
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " is running..");

			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " completed..");
			workLatch.countDown();
		}

	}

}
