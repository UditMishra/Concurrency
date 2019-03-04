package com.udit.synchronousqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSynchronousQueue {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BlockingQueue<String> queue = new SynchronousQueue<>();

		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					String s = String.valueOf(i);
					System.out.println("Putting in Queue : " + s);
					try {
						queue.put(s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(1000);
						System.out.println("Took from Queue " + queue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		consumer.start();
		producer.start();

	}
}
