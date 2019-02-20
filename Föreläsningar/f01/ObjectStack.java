package f01;
import java.util.EmptyStackException;

public class ObjectStack implements Stack2{
    private Object[] elements;
    private int size=0;
    
    public ObjectStack(int capacity) {
        elements = new Object[capacity];
    }

    public void push(Object element) {
    	if(size>=elements.length)
    		throw new StackOverflowException();
        elements[ size ] = element;
        size++;
    }

    public Object pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public Object peek() {
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
        ObjectStack stack = new ObjectStack(20);
        Integer elem;
        int sum=0;
        for(int i=30; i<40; i++) {
            stack.push(Integer.valueOf( i ));
        }
//        stack.push("HEJ"); // går bra men blir fel på rad 50
        while(!stack.isEmpty()) {
            elem = (Integer)stack.pop();
            sum += elem;
            System.out.print(elem + " ");
        }
        stack.push("Hej");
        System.out.println("sum=" + sum + "\n----------------------------");
        for( int i = 50; i < 55; i++ )
            stack.push( Integer.valueOf( i ) );
        System.out.println( "Size = " + stack.size() );
        System.out.println( "Första element = " + stack.peek() );
        while( !stack.isEmpty() )
            System.out.println( "Element = " + stack.pop() +", size = " + stack.size() );
    }
}
