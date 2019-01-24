package f02;

import java.util.EmptyStackException;
import java.util.LinkedList;
import f01.Stack;

public class LinkedStack<T> implements Stack<T> {
	private LinkedList<T> stack = new LinkedList<T>();

	public void push(T element) {
		stack.addLast(element);
	}

	public T pop() {
		if(stack.size()==0)
			throw new EmptyStackException();
		return stack.removeLast();
	}

	public T peek() {
		if(stack.size()==0)
			throw new EmptyStackException();
		return stack.getLast();
	}

	public boolean isEmpty() {
		return (stack.size()==0);
	}

	public int size() {
		return stack.size();
	}
}
