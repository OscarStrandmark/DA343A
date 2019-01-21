package F01;
import java.util.EmptyStackException;

public class IntegerStack implements Stack1 {
    private Integer[] elements;
    private int size=0;
    
    public IntegerStack(int capacity) {
        elements = new Integer[capacity];
    }

    public void push(Integer element) {
    	if(size>=elements.length)
    		throw new StackOverflowException();
        elements[ size ] = element;
        size++;
    }

    public Integer pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public Integer peek() {
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
        IntegerStack stack = new IntegerStack(20);
        int elem;
        for(int i=30; i<40; i++) {
            stack.push(i); // int -> Integer - autoboxing, annars Integer.valueOf(i)
        }
        while(!stack.isEmpty()) {
            elem = stack.pop(); // Integer -> int - unboxing, annars stack.pop().intValue()
            System.out.print(elem + " ");
        }
    }
}
