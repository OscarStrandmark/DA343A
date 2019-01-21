package F01;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private T[] elements;
    private int size=0;
    
    public ArrayStack(int capacity) {
        elements = (T[])(new Object[capacity]);
    }

    public void push(T element) {
    	if(size>=elements.length)
    		throw new StackOverflowException();
        elements[ size ] = element;
        size++;
    }

    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[size-1];
    }

    public boolean isEmpty() {
        return (size==0);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>(20);
        Integer elem;
        for(int i=0; i<10; i++) {
            stack.push(Integer.valueOf(i));
        }
//        stack.push("HEJ"); // kompileringsfel
        while(!stack.isEmpty()) {
            elem = stack.pop();
            System.out.print(elem + " ");  // elem.toString() anropas
        }
    }
}
