package com.udit.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestRecursiveTask {

	public static void main(String[] args) {

		int[] data = { 2, 42, 97, 17, 35, 19, 5, 51, 79, 91, 21, 85, 77, 89, 61 };

		ForkJoinPool pool = ForkJoinPool.commonPool();
		Integer max = pool.invoke(new MaxNumberFinder(data));

		System.out.println("Parellism  = " + pool.getParallelism());
		System.out.println("Work Steal Count = " + pool.getStealCount());

		System.out.println("Result Max = " + max);
	}

	static class MaxNumberFinder extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 1L;

		private static final int THRESHOLD_LENGTH = 3;
		private int[] data;

		public MaxNumberFinder(int[] data) {
			this.data = data;
		}

		@Override
		protected Integer compute() {

			int max = 0;
			System.out.println("inside compute by " + Thread.currentThread().getName());

			if (data.length < THRESHOLD_LENGTH) {
				for (int i = 0; i < data.length; i++) {
					if (data[i] > max) {
						max = data[i];
					}
				}
				return max;
			} else {
				int[] firstHalf = Arrays.copyOfRange(data, 0, data.length / 2);
				int[] secondHalf = Arrays.copyOfRange(data, data.length / 2, data.length);

				MaxNumberFinder left = new MaxNumberFinder(firstHalf);
				MaxNumberFinder right = new MaxNumberFinder(secondHalf);

				left.fork();
				right.fork();

				return Math.max(left.join(), right.join());
			}
		}
	}
}
