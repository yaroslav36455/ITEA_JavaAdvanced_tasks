package ua.itea;

import java.util.ArrayList;

public class Stack<T> {
	private ArrayList<T> stack;
	
	public Stack() {
		stack = new ArrayList<>();
	}
	
	public void push(T elem) {
		stack.add(elem);
	}
	
	public void pop() {
		stack.remove(stack.size() - 1);
	}
	
	public T top() {
		return stack.get(stack.size() - 1);
	}
	
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	public int size() {
		return stack.size();
	}
}
