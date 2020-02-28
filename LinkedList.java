

/**
 * Implementation of the List interface.
 * 
 * This implementation involves a single-linked list.
 * 
 * @author Greg Gagne - February 2017
 *
 */
public class LinkedList <E>implements List <E>{
	// reference to the head of the linked list
	private Node head;

	// number of elements in the list
	private int numberOfElements;

	public LinkedList() {
		head = null;
	}

	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private E data;
		private Node next;

		private Node(E data) {
			this(data,null);
		}

		private Node (E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// Methods

	@Override
	public void add(E item) {

		// adds (appends) an item to the rear of the list

		Node newNode = new Node(item);
		Node current = head;

		if (isEmpty()) {
			// special case - first element being added to the list
			head = newNode;
		}
		else {
			while (current.next != null) {
				current = current.next;
			}

			// current now references the last item in the list
			current.next = newNode;
		}

		newNode.next = null;
		++numberOfElements;
	}

	@Override
	public boolean add (E item, int index) {	
		Node newNode = new Node(item);
		Node current = head;
		int num  = 0;
		Node next = null;
		
		if(isEmpty()) {
			head = newNode;
			numberOfElements = 1;
		}
		
		else if (index==0){
			newNode.next = head;
			head = newNode;
			numberOfElements++;
			return true;
		}
		
		else if((index + 1) <= numberOfElements) { 
		while(num < (index - 1)) { 
			current = current.next;
			num++;
		}
		
		next = current.next;
		current.next = newNode;
		newNode.next = next;
		numberOfElements++;
		
		return true;
	}
	return false;
}


	@Override
	public boolean contains(E item) {
		Node current = head;
		boolean found = false;

		while (current != null && !found) {
			if (current.data.equals(item)) {
				found = true;
			}

			current = current.next;
		}

		return found; 

	}

	@Override
	public E get(int index) {
		Node current = head;
		int num = 0; 
		if(index >= numberOfElements){
			return null;
		}
		else{
			while(num != index) { 
				current = current.next;
				num++;
			}
		}
		return current.data;
	}


	@Override
	public boolean remove(E item) {
		if (this.contains(item) == true) {
		
				if(item.equals(head.data)) {
					if(head.next == null) {
						head = null;
						numberOfElements--;
					}
					else {
					head = head.next;
					numberOfElements--;
					}
				}
				else {
						Node current = head;
						Node store = null;
						if(contains(item)) {
							while(current.data != item) {
								store = current;
								current = current.next;
							}
							store.next = current.next;
							numberOfElements--;
						}
						
				}	
				return true;
			}
		
			else {
				return false;
			}
	}

	@Override
	public E remove(int index) {
		E rv = null;

		if (isEmpty() || index >= numberOfElements) {
			rv = null;
		}
		else if (index == 0) {
			// special case - first element in the list
			rv = head.data;
			head = head.next;
			numberOfElements--;
		}
		else {
			int currentIndex = 0;
			Node current = head;

			while (currentIndex < (index - 1)) {
				current = current.next;
				currentIndex++;
			}
			// current references the node we want to remove
			rv = current.next.data;
			current.next = current.next.next;
			numberOfElements--;
		}

		return rv;
	}

	@Override
	public int getLength() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		// TO DO
		Node current = head;
		for (int a = 0; a < numberOfElements; a++) {
			if (a == 0 && head.equals(null) == false) {
				return false;
			}
			else if(current.next.equals(null) == false) {
				return false;
			}
			else {
				current = current.next;
			}
		}
		return true;
		}
	

	@Override
	public int getFrequency(E item) {
		// TO DO
		int freq = 0;
		int index = 0;
		Node current = head;
		while (index < numberOfElements) {
			if(current.data.equals(item)) {
			freq++;
			}
			current = current.next;
			index++;
		}
		return freq;
	}

	@Override
	public void clear() {
		//TO DO
		Node current = head;
		while(current.next != null) {
			remove(current.next.data);
			current = current.next;
			numberOfElements --;
		}
		remove(head.data);
		remove(current.data);
		numberOfElements = numberOfElements - 2;
	}
	
	public E getMax() {
		Node current = head;
		E max = head.data;
		while(current.next!=null) {
			if ((int)max <= (int)current.data) {
				max = current.next.data;
			}
			current = current.next;
		}
		return max;
	}
	
	public int indexOf(E item) {
		Node current = head;
		int index = 0;
		for (int i = 0; i < numberOfElements; i++) {
			if (current.data == item) {
					index = i;
					break;
			}
			current = current.next;
		}
		return index;
	}
}
