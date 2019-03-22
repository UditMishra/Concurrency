package com.udit.copyonwritearraylist;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestCopyOnWriteArrayList {

	public static void main(String[] args) {

		Integer[] data = { 1, 2, 3 };
		List<Integer> list = new CopyOnWriteArrayList<>(data);

		Iterator<Integer> itr = list.iterator();
		System.out.println("Took iterator");

		list.add(4);
		list.add(null);
		System.out.println("Added 4 after that");

		itr.forEachRemaining(System.out::println);

		System.out.println("Taking again iterator");
		itr = list.iterator();

		itr.forEachRemaining(System.out::println);

		try {
			itr.remove();
		} catch (UnsupportedOperationException e) {
			System.out.println("Removal from iterator is not supported");
		}

		ListIterator<Integer> listItr = list.listIterator();

		try {
			listItr.add(1);
		} catch (UnsupportedOperationException e) {
			System.out.println("Add from ListIterator is not supported");
		}

		if (listItr.hasNext()) {
			try {
				listItr.set(2);
			} catch (UnsupportedOperationException e) {
				System.out.println("Set from ListIterator is not supported");
			}
		}

		try {
			listItr.remove();
		} catch (UnsupportedOperationException e) {
			System.out.println("Remove from ListIterator is not supported");
		}
	}
}
