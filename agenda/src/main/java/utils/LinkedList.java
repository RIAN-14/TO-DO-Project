package utils;

import java.util.Iterator;

public class LinkedList<Type> implements List<Type>,Iterable<Type> {
	
	
	private int numberOfElements;
	private ListElement first;
	private ListElement last;
	
	
	private class ListElement {
		public Type value;
		public ListElement next;
		public ListElement previous;
		public ListElement(Type value) {
			this.value = value;
			next = null;
			previous = null;
		}
	}
	

	public class Parcours implements Iterator<Type> {

		private ListElement currentIndex, toRemove;
		
		public Parcours() {
			currentIndex= first;
			toRemove=null;
		}
		
		public boolean hasNext() {
			return currentIndex != null;
		}

		public Type next() {
			Type value = currentIndex.value;
			toRemove=currentIndex;
			currentIndex = currentIndex.next;
			return value;
		}
		
		public void remove() {
			LinkedList.this.remove(toRemove);
		}
		
	}

	public class ParcoursEnvers implements Iterator<Type> {

		private ListElement currentIndex,toRemove;
		
		public ParcoursEnvers() {
			currentIndex= last;
			toRemove=null;
		}
		
		public boolean hasNext() {
			return currentIndex.previous != null;
		}

		public Type next() {
			Type value = currentIndex.value;
			toRemove = currentIndex;
			currentIndex = currentIndex.previous;
			return value;
		}
		
		public void remove() {
			LinkedList.this.remove(toRemove);
		}
		
	}
	
	public LinkedList() {
		numberOfElements = 0;
		first = null;
		last = null;
	}
	
	public void add(Type element) {
		ListElement newElement = new ListElement(element);
		if (first == null) { // liste vide
			first = newElement;
		} else { // liste non vide
			last.next = newElement;
			newElement.previous = last;
		}
		last = newElement;
		numberOfElements++;
	}
	
	private void remove(ListElement le) {
		if (le == first) {
			first = le.next;
			if (first != null) {
				first.previous = null;
			} else {
				last = null;
			}
		} else if (le == last) {
			last = le.previous;
			last.next = null;
		} else {
			le.previous.next = le.next;
			le.next.previous = le.previous;
		}
		le = null;
		numberOfElements--;
	}
	
	public void remove(Type element) {
		ListElement current = first;
		while (current != null) {
			if (current.value == element) {
				remove(current);
			}
			current = current.next;
		}
	}
	
	public void remove(int index) {
		ListElement current = first;
		int currentIndex = 0;
		while (current != null) {
			if (currentIndex == index) {
				remove(current);
				break;
			}
			current = current.next;
			currentIndex++;
		}
	}
	
	public Type get(int index) {
		ListElement current = first;
		int currentIndex = 0;
		while (current != null) {
			System.out.print(currentIndex);
			System.out.print(' ');
			if (currentIndex == index) {
				return current.value;
			}
			currentIndex++;
			current = current.next;
		}
		return null;
	}
	
	public int size() {
		return numberOfElements;
	}
	
	public boolean contains(Type element) {
		ListElement current = first;
		while (current != null) {
			if (current.value == element) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public int indexOf(Type element) {
		ListElement current = first;
		int index = 0;
		while (current != null) {
			if (current.value == element) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}
	
	public void clear() {
		ListElement current = first;
		first=null;
		ListElement next ;
		while(current != null) {
			next = current.next;
			
			if(next != null) {
				next.previous =null;
			}
			current.value=null;
			current.next =null;
			current = next;
		}
		numberOfElements = 0;
		last = null;
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	}

	public Iterator<Type> iterator() {
		return new Parcours();
	}
}
