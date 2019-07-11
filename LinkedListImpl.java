package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  int numElts = 0; // integer that tells how many items are in the list at any given time
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel = new Node(0); //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. 
  //Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	if(index < 0 || numElts < index) { // if index is beyond list size range return false
		return false; // insert was not a success
	}
	
	Node c = new Node(elt); // the node that we want to insert
	Node head = sentinel.next; // indicates the first element of the list
	
	if(index == 0 && numElts == 0) { // if this is the first element that we want to insert into the list
		c.next = sentinel;
		sentinel.next = c;
		c.prev = sentinel;
		sentinel.prev = c;
	}else if(index == 0) { // if we went to insert an element into the first position (0 index)
		head.prev = c;
		c.next = head;
		sentinel.next = c;
	}else{ // What was in the PPT slide on assignment details
		Node curr = head;
		int ct = 0;
		while(curr != sentinel) {
			if(ct < index) {
				curr = curr.next;
				ct++;
			}else {
				break;
			}
		}
		c.prev = curr.prev;
		curr.prev = c;
		c.prev.next = c;
		c.next = curr;
	}
	
	numElts++; // Insert was successful so number of elements increases
	return true;
}

@Override
public boolean remove(int index) {
	if(index < 0 || index >= numElts) {
		return false; // remove was not successful
	}
	
	Node curr = sentinel.next;
	
	if(index == 0 && numElts == 1) { // if there is only one element in the list
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}else if(index == 0) { // if the first element in the list has to be removed
		sentinel.next = curr.next;
		curr.next.prev = sentinel;
	}else { // basically the opposite of what was in the insert function
		int ct = 0;
		while(curr != sentinel) {
			if(ct < index) {
				curr = curr.next;
				ct++;
			}else {
				break;
			}
		}
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
	}
	
	numElts--; // remove was successful so number of elements decreases
	return true;
}

@Override
public double get(int index) {
	if (index < 0 ||index >= numElts) {
		return Double.NaN;
	}
	
	Node curr = sentinel.next;
	while (index != 0) { // counts down the index until the element that we are suppose to get is found
		curr = curr.next;
		index--;
	}
	
	return curr.data;
}

@Override
public int size() {
	return numElts;
}

@Override
public boolean isEmpty() {
	if(numElts == 0){
		return true;
	}
	return false;
}

@Override
public void clear() {
	while(numElts != 0) { // Removes first element of list by using the remove function until the list is empty
		remove(0);
	}
}
}