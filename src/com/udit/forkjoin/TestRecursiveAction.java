package com.udit.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class TestRecursiveAction {

	public static void main(String[] args) {

		int[] num = { 5, 9, 13, 43, 65, 86, 99 };
		SqaureAction action = new SqaureAction(num);
		action.compute();
	}

	static class SqaureAction extends RecursiveAction {

		private static final long serialVersionUID = 1L;

		private static final int THRESHOLD_LENGTH = 1;
		private int[] num;

		public SqaureAction(int[] num) {
			this.num = num;
		}

		@Override
		protected void compute() {

			if (num.length > THRESHOLD_LENGTH) {
				ForkJoinTask.invokeAll(createSubTasks());
			} else {
				process(num[0]);
			}
		}

		private List<SqaureAction> createSubTasks() {

			int[] firstHalf = Arrays.copyOfRange(num, 0, num.length / 2);
			int[] secondHalf = Arrays.copyOfRange(num, num.length / 2, num.length);

			return Arrays.asList(new SqaureAction(firstHalf), new SqaureAction(secondHalf));

		}

		private void process(int no) {
			System.out.println("No : " + no + " Square : " + (no * no) + " by " + Thread.currentThread().getName());
		}
	}
}
