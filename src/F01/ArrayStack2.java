package F01;
import java.util.EmptyStackException;

public class ArrayStack2<T> implements StackMC<T> {
    private T[] elements;
    private int size=0;
    
    public ArrayStack2(int capacity) {
        elements = (T[])(new Object[capacity]);
    }

    public StackMC<T> push(T element) {
    	if(size>=elements.length)
    		throw new StackOverflowException();
        elements[ size ] = element;
        size++;
        return this;
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
    	ArrayStack2<Integer> stack = new ArrayStack2<Integer>(20);
    	StackMC<Integer> s = stack.push( Integer.valueOf(10) ); // push returnerar referens till objekt som implementerar StackMC<Integer>. s==stack fast olika typ av referens
    	s.push( Integer.valueOf(23) ); // man behöver inte använda returvärdet i java
    	stack.push( Integer.valueOf(8) );
    	stack.push( Integer.valueOf(11) );
    	
//    	ArrayStack2<Integer> stack = new ArrayStack2<Integer>(20);
//    	stack.push(15).push( Integer.valueOf(23) ).push( Integer.valueOf(8) ).push( Integer.valueOf(11) );    	
    	
//    	StackMC<Integer> stack = new ArrayStack2<Integer>(20).push(10).push(23).push(8).push(11); // autoboxing skapar automatiskt Integer-objekt

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
