package com.udit.cyclicbarrier;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {

	private static final Result result = new Result();
	private static final Integer[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static final CyclicBarrier barrier = new CyclicBarrier(5, new ResultGetter());;

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(5);

		es.submit(new MinTask());
		es.submit(new MaxTask());
		es.submit(new CountTask());
		es.submit(new SumTask());
		es.submit(new AvgTask());

		es.shutdown();

	}

	static class ResultGetter implements Runnable {
		@Override
		public void run() {
			System.out.println("\nAll computations done..");
			System.out.println(result);
		}
	}

	static class MinTask implements Runnable {
		@Override
		public void run() {
			result.setMin(Collections.min(Arrays.asList(data)));
			try {
				System.out.println("Calculated Min..waiting for others...(no of parties waiting : "
						+ barrier.getNumberWaiting() + ")");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	static class MaxTask implements Runnable {
		@Override
		public void run() {
			result.setMax(Collections.max(Arrays.asList(data)));
			try {
				System.out.println("Calculated Max..waiting for others...(no of parties waiting : "
						+ barrier.getNumberWaiting() + ")");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	static class CountTask implements Runnable {
		@Override
		public void run() {
			result.setCount(data.length);
			try {
				System.out.println("Calculated Count..waiting for others...(no of parties waiting : "
						+ barrier.getNumberWaiting() + ")");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	static class SumTask implements Runnable {
		@Override
		public void run() {
			result.setSum(Arrays.stream(data).mapToInt(Integer::intValue).sum());
			try {
				System.out.println("Calculated Sum..waiting for others...(no of parties waiting : "
						+ barrier.getNumberWaiting() + ")");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	static class AvgTask implements Runnable {
		@Override
		public void run() {
			result.setAvg(Arrays.stream(data).mapToInt(Integer::intValue).average().getAsDouble());
			try {
				System.out.println("Calculated Avg..waiting for others...(no of parties waiting : "
						+ barrier.getNumberWaiting() + ")");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	static class Result {
		private int min;
		private int max;
		private int count;
		private int sum;
		private double avg;

		public void setMin(int min) {
			this.min = min;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}

		public void setAvg(double avg) {
			this.avg = avg;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("----------------------------\n");
			sb.append("         Statistics\n");
			sb.append("----------------------------\n");
			sb.append("Min = " + min);
			sb.append("\nMax = " + max);
			sb.append("\nCount = " + count);
			sb.append("\nSum = " + sum);
			sb.append("\nAvg = " + avg);
			sb.append("\n----------------------------");
			return sb.toString();
		}
	}
}
